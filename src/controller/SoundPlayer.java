package controller;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundPlayer {
	// instance variables
	private final String musicFile = "game_music.wav";
	private final String slashFile = "slash.wav";
	private Clip musicClip;

	public void playMusic() {
		musicClip = playSound(musicFile, true);
	}

	public void playSlash() {
		playSound(slashFile, false);
	}

	private Clip playSound(String soundFile, boolean shouldLoop) {
		//define the resource location and loop the game sound
		Clip clip = null;
		try {
			URL sound = getClass().getResource("/sounds/" + soundFile);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			if (shouldLoop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clip;
	}

	public void stopMusic() {
		//stop the sound
		if (musicClip != null) {
			musicClip.stop();
		}
	}

}
