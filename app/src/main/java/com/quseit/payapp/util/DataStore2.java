package com.quseit.payapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.support.annotation.RequiresApi;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.security.auth.x500.X500Principal;

/**
 * 文 件 名: DataStore
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/16 12:59
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class DataStore2 {
    private KeyStore keyStore;
    private SharedPreferences mSharedPreferences;
    private static final String alias = "aIOxcDSF2W35";
    private Context mContext;

    @SuppressLint("StaticFieldLeak")
    private static DataStore2 instance = null;

    private DataStore2() {

    }

    public void init(Context context) {
        mContext = context;
        mSharedPreferences = context.getSharedPreferences("secret", Context.MODE_PRIVATE);
    }

    public static DataStore2 getInstance() {
        if (instance == null) {
            synchronized (DataStore2.class) {
                instance = new DataStore2();
            }
        }
        return instance;
    }

    public void save(String key, String value) throws Exception {
//        mSharedPreferences.edit().putString(key, encryptString(value, alias)).apply();
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public void save(String key, int value) throws Exception {
//        mSharedPreferences.edit().putString(key, encryptString(value, alias)).apply();
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public int getInt(String key){
        return mSharedPreferences.getInt(key, 0);
    }

    public String getData(String key){
        //        try {
//            result = decryptString(result, alias);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return mSharedPreferences.getString(key, null);
    }

    public boolean hasData(String key){
        if (mSharedPreferences.getString(key,null)!=null){
            return true;
        }
        return false;
    }

    public void clear(){
        mSharedPreferences.edit().clear().apply();
    }

    private void initKeyStore(Context context, String alias) throws Exception {
        keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        createNewKeys(context, alias);
    }

    private void createNewKeys(Context context, String alias) throws Exception {
        if (!"".equals(alias)) {
                // Create new key if needed
                if (!keyStore.containsAlias(alias)) {
                    Calendar start = Calendar.getInstance();
                    Calendar end = Calendar.getInstance();
                    end.add(Calendar.YEAR, 1);
                    KeyPairGeneratorSpec spec = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        spec = new KeyPairGeneratorSpec.Builder(context)
                                .setAlias(alias)
                                .setSubject(new X500Principal("CN=Sample Name, O=Android Authority"))
                                .setSerialNumber(BigInteger.ONE)
                                .setStartDate(start.getTime())
                                .setEndDate(end.getTime())
                                .build();
                    }
                    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        generator.initialize(spec);
                    }

                    KeyPair keyPair = generator.generateKeyPair();
                }
        }


    }


    /**
     * 加密方法
     *
     * @param needEncryptWord 　需要加密的字符串
     * @param alias           　加密秘钥
     * @return
     */
    public String encryptString(String needEncryptWord, String alias) throws Exception {
        if (!"".equals(alias) && !"".equals(needEncryptWord)) {
            initKeyStore(mContext, alias);
            String encryptStr = "";
            byte[] vals = null;
            try {
                KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, null);
                if (needEncryptWord.isEmpty()) {
                    return encryptStr;
                }

                Cipher inCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                inCipher.init(Cipher.ENCRYPT_MODE, privateKeyEntry.getCertificate().getPublicKey());

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                CipherOutputStream cipherOutputStream = new CipherOutputStream(
                        outputStream, inCipher);
                cipherOutputStream.write(needEncryptWord.getBytes("UTF-8"));
                cipherOutputStream.close();

                vals = outputStream.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Base64.encodeToString(vals, Base64.DEFAULT);
        }
        return "";
    }


    public String decryptString(String needDecryptWord, String alias) throws Exception {
        if (!"".equals(alias) && !"".equals(needDecryptWord)) {
            initKeyStore(mContext, alias);
            String decryptStr = "";

                KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, null);

                Cipher output = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                output.init(Cipher.DECRYPT_MODE, privateKeyEntry.getPrivateKey());
                CipherInputStream cipherInputStream = new CipherInputStream(
                        new ByteArrayInputStream(Base64.decode(needDecryptWord, Base64.DEFAULT)), output);
                ArrayList<Byte> values = new ArrayList<>();
                int nextByte;
                while ((nextByte = cipherInputStream.read()) != -1) {
                    values.add((byte) nextByte);
                }

                byte[] bytes = new byte[values.size()];
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = values.get(i).byteValue();
                }

                decryptStr = new String(bytes, 0, bytes.length, "UTF-8");

            return decryptStr;
        }
        return "";
    }
}
