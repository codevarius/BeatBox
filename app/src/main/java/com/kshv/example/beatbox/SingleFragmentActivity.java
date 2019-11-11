package com.kshv.example.beatbox;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.beat_box_activity);
        Fragment fragment = createFragment ();
        getSupportFragmentManager ().beginTransaction ()
                .add (R.id.beat_box_activity_fragment_container,fragment)
                .commit ();
    }
}
