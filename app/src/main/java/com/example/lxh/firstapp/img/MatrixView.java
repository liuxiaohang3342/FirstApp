package com.example.lxh.firstapp.img;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lxh on 2018/8/14.
 */

public class MatrixView extends ImageView {

    private static final String TAG = MatrixView.class.getSimpleName();

    private final int NONE = 0;
    private final int DRAG = 1;
    private final int ZOOM = 2;
    private final int DOUBLE_CLICK = 3;

    private float mMaxScale = 6;
    private float mDoubleScale = 2;

    private int mMode = 0;

    private float mStartDis;
    private GestureDetector mGestureDetector;

    private Matrix mMatrix = new Matrix();
    private Matrix mInitMatrix = new Matrix();

    private PointF mPointF = new PointF();
    private PointF mScalePoint = new PointF();

    private float mImageWidth;
    private float mImageHeight;

    public MatrixView(Context context) {
        this(context, null, -1);
    }

    public MatrixView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(new TouchListener());
        mGestureDetector = new GestureDetector(context, new GestureListener());
        setBackgroundColor(Color.BLACK);
        setScaleType(ScaleType.FIT_CENTER);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        setInitMatrix();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        setInitMatrix();
    }

    private void setInitMatrix() {
        //设置完图片后，获取该图片的坐标变换矩阵
        mInitMatrix.set(getImageMatrix());
        float[] values = new float[9];
        mInitMatrix.getValues(values);
        mImageWidth = getWidth() / values[Matrix.MSCALE_X];
        mImageHeight = (getHeight() - values[Matrix.MTRANS_Y] * 2) / values[Matrix.MSCALE_Y];
    }

    private void setDragMatrix(MotionEvent event) {
        float dx = event.getX() - mPointF.x;
        float dy = event.getY() - mPointF.y;
        if (Math.sqrt(dx * dx + dy * dy) > 10) {
            mPointF.set(event.getX(), event.getY());
            mMatrix.set(getImageMatrix());
            float[] values = new float[9];
            mMatrix.getValues(values);
            float scale = 1 - dy / getHeight();
            if (scale * values[Matrix.MSCALE_X] < 1) {
                mMatrix.postScale(scale, scale, mPointF.x, mPointF.y);
                mMatrix.getValues(values);
                setBackgroundColor(Color.argb(getAlpha(values[Matrix.MSCALE_X]), 0, 0, 0));
            }
            mMatrix.postTranslate(dx, dy);
            setImageMatrix(mMatrix);
        }
    }

    private boolean outOfBounds() {
        float[] values = new float[9];
        mMatrix.getValues(values);
        if (values[Matrix.MTRANS_X] > 0 || mImageWidth * values[Matrix.MSCALE_X] + values[Matrix.MTRANS_X] <= getWidth()) {
            return true;
        }
        float translateY = values[Matrix.MTRANS_Y];
        mInitMatrix.getValues(values);
        if (translateY > values[Matrix.MTRANS_Y] || mImageHeight * values[Matrix.MSCALE_Y] + values[Matrix.MTRANS_Y] + translateY <= getHeight()) {
            return true;
        }
        return false;
    }


    private boolean isZoomChanged() {
        float[] values = new float[9];
        getImageMatrix().getValues(values);
        float scale = values[Matrix.MSCALE_X];
        mInitMatrix.getValues(values);
        return scale != values[Matrix.MSCALE_X];
    }

    private float distance(MotionEvent event) {
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    private void setZoomMatrix(MotionEvent event) {
        if (event.getPointerCount() < 2) {
            return;
        }
        float enDis = distance(event);
        if (enDis > 10) {
            float scale = enDis / mStartDis;
            mMatrix.set(getImageMatrix());
            float[] values = new float[9];
            mMatrix.getValues(values);
            scale = checkMaxScale(scale, values);
            checkScalePoint(event);
            mMatrix.postScale(scale, scale, mScalePoint.x, mScalePoint.y);
            mStartDis = enDis;
            mMatrix.getValues(values);
            setBackgroundColor(Color.argb(getAlpha(values[Matrix.MSCALE_X]), 0, 0, 0));
            setImageMatrix(mMatrix);
        }
    }

    private int getAlpha(float scale) {
        int alpha = (int) (scale * 255);
        if (alpha < 0) {
            alpha = 0;
        } else if (alpha > 255) {
            alpha = 255;
        }
        return alpha;
    }

    private void checkScalePoint(MotionEvent event) {
        float px = (mScalePoint.x + event.getX()) / 2;
        float py = (mScalePoint.y + event.getY()) / 2;
        mScalePoint.set(px, py);
    }

    private float checkMaxScale(float scale, float[] valus) {
        if (scale * valus[Matrix.MSCALE_X] > mMaxScale) {
            scale = mMaxScale / valus[Matrix.MSCALE_X];
        }
        return scale;
    }

    private void resetMatrix() {
        if (isMinScale()) {
            mMatrix.set(mInitMatrix);
            setBackgroundColor(Color.BLACK);
            setImageMatrix(mMatrix);
        } else if (outOfBounds()) {
            resetInsideBounds();
        }
    }

    private void resetInsideBounds() {
        float[] values = new float[9];
        mMatrix.getValues(values);
        float dx = 0;
        if (values[Matrix.MTRANS_X] > 0) {
            dx = -values[Matrix.MTRANS_X];
        } else if (mImageWidth * values[Matrix.MSCALE_X] + values[Matrix.MTRANS_X] <= getWidth()) {
            dx = getWidth() - (mImageWidth * values[Matrix.MSCALE_X] + values[Matrix.MTRANS_X]);
        }
        float dy = 0;
        if (isZoomChanged()) {
            if (values[Matrix.MTRANS_Y] > 0) {
                dy = -values[Matrix.MTRANS_Y];
            } else if (mImageHeight * values[Matrix.MSCALE_Y] + values[Matrix.MTRANS_Y] <= getHeight()) {
                dy = getHeight() - (mImageHeight * values[Matrix.MSCALE_Y] + values[Matrix.MTRANS_Y]);
            }
        } else {
            float transY = values[Matrix.MTRANS_Y];
            mInitMatrix.getValues(values);
            if (transY > values[Matrix.MTRANS_Y]) {
                dy = values[Matrix.MTRANS_Y] - transY;
            } else if (mImageHeight + values[Matrix.MTRANS_Y] + transY <= getHeight()) {
                dy = getHeight() - (mImageHeight + values[Matrix.MTRANS_Y] + transY);
            }
        }
        if (dx == 0 && dy == 0) {
            return;
        }
        mMatrix.postTranslate(dx, dy);
        setImageMatrix(mMatrix);
    }

    private boolean isMinScale() {
        float[] values = new float[9];
        getImageMatrix().getValues(values);
        float scale = values[Matrix.MSCALE_X];
        mInitMatrix.getValues(values);
        return scale < values[Matrix.MSCALE_X];
    }

    private void isMatrixEnable() {
        if (getScaleType() != ScaleType.CENTER) {
            setScaleType(ScaleType.MATRIX);
        } else {
            mMode = NONE;
        }
    }

    public void onDoubleClick(MotionEvent e) {
        float scale = isZoomChanged() ? 1 : mDoubleScale;
        mMatrix.set(mInitMatrix);
        mMatrix.postScale(scale, scale, e.getX(), e.getY());
        setImageMatrix(mMatrix);
    }


    class TouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    mMode = DRAG;
                    mPointF.set(event.getX(), event.getY());
                    isMatrixEnable();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (isZoomChanged()) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    if (mMode == ZOOM) {
                        setZoomMatrix(event);
                    } else if (mMode == DRAG) {
                        setDragMatrix(event);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    resetMatrix();
                    outOfBounds();
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (mMode == NONE) {
                        return true;
                    }
                    if (isMinScale()) {
                        return true;
                    }
                    mMode = ZOOM;
                    mScalePoint.set(event.getX(), event.getY());
                    mStartDis = distance(event);
                    break;
            }
            return mGestureDetector.onTouchEvent(event);
        }
    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            onDoubleClick(e);
            mMode = DOUBLE_CLICK;
            return true;
        }
    }

}
