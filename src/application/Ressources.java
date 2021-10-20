package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ressources {
    public static Image upButtonImage = new Image(String.valueOf(PoseidonApplication.class.getResource("icons/up.png")));
    public static ImageView upView = new ImageView(upButtonImage);

    public static Image downButtonImage = new Image(String.valueOf(PoseidonApplication.class.getResource("icons/down.png")));
    public static ImageView downView = new ImageView(downButtonImage);

    public static Image playButtonImage = new Image(String.valueOf(PoseidonApplication.class.getResource("icons/play.png")));
    public static ImageView playView = new ImageView(playButtonImage);

    public static Image indentImage = new Image(String.valueOf(PoseidonApplication.class.getResource("icons/indent.png")));
    public static ImageView indentView = new ImageView(indentImage);

    public static Image unindentImage = new Image(String.valueOf(PoseidonApplication.class.getResource("icons/unindent.png")));
    public static ImageView unindentView = new ImageView(unindentImage);
}
