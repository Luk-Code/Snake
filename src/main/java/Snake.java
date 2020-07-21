import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Snake {
    public ArrayList<Point> body = new ArrayList<>();
    public Point head;
    public int directionX;
    public int directionY;
    public int speed = 12;
    public int size = 50;
    public int sizeHead = 80;
    public int startWith = 30;



    public Snake() {
        initialize();
    }

    private void initialize() {
        try {new Images();}catch (Exception ignored){}

        head = new Point((GameGUI.GAME_WIDTH - (sizeHead / 2)) / 2, (GameGUI.GAME_HEIGHT - (sizeHead / 2)) / 2);
        head.image = Images.HEADN;
        body.add(head);
        
        directionX = 0;
        directionY = 0;
        for (int i = 0; i < startWith; i++) {
            Point last = body.get(body.size() - 1);
            body.add(new Point(last.getX(), last.getY() + speed, last));
        }
        loadImages();
    }

    public Point getPosition() {
        return head;
    }

    public void changeHeadImg(BufferedImage img) {
        head.image = img;
    }

    public void draw(Graphics g) {
        ArrayList<Point> later = new ArrayList<>();
        g.drawImage(Images.TAIL, body.get(body.size() - 1).getX() - (size / 2), body.get(body.size() - 1).getY() - (size / 2), size, size, null);
        for (Point p : body) {
            if (p.image == Images.BODYNE || p.image == Images.BODYNW || p.image == Images.BODYSE || p.image == Images.BODYSW)
                later.add(p);
            else if (body.indexOf(p) < body.size() - 1)
                g.drawImage(p.image, p.getX() - (size / 2), p.getY() - (size / 2), size, size, null);
        }

        for (Point p : later)
            g.drawImage(p.image, p.getX() - (size / 2), p.getY() - (size / 2), size, size, null);

        g.drawImage(head.image, head.getX() - (sizeHead / 2), head.getY() - (sizeHead / 2), sizeHead, sizeHead, null);

    }


    public void eat(Apple apple) {
        for (int i = 0; i < apple.getKcal(); i++) {
            Point last = body.get(body.size() - 1);
            body.add(new Point(-555, -555, last));
        }
    }

    private void loadImages() {
        for (int i = body.size() - 1; i > 0; i--) {
            Point point = body.get(i);
            if (i < body.size() - 1) {
                String f = getFatherDirection(point);
                String s = getSonDirection(i);
                if (!f.equals(s)) {
                    point.image = Images.getImgByDirection(s + f);
                } else {
                    point.image = Images.getImgByDirection(f);
                }
            }
        }
    }


    private String getFatherDirection(Point p) {
        Point father = p.father;
        if (father != null) {
            if (p.getY() < father.getY()) return "S";
            if (p.getY() > father.getY()) return "N";
            if (p.getX() > father.getX()) return "W";
            if (p.getX() < father.getX()) return "E";
        }
        return "N";
    }

    private String getSonDirection(int i) {
        Point p = body.get(i);
        if (i + 1 >= body.size()) return "X";
        Point son = body.get(i + 1);

        if (p.getY() > son.getY()) return "S";
        if (p.getY() < son.getY()) return "N";
        if (p.getX() < son.getX()) return "W";
        if (p.getX() > son.getX()) return "E";
        return "X";


    }

    public void move() {
        if (directionY != 0 || directionX != 0) {
            for (int i = body.size() - 1; i > 0; i--) {
                Point point = body.get(i);

                point.setX(point.father.getX());
                point.setY(point.father.getY());

            }

            head.setX(head.getX() + directionX);
            head.setY(head.getY() + directionY);

            moveThroughWallIfNeeded();


            loadImages();

        }
    }

    private void moveThroughWallIfNeeded(){
        if (head.getX() < -(size/2))
            head.setX(GameGUI.GAME_WIDTH + (size/2));

        if (head.getX() > GameGUI.GAME_WIDTH + (size/2) )
            head.setX(-(size/2));

        if (head.getY() < -(size/2) )
            head.setY(GameGUI.GAME_HEIGHT + (size/2));

        if (head.getY() > GameGUI.GAME_HEIGHT + (size/2)  )
            head.setY(-(size/2));
    }

    public void restart() {
        body.clear();
        initialize();
    }

    public void changeDirection(char d) {

        switch (d) {
            case 'N':
                directionX = 0;
                if (directionY == 0) {

                    changeHeadImg(Images.HEADN);
                    directionY = -1 * speed;
                }
                break;
            case 'S':
                directionX = 0;
                if (directionY == 0) {
                    changeHeadImg(Images.HEADS);
                    directionY = speed;
                }
                break;
            case 'E':
                if (directionX == 0) {
                    changeHeadImg(Images.HEADE);
                    directionX = speed;
                }
                directionY = 0;
                break;
            case 'W':
                if (directionX == 0) {

                    changeHeadImg(Images.HEADW);
                    directionX = -1 * speed;
                }
                directionY = 0;
                break;
        }


    }
}
