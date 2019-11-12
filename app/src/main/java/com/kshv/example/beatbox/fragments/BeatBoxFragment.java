package com.kshv.example.beatbox.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kshv.example.beatbox.BeatBox;
import com.kshv.example.beatbox.R;
import com.kshv.example.beatbox.Sound;
import com.kshv.example.beatbox.SoundViewModel;
import com.kshv.example.beatbox.databinding.FragmentBeatBoxBinding;
import com.kshv.example.beatbox.databinding.ListItemSoundBinding;

import java.util.List;
import java.util.Objects;

public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;
    public static final String TAG = "BeatBoxFragment";

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment ();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setRetainInstance (true);
        mBeatBox = new BeatBox (Objects.requireNonNull (getContext ()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final FragmentBeatBoxBinding binding = DataBindingUtil.inflate (inflater,
                R.layout.fragment_beat_box, container, false);
        binding.recyclerView.setLayoutManager (new GridLayoutManager (getActivity (), 3));
        binding.recyclerView.setAdapter (new SoundAdapter (mBeatBox.getSounds ()));
        binding.rateBar.setMax (100);
        binding.rateBar.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener () {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.barDigits.setText ("Rate: " + (float) (seekBar.getProgress ()) / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                float newRate = (float) (seekBar.getProgress ()) / 100;
                mBeatBox.getSounds ().get (mBeatBox.getLastPlayedSoundPos ())
                        .setRate (newRate);
                binding.barDigits.setText ("Rate: " + newRate);
            }
        });
        mBeatBox.setRateBarController (binding.rateBar);

        return binding.getRoot ();
    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
        mBeatBox.release ();
    }

    private class SoundHolder extends RecyclerView.ViewHolder {

        private ListItemSoundBinding mBinding;

        SoundHolder(ListItemSoundBinding binding) {
            super (binding.getRoot ());
            mBinding = binding;
            mBinding.setViewModel (new SoundViewModel (mBeatBox));
        }

        void bind(Sound sound) {
            mBinding.getViewModel ().setSound (sound);
            mBinding.executePendingBindings ();
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
        private List<Sound> mSounds;

        SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from (getActivity ());
            ListItemSoundBinding binding = DataBindingUtil
                    .inflate (inflater, R.layout.list_item_sound, parent, false);
            return new SoundHolder (binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            Sound sound = mSounds.get (position);
            sound.setPositionInRecyclerViewList (position);
            holder.bind (sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size ();
        }
    }
}
