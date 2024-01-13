import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.List;

import java.time.chrono.JapaneseChronology;
import java.time.chrono.JapaneseDate;

public class Main extends Application {

    static AnchorPane root = new AnchorPane();
    static Scene scene = new Scene(root);
    static final int FPS = 60;

    static ImageView background = new ImageView();

    static List<GameObject> gameObjects = new LinkedList<>();
    static List<Zombie> zombies = new LinkedList<>();
    public static void main(String[] args) {
        launch(args);
    }

    public static void addZombie(Zombie z) {
        gameObjects.add(z);
        zombies.add(z);
        root.getChildren().add(z.img);
    }

    @Override
    public void start(Stage primaryStage) {
        Image bg = new Image("file:Frontyard.png");
        background.setImage(bg);
        root.getChildren().add(background);

        Zombie z = new Zombie(100, 370);

        addZombie(z);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(-1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis((double) 1000/FPS), e -> {
            for (GameObject obj: gameObjects) {
                obj.update();
            }
        }));
        timeline.play();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}