package com.kshv.example.beatbox;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

public class SoundViewModelTest {
    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject;

    @Before
    public void setUp() throws Exception {
        mBeatBox = Mockito.mock (BeatBox.class);
        mSound = new Sound ("assetPath");
        mSubject = new SoundViewModel (mBeatBox);
        mSubject.setSound (mSound);
    }

    @Test
    public void exposesSoundNameAsTitle(){
        assertThat (mSubject.getTitle (), is(mSound.getName ()));
    }

    @Test
    public void callBeatBoxPlayOnButtonClicked(){
        mSubject.onButtonClicked();
        verify(mBeatBox).play(mSound);
    }
}