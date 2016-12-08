package com.example.test.anim.weight;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by Guo Shaobing on 2016/8/29.
 */
public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF mControlPoint;

    public BezierEvaluator(PointF controlPoint) {
        this.mControlPoint = controlPoint;
    }

    @Override
    public PointF evaluate(float t, PointF startValue, PointF endValue) {
        return BezierUtil.CalculateBezierPointForQuadratic(t, startValue, mControlPoint, endValue);
    }
}