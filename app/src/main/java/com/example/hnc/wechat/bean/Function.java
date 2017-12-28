package com.example.hnc.wechat.bean;

/**
 * Created by pc on 2017/12/28.
 */

public class Function {
    private String functionName;
    private int functionImage;

    public Function(String functionName, int functionImage) {
        this.functionName = functionName;
        this.functionImage = functionImage;
    }

    public String getFunctionName() {
        return functionName;
    }

    public int getFunctionImage() {
        return functionImage;
    }
}
