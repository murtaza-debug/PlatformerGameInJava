package Audios;

import Loader.Load;

import javax.sound.sampled.Clip;

public class LoadAudio {

    private final int music_max = 2;
    private final int action_max = 2;
    private String BACKGROUND_AUDIO = "Main2.wav" ;
    private String HEART_BEAT = "Default.wav";

    private String ATTACK = "Slash.wav";
    private String RUNNING = "Running.wav";

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
    }

    private void loadActions ()
    {
        action[0] = Load.Audio(ATTACK);
        action[1] = Load.Audio(RUNNING);
    }
}
