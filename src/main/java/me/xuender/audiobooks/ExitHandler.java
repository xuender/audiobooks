/**
 * 
 */
package me.xuender.audiobooks;

import android.os.Handler;
import android.os.Message;

/**
 * @author ender
 * 
 */
public class ExitHandler extends Handler {
	private boolean exit = false;

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		exit = false;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}
}
