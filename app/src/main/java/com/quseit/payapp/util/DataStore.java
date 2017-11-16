package com.quseit.payapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Base64;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/**
 * 文 件 名: DataStore
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/16 12:59
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class DataStore {
    //加解密模式
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private KeyStore keyStore;
    private Map<String,String> params = new HashMap<>();
    private SharedPreferences mSharedPreferences;
    private static final String alias = "secret_rm";

    private static DataStore instance = null;

    private DataStore(){
        initKeyStore();
    }

    public void init(Context context){
        mSharedPreferences = context.getSharedPreferences("secret", Context.MODE_PRIVATE);
    }

    public static DataStore getInstance(){
        if (instance==null){
            synchronized (DataStore.class){
                instance = new DataStore();
            }
        }
        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void save(String key, String value){
        try {
            mSharedPreferences.edit().putString(key,encryptText(alias,value)).apply();
        } catch (UnrecoverableEntryException | IllegalBlockSizeException | BadPaddingException | SignatureException | InvalidAlgorithmParameterException | IOException | InvalidKeyException | NoSuchPaddingException | NoSuchProviderException | KeyStoreException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getData(String key){
        String result = mSharedPreferences.getString(key,null);
        try {
            result = decryptData(alias,result);
        } catch (UnrecoverableEntryException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | IOException | InvalidKeyException | NoSuchPaddingException | NoSuchProviderException | KeyStoreException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * 对内容进行加密，返回加密后的字节数组
     * */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public String encryptText(final String alias, final String textToEncrypt)
            throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
            NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
            InvalidAlgorithmParameterException, SignatureException, BadPaddingException,
            IllegalBlockSizeException {

        //取出secretKey
        SecretKey secretKey = getSecretKey(alias);
        //没有则先创建
        if (secretKey == null){
            secretKey = createSecretKey(alias);
        }
        //初始化cipher为加密模式
        final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        //加密参数
        String iv = Base64.encodeToString(cipher.getIV(), Base64.DEFAULT);
        params.put(alias,iv);

        return Base64.encodeToString(cipher.doFinal(textToEncrypt.getBytes("UTF-8")), Base64.DEFAULT);
    }

    /**
     * 根据别名生成秘钥
     * */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    private SecretKey createSecretKey(final String alias) throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidAlgorithmParameterException {

        final KeyGenerator keyGenerator = KeyGenerator
                .getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE);

        keyGenerator.init(new KeyGenParameterSpec.Builder(alias,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build());
        return keyGenerator.generateKey();
    }

    /**
     * 初始化AndroidKeyStore,用于获取秘钥
     * */
    private void initKeyStore(){
        try {
            keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
            keyStore.load(null);
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对数据进行解密，需要加密时的加密参数
     * */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String decryptData(final String alias, String encryptedData)
            throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
            NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
            BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        //先那拿到秘钥
        SecretKey secretKey = getSecretKey(alias);
        //如果没有秘钥则解密失败
        if (secretKey == null){
            return null;
        }
        //拿到加密参数
        String iv = params.get(alias);
        if (iv == null){
            return null;
        }
        byte[] encryptionIv = Base64.decode(iv, Base64.DEFAULT);
        //初始化cipher设置模式为解密
        final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        final GCMParameterSpec spec = new GCMParameterSpec(128, encryptionIv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
        return new String(cipher.doFinal(Base64.decode(encryptedData, Base64.DEFAULT)));
    }

    /**
     * keyStore根据别名拿到秘钥
     * */
    private SecretKey getSecretKey(final String alias) throws NoSuchAlgorithmException,
            UnrecoverableEntryException, KeyStoreException {
        KeyStore.SecretKeyEntry entry = (KeyStore.SecretKeyEntry) keyStore.getEntry(alias, null);
        if (entry == null){
            return null;
        }
        return entry.getSecretKey();
    }
}
