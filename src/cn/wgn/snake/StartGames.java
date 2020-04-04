package cn.wgn.snake;

import javax.swing.JFrame;

/**
 * @author WuGuangNuo
 * @date Created in 2020/4/4 19:39
 */
public class StartGames {
    public static void main(String[] args) {
        //绘制JFrame静态窗口
        JFrame frame = new JFrame("贪吃蛇游戏");
        //设置界面大小
        frame.setBounds(10, 10, 900, 720);
        //窗口大小不可改变
        frame.setResizable(false);
        //关闭事件
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //面板JPanel
        frame.add(new GamePanel());

        frame.setVisible(true);
    }
}
