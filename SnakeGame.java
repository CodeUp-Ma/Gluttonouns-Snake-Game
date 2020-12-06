package Snake;

import javax.swing.*;
import java.awt.*;

public class SnakeGame {
    public static void main(String[] args) {
        JFrame jf = new JFrame("Snake Game");
        //获取屏幕宽度、高度：
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        jf.setBounds((width-800)/2,(height-800)/2,800,800);
        //固定窗体大小：
        jf.setResizable(false);
        //关闭窗口的同时，关闭程序：
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //创建面板：
        GamePanel gp = new GamePanel();
        jf.add(gp);

        jf.setVisible(true);
    }
}
