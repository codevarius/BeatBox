package com.kshv.example.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    public static final String TAG = "BeatBox";
    public static final String SOUND_FOLDER = "sample_sounds";
    public static final int MAX_SOUNDS = 5;
    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<> ();
    private SoundPool mSoundPool;

    public BeatBox(Context context) {
        mAssets = context.getAssets ();
        mSoundPool = new SoundPool (MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds ();
    }

    void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list (SOUND_FOLDER);
            Log.i (TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException e) {
            soundNames = new String[0];
            Log.e (TAG, "Could not list assets " + e);
        }

        for (String filename : soundNames) {
            try {
                String assetPath = SOUND_FOLDER + "/" + filename;
                Sound sound = new Sound (assetPath);
                loadSound (sound);
                mSounds.add (sound);
            } catch (IOException e) {
                Log.e (TAG, "Could not load sound " + filename, e);
            }

        }
    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
        Log.i (TAG,"sound "+sound.getName ()+" played");
    }

    private void loadSound(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd (sound.getAssetPath ());
        int soundId = mSoundPool.load (afd, 1);
        sound.setSoundId (soundId);
    }

    public void release(){
        mSoundPool.release();
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
