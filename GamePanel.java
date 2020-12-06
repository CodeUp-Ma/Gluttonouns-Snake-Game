package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


/*
**游戏面板*/
public class GamePanel extends JPanel {
    //定义蛇的长度：
    int len;
    /**
    *定义两个数组，存储蛇的X轴Y轴*/
    int[] snakeX = new int[300];
    int[] snakeY = new int[300];
    //定义初始的状态
    //默认是暂停状态
    boolean isStart = false;
    /**
     * direction定义蛇头的方向
     */
    String direction;
    /**
     * timer定义刷新频率
     */
    Timer timer;
    /**
     * foodX--食物的X坐标
     * foodY--食物的Y坐标
     */
    int foodX,foodY;
    /**
     * chonghe 检测食物和蛇身重合
     */
    Random random = new Random();
    boolean chonghe = false;
    /**
     * 初始化蛇头坐标
     */
    public void init() {
        len = 4;

        snakeX[0] = 180;
        snakeY[0] = 271;

        snakeX[1] = 155;
        snakeY[1] = 271;

        snakeX[2] = 130;
        snakeY[2] = 271;

        snakeX[3] = 105;
        snakeY[3] = 271;

        direction = "R";
    }

    /**
     * 初始化食物坐标
     */
    public void initFoodXY() {
        do {
            foodX = 30+25 * random.nextInt(29);
            foodY = 121+25 * random.nextInt(24);
            for (int i = 0; i <len; i++) {
                if ( foodX == snakeX[i] && foodY == snakeY[i]) {
                    chonghe = true;
                }
                else {
                    chonghe = false;
                }
            }
        } while (chonghe);
    }
    public GamePanel() {
        init();
        initFoodXY();
        //设置焦点
        this.setFocusable(true);
        //增加键盘输入监听,开始、暂停
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                //方向键开始
                if (!isStart) {
                    if (keyCode == 37 || keyCode == 38 || keyCode == 39 || keyCode == 40) {
                        isStart = true;
                        repaint();
                    }
                }
                //空格键开始于暂停
                if (keyCode == 32) {
                    isStart = !isStart;
                    repaint();//重绘动作
                }

                //方向键改变蛇头
                if (keyCode == KeyEvent.VK_LEFT) {
                    if (!"R".equals(direction)) {
                        direction = "L";
                    }
                }
                if (keyCode == KeyEvent.VK_RIGHT) {
                    if (!"L".equals(direction)) {
                        direction = "R";
                    }
                }
                if (keyCode == KeyEvent.VK_UP) {
                    if (!"D".equals(direction)) {
                        direction = "U";
                    }
                }
                if (keyCode == KeyEvent.VK_DOWN) {
                    if (!"U".equals(direction)) {
                        direction = "D";
                    }
                }
            }
        });
        timer = new Timer(150-len, new ActionListener() {
            //事件监听，每delay处理一下
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStart) {
                    for (int i = len - 1; i > 0; i--) {
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }
                    if ("R".equals(direction)) {
                        snakeX[0] = snakeX[0] + 25;
                    }
                    if ("L".equals(direction)) {
                        snakeX[0] = snakeX[0] - 25;
                    }
                    if ("U".equals(direction)) {
                        snakeY[0] = snakeY[0] - 25;
                    }
                    if ("D".equals(direction)) {
                        snakeY[0] = snakeY[0] + 25;
                    }
                    if (snakeX[0] == foodX && snakeY[0] == foodY) {
                        initFoodXY();
                        len++;
                    }

                    repaint();//重绘
                    //超出界限
                    if (snakeX[0] == 755
                            ||snakeX[0] == 5
                            ||snakeY[0] ==96
                            ||snakeY[0] ==721) {
                        isStart = false;
                        init();
                    }
                    //碰到身体
                    for (int i = 1; i <len; i++) {
                        if ( snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                            isStart = false;
                            init();
                        }
                    }
                }
            }
        });
        timer.start();

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(189, 182, 182));
        Images.headerImg.paintIcon(this,g,10,10);
        g.setColor(new Color(154, 183, 191, 255));
        g.fillRect(10,95,764,653);
        //设置四周的墙
        for (int i=3; i<29; i++) {
            Images.wallImg.paintIcon(this, g, 5, 21 + 25 * i);
        }
        for (int i=3; i<29; i++) {
            Images.wallImg.paintIcon(this, g, 755, 21 + 25 * i);
        }
        for (int i=1; i<30; i++) {
            Images.wallImg.paintIcon(this, g, 5 + 25 * i, 96);
        }
        for (int i=1; i<30; i++) {
            Images.wallImg.paintIcon(this, g, 5 + 25 * i, 721);
        }

        //绘制蛇身：
        if ("R".equals(direction)) {
            Images.rightImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if ("L".equals(direction)) {
            Images.leftImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if ("U".equals(direction)) {
            Images.upImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if ("D".equals(direction)) {
            Images.downImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        for (int i = 1; i<len; i++) {
            Images.bodyImg.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        Images.foodImg.paintIcon(this,g,foodX,foodY);

        //如果游戏是暂停状态->设置提示语
        if (isStart == false) {
            g.setColor(new Color(2, 114, 24));
            //设置字体
            g.setFont(new Font("宋体",Font.BOLD,40));
            //打印文字
            g.drawString("点击空格以开始/暂停",210,330);
        }

        g.setColor(new Color(2, 114, 24));
        g.setFont(new Font("宋体",Font.BOLD,20));
        //打印文字
        g.drawString("得分 ："+(len-4),650,53);

    }
}
