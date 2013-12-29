/**
 * 
 */
package me.xuender.audiobooks;

import android.os.Handler;

/**
 * @author ender
 * 
 */
public class PositionRunnable implements Runnable {
	private Player player;
	private ScrollTextView view;
	private Handler handler = new Handler();

	public PositionRunnable(Player player, ScrollTextView view) {
		this.player = player;
		this.view = view;

	}

	public void start() {
		handler.post(this);
	}

	@Override
	public void run() {
		if (player.isPlaying()) {
			view.setPosition(player.getCurrentPosition());
		}
		handler.postDelayed(this, 100);
	}
}