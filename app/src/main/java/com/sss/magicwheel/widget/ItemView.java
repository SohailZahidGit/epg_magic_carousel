package com.sss.magicwheel.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.sss.magicwheel.entity.CoordinatesHolder;
import com.sss.magicwheel.entity.CustomRect;
import com.sss.magicwheel.entity.LinearClipData;
import com.sss.magicwheel.util.MagicCalculationHelper;

/**
 * @author Alexey Kovalev
 * @since 05.11.2015.
 */
public class ItemView extends ImageView {

    private static final String TAG = ItemView.class.getCanonicalName();

    private final Paint paint;
    private final MagicCalculationHelper calculationHelper;
    private Path path;
    private LinearClipData linearClipData;


    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        calculationHelper = MagicCalculationHelper.getInstance();

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setColor(Color.RED);

        path = new Path();
    }


    public void setClipArea(LinearClipData linearClipData) {
        this.linearClipData = linearClipData;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (linearClipData == null) {
            super.onDraw(canvas);
            return;
        }

        Path pathToClip = createPathForClip(linearClipData, canvas);
        canvas.clipPath(pathToClip);

        super.onDraw(canvas);
    }


    private Path createPathForClip(LinearClipData clipData, Canvas canvas) {
        path.reset();

        CoordinatesHolder first = linearClipData.getFirst();
        CoordinatesHolder second = linearClipData.getSecond();
        CoordinatesHolder third = linearClipData.getThird();
        CoordinatesHolder four = linearClipData.getFourth();

        path.moveTo(first.getXAsFloat(), first.getYAsFloat());
        path.lineTo(second.getXAsFloat(), second.getYAsFloat());
        path.lineTo(four.getXAsFloat(), four.getYAsFloat());
        path.lineTo(third.getXAsFloat(), third.getYAsFloat());
        path.close();

        return path;

    }

}
