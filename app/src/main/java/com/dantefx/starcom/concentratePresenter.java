package com.dantefx.starcom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dantefx.starcom.databinding.ConcentrateViewBinding;

public class concentratePresenter extends Fragment {

    private ConcentrateViewBinding binding;
    ToneGenerator tg = new ToneGenerator();


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = ConcentrateViewBinding.inflate(inflater, container, false);
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

            tg.playPulse(123.47,23,40000);
            binding.buttonSecond.setImageResource(android.R.drawable.ic_media_pause);
        }
    }

}