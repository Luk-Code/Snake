import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GameEngine extends JPanel implements KeyListener, ActionListener {

    private Snake snake = new Snake();
    public int score = 0;
    public int best = 0;
    public ArrayList<Apple> apples = new ArrayList<>();
    private Timer timer;

    public GameEngine() {
        try {new Images();}catch (Exception ignored){}
        apples.add(new Apple());
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(GameGUI.DELAY, this);
        timer.start();
    }


    public void actionPerformed(ActionEvent actionEvent) {
        timer.start();
        snake.move();


        loadHead();
        eatApples();

        if (apples.size() <= score /(5 * Math.pow( 2, apples.size()))) {
            apples.add(new Apple());
        }
        repaint();
        if(gameOver()){
            JOptionPane.showMessageDialog(this, "GAME OVER");
            restart();
        }
    }
    private void eatApples(){
        for (Apple apple : apples) {
            if (Math.abs(snake.getPosition().getX() - apple.getPosition().getX()) < (snake.size + apple.size) / 2 && Math.abs(snake.getPosition().getY() - apple.getPosition().getY()) < (snake.size + apple.size) / 2) {
                score += apple.getKcal();
                if (score > best)
                    best = score;
                snake.eat(apple);
                apple.renew();
                while (!isAppleOk(apple, snake)) apple.renew();

            }
        }
}
    private void restart(){
        snake.restart();
        apples.clear();
        timer.setDelay(15);
        apples.add(new Apple());
        score = 0;

    }
    private boolean gameOver(){
        for (int i = snake.body.size() - 1; i > 0; i--) {
            Point point = snake.body.get(i);
            if (Math.abs(point.getX() - snake.head.getX()) < (snake.size / 2 + (snake.sizeHead - 15) / 2) && Math.abs(point.getY() - snake.head.getY()) < (snake.size / 2 + (snake.sizeHead - 15) / 2) && i > (snake.sizeHead - 15) * 2 / snake.speed) {
               return true;
            }
        }
        return false;
    }

    public int distanceToApple() {
        int min = Integer.MAX_VALUE;
        for (Apple apple: apples){
            int temp = (int) Math.sqrt((apple.getPosition().getX() - snake.getPosition().getX()) * (apple.getPosition().getX() - snake.getPosition().getX()) + (apple.getPosition().getY() - snake.getPosition().getY()) * (apple.getPosition().getY() - snake.getPosition().getY()));
            if(min > temp)
                min = temp;
        }

        return min;
    }

    public void loadHead() {
        if (distanceToApple() < 200) {

            if (compareImages(Images.HEADN, snake.head.image)) snake.changeHeadImg(Images.HEADNL);

            if (compareImages(Images.HEADS, snake.head.image)) snake.head.image = Images.HEADSL;

            if (compareImages(Images.HEADE, snake.head.image)) snake.head.image = Images.HEADEL;

            if (compareImages(Images.HEADW, snake.head.image)) snake.head.image = Images.HEADWL;
        } else {
            if (compareImages(Images.HEADNL, snake.head.image)) snake.head.image = Images.HEADN;

            if (compareImages(Images.HEADSL, snake.head.image)) snake.head.image = Images.HEADS;

            if (compareImages(Images.HEADEL, snake.head.image)) snake.head.image = Images.HEADE;

            if (compareImages(Images.HEADWL, snake.head.image)) snake.head.image = Images.HEADW;
        }
    }

    public boolean compareImages(BufferedImage img1, BufferedImage img2) {
        int width1 = img1.getWidth();
        int height1 = img1.getHeight();

        for (int x = 0; x < width1; x++) {
            if (img1.getRGB(x, height1 / 2) != img2.getRGB(x, height1 / 2)) return false;
        }
        for (int y = 0; y < height1; y++) {
            if (img1.getRGB(width1 / 2, y) != img2.getRGB(width1 / 2, y)) {
                return false;
            }
        }
        return true;

    }

    public void paint(Graphics g) {

        g.setColor(new Color(154, 154, 154));
        g.fillRect(0, 0, GameGUI.GAME_WIDTH, GameGUI.GAME_HEIGHT);
        snake.draw(g);
        for(Apple apple: apples)
            apple.draw(g);
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10 , 20);
        g.drawString("Best score: " + best, GameGUI.GAME_WIDTH - 170 , 20);
        g.dispose();
    }

    public void keyPressed(KeyEvent keyEvent) {
        snakeDirections(keyEvent);
    }

    private void snakeDirections(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                snake.changeDirection('N');
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                snake.changeDirection('S');
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                snake.changeDirection('E');
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                snake.changeDirection('W');
                break;
            case KeyEvent.VK_ESCAPE:
                int tempX = snake.directionX;
                int tempY = snake.directionY;
                snake.directionY = 0;
                snake.directionX = 0;
                int res = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?");
                if(res == 0) {
                    System.exit(0);
                } else if (res == 1) {
                    snake.directionY = tempY;
                    snake.directionX = tempX;
                } else {
                    snake.directionY = tempY;
                    snake.directionX = tempX;
                }
                break;
            case KeyEvent.VK_ADD:
                if (timer.getDelay()> 1)
                    timer.setDelay(timer.getDelay() - 1);
                break;
            case KeyEvent.VK_SUBTRACT:
                    timer.setDelay(timer.getDelay() + 1);
                break;
            case KeyEvent.VK_U:
                for(int i = 0; i<20; i++){
                    Apple apple = apples.get(0);
                    score += apple.getKcal();
                    if (score > best)
                        best = score;
                    snake.eat(apple);
                    apple.renew();
                    while (!isAppleOk(apple, snake)) apple.renew();
                }
                break;

        }
    }

    public boolean isAppleOk(Apple a, Snake s) {
        for (Point p : s.body) {
            if (Math.abs(p.getX() - a.getPosition().getX()) < (snake.size + a.size) / 2 && Math.abs(p.getY() - a.getPosition().getY()) < (snake.size + a.size) / 2)
                return false;
        }
        for (Apple apple: apples) {
            if (apple != a) {
                if (Math.abs(apple.getPosition().getX() - a.getPosition().getX()) < a.size && Math.abs(apple.getPosition().getY() - a.getPosition().getY()) < a.size)
                    return false;
            }
        }
        return true;
    }

    public void keyReleased(KeyEvent keyEvent) {
    }

    public void keyTyped(KeyEvent keyEvent) {
    }

}
