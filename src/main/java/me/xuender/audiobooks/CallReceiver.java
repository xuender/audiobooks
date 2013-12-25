/**
 * 
 */
package me.xuender.audiobooks;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.MediaController.MediaPlayerControl;

/**
 * Call Receiver
 * 
 * @author ender
 * 
 */
public class CallReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		MediaPlayerControl mediaPlayerControl = Player.getMediaPlayerControl();
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Service.TELEPHONY_SERVICE);
		switch (tm.getCallState()) {
		case TelephonyManager.CALL_STATE_RINGING:
			mediaPlayerControl.pause();
			break;
		case TelephonyManager.CALL_STATE_IDLE:
			if (!mediaPlayerControl.isPlaying()) {
				mediaPlayerControl.start();
			}
		}
	}
}