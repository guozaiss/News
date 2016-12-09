package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Size {
    private String dirStr = "C:\\Users\\Admin\\Desktop";

    private void generateXmlFile(float bei, int max) {

        StringBuffer sbForWidth = new StringBuffer();
        sbForWidth.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sbForWidth.append("<resources>\n");
        float cellw = max * 1.0f;

        for (int i = 1; i < cellw; i++) {
            sbForWidth.append("<dimen name=\"size_" + i + "\">" + (float) i / bei + "dp</dimen>\n");
        }
        sbForWidth.append("</resources>");


        File fileDir = new File(dirStr + File.separator
                + bei + ".xml");

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileDir));
            pw.print(sbForWidth.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Size().generateXmlFile(1.5f, 3000);
    }
}
