package com.dantefx.starcom;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class ToneGenerator {
    private AudioTrack audioTrack;
    public Boolean isPlaying = false;
    public void playPulse(int crossFreq, int pulseFreq, final int duration) {
        float volume1 = 0.5f;
        float volume2 = 0.3f;
        float chorusAmount = 0.8f;
        int sampleRate = 44100; // Frecuencia de muestreo de 44100 Hz
        int numSamples = duration * sampleRate / 1000;
        int bufferSize = numSamples * 2;
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        AudioFormat format = new AudioFormat.Builder()
                .setSampleRate(sampleRate)
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                .build();

        short[] buffer1 = new short[numSamples];
        short[] buffer2 = new short[numSamples];
        short[] buffer3 = new short[numSamples];

        double angularFrequency1 = 2 * Math.PI * crossFreq / sampleRate;
        double angularFrequency2 = 2 * Math.PI * pulseFreq / sampleRate;
        double angle1 = angularFrequency1 * numSamples / 4;
        double angle2 = angularFrequency2 * numSamples / 4; // Desfase de un cuarto de ciclo
        double amplitude1 = volume1 * Short.MAX_VALUE*-0.3;
        double amplitude2 = volume2 * Short.MAX_VALUE * 2.8; // Amplitud reducida

        // Generamos la señal original
        for (int i = 0; i < numSamples; i++) {
            buffer1[i] = (short) (amplitude1 * Math.sin(angle1) + amplitude2 * Math.sin(angle2));
            angle1 += angularFrequency1;
            angle2 += angularFrequency2;
        }

        // Generamos la versión con un retardo de 20 ms
        for (int i = 0; i < numSamples; i++) {
            if (i < sampleRate / 50) {
                buffer2[i] = buffer1[i];
            } else {
                buffer2[i] = (short) ((buffer1[i] + buffer1[i - sampleRate / 50]) / 2);
            }
        }

        // Generamos la versión con un retardo de 40 ms
        for (int i = 0; i < numSamples; i++) {
            if (i < sampleRate / 25) {
                buffer3[i] = buffer1[i];
            } else {
                buffer3[i] = (short) ((buffer1[i] + buffer1[i - sampleRate / 25]) / 2);
            }
        }

        // Sumamos las tres señales para crear el efecto chorus
        short[] buffer = new short[numSamples];
        for (int i = 0; i < numSamples; i++) {
            double chorusAmplitude = amplitude1 * chorusAmount;
            buffer[i] = (short) ((buffer1[i] * (1 - chorusAmount)) + (buffer2[i] * chorusAmount * 0.5) + (buffer3[i] * chorusAmount * 0.5));
        }

        audioTrack = new AudioTrack(attributes, format, bufferSize, AudioTrack.MODE_STREAM, AudioManager.AUDIO_SESSION_ID_GENERATE);
        audioTrack.setVolume(AudioTrack.getMaxVolume());
        audioTrack.play();
        audioTrack.write(buffer, 0, numSamples);
        isPlaying = true;
    }


    public void stopPulse() {
        audioTrack.stop();
        audioTrack.release();
        isPlaying = false;
    }
}
