package com.kshv.example.beatbox;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class SoundViewModel extends BaseObservable {
    private Sound mSound;
    private BeatBox mBeatBox;

    public SoundViewModel(BeatBox beatBox){
        mBeatBox = beatBox;
    }

    public Sound getSound() {
        return mSound;
    }

    public void setSound(Sound mSound) {
        this.mSound = mSound;
        notifyChange ();
    }

    @Bindable
    public String getTitle(){
        return mSound.getName ();
    }

    public void onButtonClicked() {
        mBeatBox.updateRateBar((int)(mSound.getRate () * 100));
        mBeatBox.play (mSound);
    }
}
