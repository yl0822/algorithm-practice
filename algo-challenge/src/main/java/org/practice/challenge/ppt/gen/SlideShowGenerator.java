package org.practice.challenge.ppt.gen;

import org.apache.poi.xslf.usermodel.XMLSlideShow;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author feikong
 * @version 2022/8/16
 */
public class SlideShowGenerator implements Generator{
    private XMLSlideShow ppt;
    private String outputPath;

    @Override
    public void generate() {
        try {
            FileOutputStream out = new FileOutputStream(outputPath);
            ppt.write(out);
            out.close();
            ppt.close();
        } catch (IOException e) {
            throw new RuntimeException("export ppt file error");
        }
    }
}
