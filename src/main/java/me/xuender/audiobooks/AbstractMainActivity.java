/**
 * 
 */
package me.xuender.audiobooks;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * @author ender
 * 
 */
public abstract class AbstractMainActivity extends Activity {
	private ExitHandler exitHandler = new ExitHandler();
	private Player player;
	private AdRes adRes;

	private void exit() {
		if (exitHandler.isExit()) {
			finish();
			System.exit(0);
			return;
		}
		exitHandler.setExit(true);
		Toast.makeText(getApplicationContext(), adRes.getExitMsgId(),
				Toast.LENGTH_SHORT).show();
		exitHandler.sendEmptyMessageDelayed(0, 2000);
	}

	protected abstract AdRes getAdRes();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adRes = getAdRes();
		setContentView(adRes.getLayoutId());
		player = new Player(this, adRes.getRawId(), findViewById(adRes.getMainViewId()));
		player.start();
		ScrollTextView stv = (ScrollTextView) findViewById(adRes.getScrollId());
		stv.setResIds(adRes);
		new PositionRunnable(player, stv).start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		player.stop();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		player.show();
		return false;
	}
}