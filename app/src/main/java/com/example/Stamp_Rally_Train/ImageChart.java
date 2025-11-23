package com.example.Stamp_Rally_Train;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class ImageChart extends BarChartRenderer {

    private final Bitmap mBitmap;
    private final RectF mBarShadowRectBuffer = new RectF();

    public ImageChart(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler, Bitmap bitmap) {
        super(chart, animator, viewPortHandler);
        this.mBitmap = bitmap;
    }

    @Override
    public void drawDataSet(Canvas c, IBarDataSet dataSet, int index) {
        Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());

        this.mBarBorderPaint.setColor(dataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(com.github.mikephil.charting.utils.Utils.convertDpToPixel(dataSet.getBarBorderWidth()));

        final float phaseX = mAnimator.getPhaseX();
        final float phaseY = mAnimator.getPhaseY();

        if (mBarBuffers != null) {
            BarBuffer buffer = mBarBuffers[index];
            buffer.setPhases(phaseX, phaseY);
            buffer.setDataSet(index);
            buffer.setInverted(mChart.isInverted(dataSet.getAxisDependency()));
            buffer.setBarWidth(mChart.getBarData().getBarWidth());

            buffer.feed(dataSet);
            trans.pointValuesToPixel(buffer.buffer);

            for (int j = 0; j < buffer.buffer.length * mAnimator.getPhaseX(); j += 4) {
                float left = buffer.buffer[j];
                float top = buffer.buffer[j + 1];
                float right = buffer.buffer[j + 2];
                float bottom = buffer.buffer[j + 3];

                mBarShadowRectBuffer.set(left, top, right, bottom);
                if (mViewPortHandler.isInBoundsLeft(mBarShadowRectBuffer.right)) {
                    if (!mViewPortHandler.isInBoundsRight(mBarShadowRectBuffer.left))
                        break;
                    if (mBitmap != null) {
                        c.drawBitmap(mBitmap, null, mBarShadowRectBuffer, null);
                    }
                }
            }
        }
    }
}