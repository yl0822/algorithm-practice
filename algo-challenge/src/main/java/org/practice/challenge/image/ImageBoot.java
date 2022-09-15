package org.practice.challenge.image;

import java.io.FileInputStream;

import static org.practice.challenge.image.ImageUtil.readBytesByBuffer;
import static org.practice.challenge.image.ImageUtil.readBytesByBufferWithoutCompress;
import static org.practice.challenge.image.ImageUtil.readBytesByPpt;
import static org.practice.challenge.image.ImageUtil.readBytesByStreamFromPath;
import static org.practice.challenge.image.ImageUtil.readBytesByStreamFromUrl;
import static org.practice.challenge.image.ImageUtil.write2File;

/**
 * @author feikong
 * @version 2022/9/14
 */
public class ImageBoot {
    private static final String INPUT_IMAGE = "C:\\Users\\Administrator\\Desktop\\166208709084707846.png";
    private static final String INPUT_PPT = "C:\\Users\\Administrator\\Desktop\\4444.pptx";
    private static final String OUTPUT_PATH = "C:\\Users\\Administrator\\Desktop\\" + System.currentTimeMillis() + ".png";

    public static void main(String[] args) throws Exception{
//        write2File(readBytesByStreamFromUrl(INPUT_IMAGE_URL), OUTPUT_PATH);
//        write2File(readBytesByStreamFromPath(INPUT_IMAGE), OUTPUT_PATH);
//        write2File(readBytesByBuffer(INPUT_IMAGE), OUTPUT_PATH);
//        readBytesByBufferWithoutCompress(INPUT_IMAGE, OUTPUT_PATH);
//        write2File(readBytesByPpt(INPUT_PPT), OUTPUT_PATH);

        System.out.println(new SimpleImageInfo(new FileInputStream(INPUT_IMAGE)));
    }
}
