/**
 *
 */
package me.xuender.audiobooks;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.view.View;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

/**
 * audiobooks player
 *
 * @author ender
 */
public class Player implements OnPreparedListener, MediaPlayerControl {
    private static MediaPlayerControl mediaPlayerControl;
    private MediaController mediaController;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private View view;

    static MediaPlayerControl getMediaPlayerControl() {
        return mediaPlayerControl;
    }

    public Player(Context context, int resid, View view) {
        this.view = view;
        mediaController = new MediaController(context);
        mediaPlayer = MediaPlayer.create(context, resid);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setLooping(false);
        mediaPlayerControl = this;
        // start();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void show() {
        mediaController.show();
    }

    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public void onPrepared(MediaPlayer arg0) {
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(view);
        handler.post(new Runnable() {
            public void run() {
                mediaController.setEnabled(true);
                mediaController.show();
            }
        });
    }
}