import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Random;

public class Point {
    private int x;
    private int y;
    public Point father;
    public BufferedImage image;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.father = null;
    }

    public Point(int x, int y, Point father) {
        this.x = x;
        this.y = y;
        this.father = father;
    }

    public Point() {
    }

    public void randomize(int maxX, int maxY, int margin) {
        Random r = new Random();
        this.x = r.nextInt(maxX - (margin * 2)) + margin;
        this.y = r.nextInt(maxY - (margin * 2)) + margin;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

}
