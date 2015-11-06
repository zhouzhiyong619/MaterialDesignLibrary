package com.gc.materialdesign.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.gc.materialdesign.R;
import com.gc.materialdesign.utils.Utils;

/**
 * Created by zhouzhiyong on 15-11-6.
 */
public class CheckView extends CustomView {

    final static String ANDROIDXML = "http://schemas.android.com/apk/res/android";
    private int srcId;
    private boolean isChecked;
    private Bitmap mBitmap;
    private int imgHeight = 40;
    private Rect srcRect;
    private Rect dstRect;
    private int widthOffest;
    private Paint srcPaint;
    private int drawHeight;

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs);
    }

    protected void setAttributes(AttributeSet attrs) {
        // Set size of view
        setMinimumHeight(Utils.dpToPx(48, getResources()));
        setMinimumWidth(Utils.dpToPx(48, getResources()));
        srcId = attrs.getAttributeResourceValue(ANDROIDXML, "src", R.drawable.sprite_check);
        mBitmap = BitmapFactory.decodeResource(getResources(), srcId);
//        mBitmap = Bitmap.createBitmap(15 * this.imgHeight, this.imgHeight, Bitmap.Config.ARGB_8888);
//        Rect localRect = new Rect(0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight());
//        srcPaint = new Paint();
//        srcPaint.setAntiAlias(true);
//        new Canvas(this.mBitmap).drawBitmap(srcBitmap, null, localRect, srcPaint);
        srcRect = new Rect();
        dstRect = new Rect();
        widthOffest = 0;
        setBackgroundColor(Color.TRANSPARENT);
        drawHeight = Utils.dpToPx(20, getResources());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSrc(canvas);
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        widthOffest = isChecked ? 0 : 14;
        invalidate();
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setCheckedNoAnim(boolean checked) {
        isChecked = checked;
        widthOffest = isChecked ? 14 : 0;
        invalidate();
    }

    private void drawSrc(Canvas canvas) {
        canvas.save();
        srcRect = new Rect(imgHeight * widthOffest, 0, imgHeight * widthOffest + imgHeight, imgHeight);
        //dstRect = new Rect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        //dstRect = new Rect(0, 0,getWidth(), getHeight());
        dstRect = new Rect((getWidth() - drawHeight) / 2, (getHeight() - drawHeight) / 2,
                (getWidth() + drawHeight) / 2, (getHeight() + drawHeight) / 2);
        canvas.drawBitmap(mBitmap, srcRect, dstRect, srcPaint);
        if (isChecked && widthOffest < 14) {
            widthOffest++;
            postInvalidateDelayed(10);
        } else if (!isChecked && widthOffest > 0) {
            widthOffest--;
            postInvalidateDelayed(10);
        }

        canvas.restore();
    }
}
