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
        if (currentState == PLAYING) {
            if (action != STOP_ALL) {
                if (audio.action[action].getLongFramePosition() >= audio.action[action].getFrameLength())
                    audio.action[action].setMicrosecondPosition(0);
                audio.action[action].start();
            }

            for (int i = 0; i < audio.action.length; i++) {
                if (action != i)
                    audio.action[i].stop();
                if (action == STOP_ALL) {
                    audio.action[i].stop();
                }
            }
        }
        else
        {
            for (int i = 0; i < audio.action.length; i++) {
                audio.action[i].stop();
            }
        }
    }

    public void playMusic ()
    {
        if (currentState == PLAYING) {
            audio.music[BACKGROUND].start();
            audio.music[BACKGROUND].loop(Clip.LOOP_CONTINUOUSLY);
            audio.music[HEART_BEAT].start();
            audio.music[HEART_BEAT].loop(Clip.LOOP_CONTINUOUSLY);
        }
        else
        {
            audio.music[BACKGROUND].stop();
            audio.music[HEART_BEAT].stop();
        }
    }
}
