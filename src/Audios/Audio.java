package Audios;

import javax.sound.sampled.Clip;

import java.util.Random;

import static Audios.AudioConstants.*;
import static Entities.Player.inAir;
import static Entities.Player.jump_delay;
import static GameStates.StateConstants.*;
import static MAINGAME.Panel.currentState;

public class Audio {


    LoadAudio audio;
    Random rand ;
    int r = 0;

    public Audio ()
    {
        audio = new LoadAudio();
        rand = new Random();
    }

    public void playAction(int action)
    {



        if (!audio.action[action].isActive())
            audio.action[action].setMicrosecondPosition(0);

        if (action != JUMPING)
            audio.action[action].start();


        if (action == JUMPING)
        {
            jump_delay++;
            if (jump_delay >= 15)
            {
                jump_delay = 0;
                audio.action[action].start();
            }
        }

        if (inAir && action == RUNNING) audio.action[action].stop();
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
        if (currentState == MENU)
        {
            audio.music[MENU_AUDIO].start();
            audio.music[MENU_AUDIO].loop(Clip.LOOP_CONTINUOUSLY);
        }
        else
        {
            audio.music[MENU_AUDIO].stop();
        }

    }
}
