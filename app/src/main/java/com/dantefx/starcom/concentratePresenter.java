package com.dantefx.starcom;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dantefx.starcom.databinding.ConcentrateViewBinding;

public class concentratePresenter extends Fragment {

    private ConcentrateViewBinding binding;
    public MediaPlayer mp;
    private int num=0;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = ConcentrateViewBinding.inflate(inflater, container, false);
        mp = MediaPlayer.create(this.getContext(), R.raw.concentracion);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                num+=1;
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
        if (num % 2 == 0) {
            // tg.stopPulse();
            mp.stop();
            mp.seekTo(0);
            binding.buttonSecond.setImageResource(android.R.drawable.ic_media_play);
        }else{

            mp.start();;
            // tg.playPulse(98,2,40000);
            binding.buttonSecond.setImageResource(android.R.drawable.ic_media_pause);
        }
    }

}