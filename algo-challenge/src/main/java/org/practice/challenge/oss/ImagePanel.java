package org.practice.challenge.oss;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author feikong
 * @version 2022/9/27
 */
public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel() {
        try {
            image = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\iu001.jpg"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 300, 300, this); // see javadoc for more info on the parameters
    }

    public static void main(String[] args) throws Exception{
        BufferedImage wPic = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\iu001.jpg"));
        JLabel wIcon = new JLabel(new ImageIcon(wPic));
    }
}
