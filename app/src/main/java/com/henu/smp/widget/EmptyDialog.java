package com.henu.smp.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.henu.smp.R;

/**
 * Created by liyngu on 12/23/15.
 */
public class EmptyDialog extends FrameLayout implements SmpWidget {

    public EmptyDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获得inflater 对象
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //通过resource在container中填充组件
        inflater.inflate(R.layout.dialog_empty, this);

        setOnClickListener(null);
    }

    public void show() {
        Animator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 0f, 1f);
        Animator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 0f, 1f);
        AnimatorSet startAnimation = new AnimatorSet();
        startAnimation.setDuration(300);
        startAnimation.play(scaleXAnimator).with(scaleYAnimator);
        setVisibility(VISIBLE);
    }
}
