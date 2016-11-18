package com.example.test.charts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class HelloChartsActivity extends AppCompatActivity {

    private LineChartView chart;
    private LineChartData data;
    private Viewport viewport;
    private int left,right,bottom, top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_charts);
        chart = (LineChartView) findViewById(R.id.chart);
        View btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                right++;
                left++;
                viewport = new Viewport(left, top,right, bottom);
                chart.setCurrentViewport(viewport);
            }
        });

        List<PointValue> values = new ArrayList<>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 8));
        values.add(new PointValue(2, 5));
        values.add(new PointValue(3, 4));

        List<PointValue> values2 = new ArrayList<>();
        values2.add(new PointValue(0, 27));
        values2.add(new PointValue(1, 61));
        values2.add(new PointValue(2, 32));
        values2.add(new PointValue(3, 18));

        List<PointValue> values3 = new ArrayList<>();
        values3.add(new PointValue(0, 7));
        values3.add(new PointValue(1, 72));
        values3.add(new PointValue(2, 94));
        values3.add(new PointValue(3, 46));

        List<AxisValue> axisValues = new ArrayList<>();
        axisValues.add(new AxisValue(1).setLabel("一"));
        axisValues.add(new AxisValue(2).setLabel("二"));
        axisValues.add(new AxisValue(3).setLabel("三"));
        axisValues.add(new AxisValue(4).setLabel("四"));
        axisValues.add(new AxisValue(5).setLabel("五"));
        axisValues.add(new AxisValue(6).setLabel("六"));
        axisValues.add(new AxisValue(7).setLabel("七"));
        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.BLUE).setStrokeWidth(2).setFilled(true).setHasPoints(false).setCubic(true);
        Line line2 = new Line(values2).setColor(Color.BLACK).setStrokeWidth(2).setHasPoints(false).setCubic(true);
        Line line3 = new Line(values3).setColor(Color.GREEN).setStrokeWidth(2).setHasPoints(false).setCubic(true);
        List<Line> lines = new ArrayList<>();
        lines.add(line);
        lines.add(line2);
        lines.add(line3);

        data = new LineChartData();
        int color = Color.GRAY;
        data.setLines(lines);
        data.setAxisXBottom(new Axis()
                .setHasLines(true)
                .setTextColor(color)
                .setLineColor(color)
                .setMaxLabelChars(4));
        data.setAxisYLeft(new Axis()
                .setHasLines(true)
                .setTextColor(color)
                .setLineColor(color)
                .setMaxLabelChars(4));


        chart.setViewportCalculationEnabled(false);
        left = 0;
        right = 7;
        bottom = 0;
        top = 110;
        viewport = new Viewport(left, top,right, bottom);
        chart.setMaximumViewport(new Viewport(0,110,110,0));
        chart.setCurrentViewport(viewport);
        chart.setZoomType(ZoomType.VERTICAL);
        chart.setLineChartData(data);
        chart.setInteractive(false);//能否互动
        chart.setOnValueTouchListener(new LineChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
                Toast.makeText(HelloChartsActivity.this,lineIndex+" "+pointIndex+" "+value.getX()+" "+value.getY(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }
}
