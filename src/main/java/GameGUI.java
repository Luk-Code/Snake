import javax.swing.*;
import java.awt.*;

public class GameGUI {
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int GAME_WIDTH = 700;
    public static int GAME_HEIGHT = 700;
    public static int DELAY = 20;


    public GameGUI() {
        GAME_WIDTH = screenSize.width;
        GAME_HEIGHT = screenSize.height;
        JFrame frame = new JFrame();
        GameEngine gameEngine = new GameEngine();
        frame.setUndecorated(true);
        frame.pack();
        frame.setTitle("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gameEngine);
        frame.setVisible(true);
        device.setFullScreenWindow(frame);
    }
}
