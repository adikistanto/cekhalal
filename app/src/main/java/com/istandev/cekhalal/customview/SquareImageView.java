package com.istandev.cekhalal.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by ADIK on 19/04/2016.
 */
public class SquareImageView extends ImageView {

    public SquareImageView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

}
