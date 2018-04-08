package com.example.test.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.test.R;

import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionResult;

public class CalculatorActivity extends Activity {

    private Evaluator evaluator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        evaluator = new Evaluator();
        evaluator.putFunction(new HexFun());
        evaluator.putFunction(new SelectFun());

//        evaluator.putVariable("X", "870000DD070A191332240000F02811");
//        evaluator.putVariable("X", "0240DD0C1C009D005C0000004E00000002");
        evaluator.putVariable("X", "0240dd0c1c006500390000004100000090");

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    String evaluate = evaluator.evaluate("equals('hex(fa,16)','hex(fa,16)')", false, true);
//                    String evaluate = evaluator.evaluate("hex('ff',16)",false, true);
//                    String evaluate = evaluator.evaluate("select(2 == 2 ,23 , 21)");
//                    String evaluate = evaluator.evaluate("select('hex(fb,16)' == 'hex(fb,16)' ,23 , 21)",false, true);
//                    String evaluate = evaluator.evaluate("0==1",false, true);
//                    String evaluate = evaluator.evaluate("0==0",false, true);
//                    String evaluate = evaluator.evaluate("0==0&&0==1",false, true);
//                    String evaluate = evaluator.evaluate("hex(substring('FFff',2,4),16)",false, true);
//                    String evaluate = evaluator.evaluate("hex(substring('#{X}',2,4),16)",false, true);
//                    String evaluate = evaluator.evaluate("hex(substring('#{X}',6,8),16)",false, true);
//                    String evaluate = evaluator.evaluate("hex(substring('#{X}',6,8),16)",false, true);
//                    String evaluate = evaluator.evaluate("charAt('#{X}',24)",false, true);
//                    String evaluate = evaluator.evaluate("select(length('#{X}')==30&&equals(substring('#{X}',6,8),'DD'),hex(substring('#{X}',25,28),16)*pow(10,(hex(charAt('#{X}',24),16)-16)),0)", false, true);
//                    String evaluate = evaluator.evaluate("select(length('#{X}')==34,hex(substring('#{X}',12,14),16),0)", false, true);
                    String evaluate = evaluator.evaluate("select(length('#{X}')==34&&equals(substring('#{X}',8,10),'1C'),hex(substring('#{X}',12,14),16),0)", false, true);
                    Log.e("evaluate", evaluate + "");
                } catch (Exception e) {
                    Log.e("evaluate", "err", e);
                }
            }
        });
    }

    private class HexFun implements Function {

        @Override
        public String getName() {
            return "hex";
        }

        @Override
        public FunctionResult execute(Evaluator evaluator, String s) throws FunctionException {
            String[] split = s.split(",");
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].replace("\'", "");
            }
            Integer integer = Integer.valueOf(split[0].toUpperCase(), Float.valueOf(split[1]).intValue());
            Log.e("hex", split[0] + " " + integer);

            return new FunctionResult(String.valueOf(integer), 0);
        }
    }

    private class SelectFun implements Function {

        @Override
        public String getName() {
            return "select";
        }

        @Override
        public FunctionResult execute(Evaluator evaluator, String s) throws FunctionException {
            String[] split = s.split(",");
            if (split.length != 3) {
                return null;
            }
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].replace("\'", "");
            }

            FunctionResult functionResult = null;
            if (Float.valueOf(split[0]).intValue() == 1) {
                functionResult = new FunctionResult(split[1], 0);
            } else {
                functionResult = new FunctionResult(split[2], 0);
            }

            return functionResult;
        }
    }


}