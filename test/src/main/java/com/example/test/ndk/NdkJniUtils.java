package com.example.test.ndk;

/**
 * Created by Guo Shaobing on 2016/10/17.
 */
public class NdkJniUtils {
    static {
        System.loadLibrary("TestJniLibName");   //defaultConfig.ndk.moduleName
    }
    public native String getCLanguageString();
}
