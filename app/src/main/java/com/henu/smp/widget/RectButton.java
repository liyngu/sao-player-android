package com.henu.smp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.henu.smp.R;
import com.henu.smp.base.BaseButton;
import com.henu.smp.util.WidgetUtil;

/**
 * Created by leen on 10/14/15.
 */
public class RectButton extends BaseButton {
    public RectButton(Context context) {
        super(context, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setBackgroundResource(R.drawable.default_rect_btn);
    }

    public RectButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String text = this.getText();
        Paint paint = new Paint();
        paint.setTextSize(40);
        int offsetX = WidgetUtil.getResourceDimen(R.dimen.rect_button_text_offset_x);
        int offsetY = WidgetUtil.getResourceDimen(R.dimen.rect_button_text_offset_y);
        canvas.drawText(text, offsetX, offsetY, paint);
        super.onDraw(canvas);
    }
}