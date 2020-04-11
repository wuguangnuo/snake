package cn.wgn.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author WuGuangNuo
 * @date Created in 2020/4/4 19:47
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private int lenth; //蛇的长度
    private int score; //积分
    private int[] snakeX = new int[500], snakeY = new int[500]; //蛇的坐标
    private String fx; //方向 R L U D
    private int foodx, foody; //食物
    private boolean isStart = false; //游戏开始
    private boolean isFail = false; //游戏失败
    private Random random = new Random(); //随机种子
    private Timer timer = new Timer(100, this); //定时器

    /**
     * 构造器
     */
    GamePanel() {
        init();
        //获取键盘监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    /**
     * 初始化
     */
    private void init() {
        lenth = 3;
        score = 0;

        fx = "R";
        snakeX[0] = 100;
        snakeY[0] = 100; //头邡坐标
        snakeX[1] = 75;
        snakeY[1] = 100; //第1个身体坐标
        snakeX[2] = 50;
        snakeY[2] = 100; //第2个身体坐标

        foodx = 25 + 25 * random.nextInt(34);
        foody = 75 + 25 * random.nextInt(24);
    }

    /**
     * 画板:画界面，画蛇
     *
     * @param g Graphics画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //清屏
        this.setBackground(Color.WHITE); // 设置背景的颜色
        Data.header.paintIcon(this, g, 25, 11); //绘制头部的广告栏
        g.fillRect(25, 75, 850, 600); //绘制游戏区域

        //画一条静态的小蛇
        if ("R".equals(fx)) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if ("L".equals(fx)) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if ("U".equals(fx)) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if ("D".equals(fx)) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        for (int i = 1; i < lenth; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]); //蛇的身体长度通过length来控制;
        }

        //画食物
        Data.food.paintIcon(this, g, foodx, foody);

        //画积分
        g.setColor(Color.BLACK);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("长度：" + lenth, 750, 30);
        g.drawString("积分：" + score, 750, 50);

        //游戏提示:是否开始
        if (!isStart) {
            //画一个文字，String
            g.setColor(Color.WHITE); //设置画管的颜色
            g.setFont(new Font("微软雅黑", Font.BOLD, 40)); //设置字体
            g.drawString("按下空格开始游戏", 300, 300);
        }

        //失败提示
        if (isFail) {
            //画一个文字，String
            g.setColor(Color.RED); //设置画管的颜色
            g.setFont(new Font("微软雅黑", Font.BOLD, 40)); //设置字体
            g.drawString("游戏失败，按下空格重新开始", 200, 300);
        }
    }

    /**
     * 接收健盘的输入:监听
     *
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //获取按下的键盘是哪个键
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFail) {
                //如果失败
                isFail = false;
                init();
            } else {
                isStart = !isStart;
            }
            repaint(); //刷新界面
        }

        //键盘控制走向
        if (keyCode == KeyEvent.VK_LEFT) {
            fx = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            fx = "R";
        } else if (keyCode == KeyEvent.VK_UP) {
            fx = "U";
        } else if (keyCode == KeyEvent.VK_DOWN) {
            fx = "D";
        }
    }

    /**
     * 定时器，执行定时操作
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态
        if (isStart && !isFail) {
            //右移
            for (int i = lenth - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }

            //通过控制方向让头部移动
            if ("R".equals(fx)) {
                snakeX[0] = snakeX[0] + 25; //头部移动
                if (snakeX[0] > 850) {
                    snakeX[0] = 25;
                }
            } else if ("L".equals(fx)) {
                snakeX[0] = snakeX[0] - 25; //头部移动
                if (snakeX[0] < 25) {
                    snakeX[0] = 850;
                }
            } else if ("U".equals(fx)) {
                snakeY[0] = snakeY[0] - 25; //头部移动
                if (snakeY[0] < 75) {
                    snakeY[0] = 650;
                }
            } else if ("D".equals(fx)) {
                snakeY[0] = snakeY[0] + 25; //头部移动
                if (snakeY[0] > 650) {
                    snakeY[0] = 75;
                }
            }

            //吃食物
            if (snakeX[0] == foodx && snakeY[0] == foody) {
                //长度+1
                lenth++;
                //积分+10
                score += 10;
                //新食物
                foodx = 25 + 25 * random.nextInt(34);
                foody = 75 + 25 * random.nextInt(24);
            }

            //结束判断
            for (int i = 1; i < lenth; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                    break;
                }
            }

            //刷新界面
            repaint();
        }

        timer.start();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
