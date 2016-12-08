package com.example.test.ndk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.test.R;

public class NDKActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);
        TextView tv = (TextView) findViewById(R.id.tv);
        NdkJniUtils ndkJniUtils=new NdkJniUtils();
        tv.setText(ndkJniUtils.getCLanguageString());
    }
}
