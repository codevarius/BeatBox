package com.kshv.example.beatbox;

import androidx.fragment.app.Fragment;

import com.kshv.example.beatbox.fragments.BeatBoxFragment;

public class BeatBoxActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }
}
