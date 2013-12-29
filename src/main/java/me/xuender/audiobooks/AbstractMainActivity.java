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
	private ResIds ids;

	private void exit() {
		if (exitHandler.isExit()) {
			finish();
			System.exit(0);
			return;
		}
		exitHandler.setExit(true);
		Toast.makeText(getApplicationContext(), ids.getExitMsg(),
				Toast.LENGTH_SHORT).show();
		exitHandler.sendEmptyMessageDelayed(0, 2000);
	}

	protected abstract ResIds getResIds();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ids = getResIds();
		setContentView(ids.getLayout());
		player = new Player(this, ids.getRaw(), findViewById(ids.getMainView()));
		player.start();
		ScrollTextView stv = (ScrollTextView) findViewById(ids.getScroll());
		stv.setResIds(ids);
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