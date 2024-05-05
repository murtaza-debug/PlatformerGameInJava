package Audios;

import Loader.Load;

import javax.sound.sampled.Clip;

public class LoadAudio {

    private final int music_max = 4;
    private final int action_max = 5;
    private String BACKGROUND_AUDIO = "Main3.wav" ;
    private String HEART_BEAT = "Default.wav";
    private String MENU_MUSIC = "Menu.wav";
    private String Terror = "Terror.wav";

    private String ATTACK = "Slash.wav";
    private String RUNNING = "Running.wav";
    private String Skull = "Skull.wav";
    private String Fire = "Fire.wav";
    private String JUMP = "Jump.wav";

    protected Clip music[];
    protected Clip action[];

    protected LoadAudio ()
    {
        music = new Clip[music_max];
        action = new Clip[action_max];
        loadMusic();
        loadActions();
    }

    private void loadMusic()
    {
        music[0] = Load.Audio(BACKGROUND_AUDIO);
        music[1] = Load.Audio(HEART_BEAT);
        music[2] = Load.Audio(MENU_MUSIC);
        music[3] = Load.Audio(Terror);
    }

    private void loadActions ()
    {
        action[0] = Load.Audio(ATTACK);
        action[1] = Load.Audio(RUNNING);
        action[2] = Load.Audio(Skull);
        action[3] = Load.Audio(Fire);
        action[4] = Load.Audio(JUMP);
    }
}
