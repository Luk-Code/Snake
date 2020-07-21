import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {

    public static BufferedImage APPLE;
    
    public static BufferedImage BODYNS;
    public static BufferedImage BODYEW;
    public static BufferedImage BODYNE;
    public static BufferedImage BODYNW;
    public static BufferedImage BODYSW;
    public static BufferedImage BODYSE;

    public static BufferedImage HEADN;
    public static BufferedImage HEADS;
    public static BufferedImage HEADE;
    public static BufferedImage HEADW;
    public static BufferedImage HEADNL;
    public static BufferedImage HEADSL;
    public static BufferedImage HEADEL;
    public static BufferedImage HEADWL;


    public static BufferedImage TAIL;


    public Images() throws IOException {

        APPLE = ImageIO.read(Images.class.getResourceAsStream("/apple.png"));
        
        BODYNS = ImageIO.read(Images.class.getResourceAsStream("/body_NS.png"));
        BODYEW = ImageIO.read(Images.class.getResourceAsStream("/body_EW.png"));

        BODYNE = ImageIO.read(Images.class.getResourceAsStream("/body_NE.png"));
        BODYNW = ImageIO.read(Images.class.getResourceAsStream("/body_NW.png"));
        BODYSW = ImageIO.read(Images.class.getResourceAsStream("/body_SW.png"));
        BODYSE = ImageIO.read(Images.class.getResourceAsStream("/body_SE.png"));

        HEADN = ImageIO.read(Images.class.getResourceAsStream("/head_N.png"));
        HEADS = ImageIO.read(Images.class.getResourceAsStream("/head_S.png"));
        HEADE = ImageIO.read(Images.class.getResourceAsStream("/head_E.png"));
        HEADW = ImageIO.read(Images.class.getResourceAsStream("/head_W.png"));

        HEADNL = ImageIO.read(Images.class.getResourceAsStream("/head_NL.png"));
        HEADSL = ImageIO.read(Images.class.getResourceAsStream("/head_SL.png"));
        HEADEL = ImageIO.read(Images.class.getResourceAsStream("/head_EL.png"));
        HEADWL = ImageIO.read(Images.class.getResourceAsStream("/head_WL.png"));


        TAIL = ImageIO.read(Images.class.getResourceAsStream("/tail.png"));

    }

    public static BufferedImage getImgByDirection(String direction) {
        switch (direction) {
            case "N":
            case "S":
                return BODYNS;
            case "E":
            case "W":
                return BODYEW;

            case "NE":
            case "WS":
                return BODYNE;

            case "NW":
            case "ES":
                return BODYNW;

            case "SE":
            case "WN":
                return BODYSE;

            case "SW":
            case "EN":
                return BODYSW;


        }

        return BODYNS;
    }

}
