/**
 * 
 */
package me.xuender.audiobooks;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author ender
 * 
 */
public class ScrollTextView extends TextView {
	private List<ScrollText> scrollTexts = new ArrayList<ScrollText>();
	private Paint currentPaint;
	private Paint otherPaint;
	private float width;
	private float height;
	private float textSize = 18;
	private float textHeight = 25;
	private int index = 0;
	private float move = 0;

	public ScrollTextView(Context context) {
		super(context);
		init();
	}

	public ScrollTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ScrollTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		setFocusable(true); // 设置可对焦
		// 高亮部分
		currentPaint = new Paint();
		currentPaint.setAntiAlias(true); // 设置抗锯齿，让文字美观饱满
		currentPaint.setTextAlign(Paint.Align.CENTER);// 设置文本对齐方式

		// 非高亮部分
		otherPaint = new Paint();
		otherPaint.setAntiAlias(true);
		otherPaint.setTextAlign(Paint.Align.CENTER);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (canvas == null) {
			return;
		}

		currentPaint.setColor(Color.argb(210, 29, 248, 240));
		otherPaint.setColor(Color.argb(140, 0, 0, 0));

		currentPaint.setTextSize(24);
		currentPaint.setTypeface(Typeface.SERIF);

		otherPaint.setTextSize(textSize);
		otherPaint.setTypeface(Typeface.DEFAULT);

		setText("");
		canvas.drawText(scrollTexts.get(index).getString(), width / 2, height
				/ 2 - move, currentPaint);

		float tempY = height / 2;
		// 画出本句之前的句子
		for (int i = index - 1; i >= 0; i--) {
			// 向上推移
			tempY = tempY - textHeight;
			canvas.drawText(scrollTexts.get(i).getString(), width / 2, tempY
					- move, otherPaint);
		}
		tempY = height / 2;
		// 画出本句之后的句子
		for (int i = index + 1; i < scrollTexts.size(); i++) {
			// 往下推移
			tempY = tempY + textHeight;
			canvas.drawText(scrollTexts.get(i).getString(), width / 2, tempY
					- move, otherPaint);
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		this.width = w;
		this.height = h;
	}

	public void setPosition(int position) {
		for (int i = 0; i < scrollTexts.size() - 1; i++) {
			if (position >= scrollTexts.get(i).getIndex()
					&& position < scrollTexts.get(i + 1).getIndex()) {
				index = i;
				break;
			}
		}
		int o = scrollTexts.get(index).getIndex();
		int n = scrollTexts.get(index + 1).getIndex();
		move = (float) (position - o) / (float) (n - o) * (float) textHeight;
	}

	public void setResIds(ResIds resIds) {
		Resources res = getResources();
		String[] text = res.getStringArray(resIds.getScrollText());
		scrollTexts.clear();
		for (String s : text) {
			ScrollText st = new ScrollText();
			int i = s.indexOf(";");
			if (i > 0) {
				st.setIndex(Integer.valueOf(s.substring(0, i)));
				st.setString(s.substring(i + 1));
			} else {
				st.setString(s);
			}
			scrollTexts.add(st);
		}
	}
}