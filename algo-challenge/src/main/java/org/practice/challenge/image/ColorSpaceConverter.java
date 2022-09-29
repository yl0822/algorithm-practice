package org.practice.challenge.image;

import lombok.ToString;

/**
 * @author feikong
 * @version 2022/9/15
 */
public class ColorSpaceConverter {

    /**
     * https://mrtan.me/post/java-cmyk-to-rgb-converter.html
     * */
    public static RGB convertCmyk2Rgb(CMYK cmyk) {
        int r = 255 * (1 - cmyk.c / 100) * (1 - cmyk.k / 100);
        int g = 255 * (1 - cmyk.m / 100) * (1 - cmyk.k / 100);
        int b = 255 * (1 - cmyk.y / 100) * (1 - cmyk.k / 100);
        return new RGB(r, g, b);
    }

    /**
     * https://mrtan.me/post/java-rgb-to-cmyk-converter.html
     */
    public static CMYK convertRgb2Cmyk(RGB rgb) {
        double percentageR = rgb.r / 255.0 * 100;
        double percentageG = rgb.g / 255.0 * 100;
        double percentageB = rgb.b / 255.0 * 100;
        double k = 100 - Math.max(Math.max(percentageR, percentageG), percentageB);
        if (k == 100) {
            return new CMYK(0, 0, 0, 100);
        }
        int c = (int) ((100 - percentageR - k) / (100 - k) * 100);
        int m = (int) ((100 - percentageG - k) / (100 - k) * 100);
        int y = (int) ((100 - percentageB - k) / (100 - k) * 100);
        return new CMYK(c, m, y, (int) k);
    }

    @ToString
    static class RGB {
        int r;
        int g;
        int b;

        public RGB(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

    @ToString
    static class CMYK {
        int c;
        int m;
        int y;
        int k;

        public CMYK(int c, int m, int y, int k) {
            this.c = c;
            this.m = m;
            this.y = y;
            this.k = k;
        }
    }
}
