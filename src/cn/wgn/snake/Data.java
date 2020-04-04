package cn.wgn.snake;

import javax.swing.ImageIcon;
import java.net.URL;

/**
 * 外部素材数据
 *
 * @author WuGuangNuo
 * @date Created in 2020/4/4 19:47
 */
class Data {
    private static URL headerUrl = Data.class.getResource("/statics/header.png");
    private static URL upUrl = Data.class.getResource("/statics/up.png");
    private static URL downUrl = Data.class.getResource("/statics/down.png");
    private static URL leftUrl = Data.class.getResource("/statics/left.png");
    private static URL rightUrl = Data.class.getResource("/statics/right.png");
    private static URL bodyUrl = Data.class.getResource("/statics/body.png");
    private static URL foodUrl = Data.class.getResource("/statics/food.png");

    static ImageIcon header;
    static ImageIcon up;
    static ImageIcon down;
    static ImageIcon left;
    static ImageIcon right;
    static ImageIcon body;
    static ImageIcon food;

    Data() {
    }

    static {
        header = new ImageIcon(headerUrl);
        up = new ImageIcon(upUrl);
        down = new ImageIcon(downUrl);
        left = new ImageIcon(leftUrl);
        right = new ImageIcon(rightUrl);
        body = new ImageIcon(bodyUrl);
        food = new ImageIcon(foodUrl);
    }
}
