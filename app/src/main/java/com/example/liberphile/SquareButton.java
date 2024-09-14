package com.example.liberphile;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatButton;

public class SquareButton extends AppCompatButton {

    public SquareButton(Context context) {
        super(context);
        init();
    }

    public SquareButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SquareButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(Color.parseColor("#4A4A4A")); // Background color
        drawable.setCornerRadius(12); // Rounded corners

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            setElevation(8); // Shadow elevation for API 21+
        }

        setBackground(drawable);
        setTextColor(Color.WHITE);
        setTextSize(18);
        setGravity(Gravity.CENTER);
        setPadding(10, 10, 10, 10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
