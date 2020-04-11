package cn.wgn.snake;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * 外部素材数据
 *
 * @author WuGuangNuo
 * @date Created in 2020/4/4 19:47
 */
class Data {
    private static URL headerUrl = Data.class.getResource("/statics/header.png");
    private static URL upUrl = Data.class.getResource("/statics/up.png");
    private static URL bodyUrl = Data.class.getResource("/statics/body.png");
    private static URL foodUrl = Data.class.getResource("/statics/food.png");

    static ImageIcon header = new ImageIcon(headerUrl);
    static ImageIcon up = new ImageIcon(upUrl);
    static ImageIcon right = rotate(up, 90);
    static ImageIcon down = rotate(up, 180);
    static ImageIcon left = rotate(up, 270);
    static ImageIcon body = new ImageIcon(bodyUrl);
    static ImageIcon food = new ImageIcon(foodUrl);

    /**
     * 旋转图片
     *
     * @param image 源图片
     * @param angel 角度
     * @return 目标图片
     */
    private static ImageIcon rotate(ImageIcon image, int angel) {
        Image src = image.getImage();
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        // calculate the new image size
        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(
                src_width, src_height)), angel);

        BufferedImage res = null;
        res = new BufferedImage(rect_des.width, rect_des.height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = res.createGraphics();

        // transform(这里先平移、再旋转比较方便处理；绘图时会采用这些变化，绘图默认从画布的左上顶点开始绘画，源图片的左上顶点与画布左上顶点对齐，然后开始绘画，修改坐标原点后，绘画对应的画布起始点改变，起到平移的效果；然后旋转图片即可)
        //平移（原理修改坐标系原点，绘图起点变了，起到了平移的效果，如果作用于旋转，则为旋转中心点）
        g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);

        //旋转（原理transalte(dx,dy)->rotate(radians)->transalte(-dx,-dy);修改坐标系原点后，旋转90度，然后再还原坐标系原点为(0,0),但是整个坐标系已经旋转了相应的度数 ）
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

        g2.drawImage(src, null, null);
        return new ImageIcon(res);
    }

    /**
     * 计算转换后目标矩形的宽高
     *
     * @param src   源矩形
     * @param angel 角度
     * @return 目标矩形
     */
    private static Rectangle CalcRotatedSize(Rectangle src, int angel) {
        double cos = Math.abs(Math.cos(Math.toRadians(angel)));
        double sin = Math.abs(Math.sin(Math.toRadians(angel)));
        int des_width = (int) (src.width * cos) + (int) (src.height * sin);
        int des_height = (int) (src.height * cos) + (int) (src.width * sin);
        return new java.awt.Rectangle(new Dimension(des_width, des_height));
    }
}
