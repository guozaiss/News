package com.example.test.gsontran;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.test.R;
import com.google.gson.Gson;

public class GsonTranActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_tran);
        Model model = new Model();
        model.setAge(2);
        String s = new Gson().toJson(model);
        Log.d("GsonTranActivity", s);
    }
}
