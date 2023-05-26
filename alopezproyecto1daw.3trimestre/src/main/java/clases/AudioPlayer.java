package clases;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer {
    private MediaPlayer mediaPlayer;

    public void playAudio(String audioFilePath) {
        Media media = new Media(audioFilePath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void stopAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}