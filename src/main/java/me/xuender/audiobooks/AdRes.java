/**
 *
 */
package me.xuender.audiobooks;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author ender
 */
public class AdRes {
    private int exitMsgId;
    private int layoutId;
    private int rawId;
    private int mainViewId;
    private int scrollId;
    private int scrollTextId;
    private int currentColor = Color.argb(210, 29, 248, 240);
    private int otherColor = Color.argb(140, 0, 0, 0);
    private Paint.Align textAlign = Paint.Align.CENTER;

    public Paint.Align getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(Paint.Align textAlign) {
        this.textAlign = textAlign;
    }


    public int getOtherColor() {
        return otherColor;
    }

    public void setOtherColor(int otherColor) {
        this.otherColor = otherColor;
    }

    public int getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
    }

    public int getExitMsgId() {
        return exitMsgId;
    }

    public void setExitMsgId(int exitMsgId) {
        this.exitMsgId = exitMsgId;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public int getRawId() {
        return rawId;
    }

    public void setRawId(int rawId) {
        this.rawId = rawId;
    }

    public int getMainViewId() {
        return mainViewId;
    }

    public void setMainViewId(int mainViewId) {
        this.mainViewId = mainViewId;
    }

    public int getScrollId() {
        return scrollId;
    }

    public void setScrollId(int scrollId) {
        this.scrollId = scrollId;
    }

    public int getScrollTextId() {
        return scrollTextId;
    }

    public void setScrollTextId(int scrollTextId) {
        this.scrollTextId = scrollTextId;
    }
}