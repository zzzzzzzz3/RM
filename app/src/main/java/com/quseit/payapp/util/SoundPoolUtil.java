package com.quseit.payapp.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.quseit.payapp.R;


/**
 * 文 件 名: SoundPoolUtil
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/30 22:07
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class SoundPoolUtil {

    private static SoundPoolUtil instance = null;
    private SoundPool mSoundPool;
    private float volume;

    @SuppressWarnings("deprecation")
    private SoundPoolUtil() {
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
    }

    public void init(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volume = currentVolume / (float) maxVolume;
        Log.d("sound", "max:" + maxVolume + " current:" + currentVolume);
        mSoundPool.load(context, R.raw.error, 1);
        mSoundPool.load(context, R.raw.right, 1);
        mSoundPool.load(context, R.raw.change_mail, 1);
        mSoundPool.load(context, R.raw.idcard_error, 1);
    }

    public static SoundPoolUtil getInstance() {
        if (instance == null) {
            synchronized (SoundPoolUtil.class) {
                instance = new SoundPoolUtil();
            }
        }
        return instance;
    }

    public void play(SoundType soundType) {
        int id = 0;
        switch (soundType) {
            case ERROR:
                id = 1;
                break;
            case SUCCESS:
                id = 2;
                break;
            case CHANGE_MAIL:
                id = 3;
                break;
            case IDCARD_ERROR:
                id = 4;
                break;
        }
        int code = mSoundPool.play(id, volume, volume, 1, 0, 1);
        Log.d("sound", code + "");
    }

    public enum SoundType {
        ERROR, SUCCESS, CHANGE_MAIL, IDCARD_ERROR
    }
}
