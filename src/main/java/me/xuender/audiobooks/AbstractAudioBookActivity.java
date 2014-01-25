/**
 *
 */
package me.xuender.audiobooks;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * @author ender
 */
public abstract class AbstractAudioBookActivity extends Activity {
    private ExitHandler exitHandler = new ExitHandler();
    private Player player;
    private AdRes adRes;

    private void doExit() {
        if (exitHandler.isExit()) {
            quit();
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
        ScrollTextView stv = (ScrollTextView) findViewById(adRes.getScrollId());
        stv.setResIds(adRes);
        player = new Player(this, adRes.getRawId(), findViewById(adRes.getMainViewId()));
        player.start();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        new PositionRunnable(player, stv).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        quit();
    }

    private void quit() {
        player.stop();
        finish();
        System.exit(0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            doExit();
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