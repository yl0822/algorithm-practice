package org.practice.challenge.image.gif;

import com.madgag.gif.fmsware.AnimatedGifEncoder;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author feikong
 * @version 2022/10/17
 */
public class AnimatedGifGenerator {

    public static void main(String[] args) throws Exception{
        BufferedImage image1 = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\001.jpg"));
        BufferedImage image2 = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\002.jpg"));
        BufferedImage image3 = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\003.jpg"));
        AnimatedGifEncoder e = new AnimatedGifEncoder();
        //生成的图片路径
        e.start(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\" + System.currentTimeMillis()+".gif"));
//        e.setSize(960, 720);
        //图片之间间隔时间
        e.setDelay(500);   // 1 frame per sec
        //重复次数 0表示无限重复 默认不重复
        e.setRepeat(0);
        //添加图片
//        e.setBackground(Color.GREEN);
        e.addFrame(image1);
        e.addFrame(image2);
        e.addFrame(image3);
        e.finish();
    }
}
