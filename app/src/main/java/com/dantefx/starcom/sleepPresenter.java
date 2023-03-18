package com.dantefx.starcom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dantefx.starcom.databinding.SleepViewBinding;

public class sleepPresenter extends Fragment {

    private SleepViewBinding binding;
    ToneGenerator tg = new ToneGenerator();


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = SleepViewBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                isPlaying();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onResume() {
        super.onResume();
    }


    public void isPlaying(){
        if (tg.isPlaying) {
            tg.stopPulse();
            binding.buttonSecond.setImageResource(android.R.drawable.ic_media_play);
        }else{

            tg.playPulse(98,2,10000);
            binding.buttonSecond.setImageResource(android.R.drawable.ic_media_pause);
        }
    }

}