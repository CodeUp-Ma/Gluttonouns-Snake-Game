package Snake;

import javax.swing.*;
import java.net.URL;

/**
 *把图片封装成一个类
 * 这个类用来获取图片
 * @author mahongwei
 */
public class Images {
    /**
     * 将图片的路径封装为对象：****URL
     * 将把路径的图片封装为对象：****Img
     */
    public static URL bodyURL = Images.class.getResource("/image/body.png");
    public static ImageIcon bodyImg = new ImageIcon(bodyURL);

    public static URL foodURL = Images.class.getResource("/image/food.png");
    public static ImageIcon foodImg = new ImageIcon(foodURL);

    public static URL upURL = Images.class.getResource("/image/up.png");
    public static ImageIcon upImg = new ImageIcon(upURL);

    public static URL downURL = Images.class.getResource("/image/down.png");
    public static ImageIcon downImg = new ImageIcon(downURL);

    public static URL leftURL = Images.class.getResource("/image/left.png");
    public static ImageIcon leftImg = new ImageIcon(leftURL);

    public static URL rightURL = Images.class.getResource("/image/right.png");
    public static ImageIcon rightImg = new ImageIcon(rightURL);

    public static URL wallURL = Images.class.getResource("/image/wall.png");
    public static ImageIcon wallImg = new ImageIcon(wallURL);

    public static URL headerURL = Images.class.getResource("/image/header.png");
    public static ImageIcon headerImg = new ImageIcon(headerURL);
}
