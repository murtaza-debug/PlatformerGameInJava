package Audios;

import javax.sound.sampled.Clip;

import static Audios.AudioConstants.*;
import static GameStates.StateConstants.*;
import static MAINGAME.Panel.currentState;

public class Audio {


    LoadAudio audio;

    public Audio ()
    {
        audio = new LoadAudio();
    }

    public void playAction(int action)
    {
        if (!audio.action[action].isActive())
            audio.action[action].setMicrosecondPosition(0);
        audio.action[action].start();

    }

    public void playMusic ()
    {
        if (currentState == PLAYING) {
            audio.music[BACKGROUND].start();
            audio.music[BACKGROUND].loop(Clip.LOOP_CONTINUOUSLY);
            audio.music[HEART_BEAT].start();
            audio.music[HEART_BEAT].loop(Clip.LOOP_CONTINUOUSLY);
            audio.action[SKULL].start();
            audio.action[SKULL].loop(Clip.LOOP_CONTINUOUSLY);
        }
        else
        {
            audio.music[BACKGROUND].stop();
            audio.music[HEART_BEAT].stop();
        }
    }
}
