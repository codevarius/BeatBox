package com.kshv.example.beatbox;

public class Sound {
    private String mAssetPath, mName;
    private Integer mSoundId;
    private float rate = 1;
    private int mPosInRecyclerViewList;

    Sound(String assetPath){
        mAssetPath = assetPath;
        String[] components = assetPath.split ("/");
        String filename = components[components.length - 1];
        mName = filename.replace (".wav","");
    }

    String getAssetPath() {
        return mAssetPath;
    }

    String getName() {
        return mName;
    }

    float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    Integer getSoundId() {
        return mSoundId;
    }

    void setSoundId(Integer mSoundId) {
        this.mSoundId = mSoundId;
    }

    public void setPositionInRecyclerViewList(int position) {
        mPosInRecyclerViewList = position;
    }

    int getPosInRecyclerViewList() {
        return mPosInRecyclerViewList;
    }
}
