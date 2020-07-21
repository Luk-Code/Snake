import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Apple {
    private Point position = new Point();
    public int size = 75;
    private int kcal = 5;
    private BufferedImage image;

    public Apple() {
        this.renew();
        image = Images.APPLE;


    }

    public void draw(Graphics g) {

        g.drawImage(image, position.getX() - size / 2, position.getY() - size / 2, size, size, null);
    }


    public Point getPosition() {
        return position;
    }

    public int getKcal() {
        return kcal;
    }

    public void renew() {
        Random r = new Random();
        this.kcal = r.nextInt(5) * 5;
        if (this.kcal == 0)
            this.kcal = 5;
        this.position.randomize(GameGUI.GAME_WIDTH, GameGUI.GAME_HEIGHT, size / 2);
    }
}
