package com.dantefx.starcom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dantefx.starcom.databinding.RelaxmodeViewBinding;

public class relaxModePresenter extends Fragment {

    private RelaxmodeViewBinding binding;
    ToneGenerator toneGenerator = new ToneGenerator();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = RelaxmodeViewBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.sleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(relaxModePresenter.this)
                        .navigate(R.id.relaxModeToSleep);
            }
        });
        binding.relaxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(relaxModePresenter.this)
                        .navigate(R.id.relaxModeToRelax);
            }
        });
        binding.studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(relaxModePresenter.this)
                        .navigate(R.id.relaxModeToConcentrate);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}