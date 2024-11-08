import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Plant {
    public enum Type{
        Shooter,iceShooter,doubleShooter,
        Flower,Walnut,Mine
    }
    Type type;
    int x;
    int y;
    int hp = 100;
    Timeline shoot = new Timeline(new KeyFrame(Duration.millis(2000), e -> {
        peaShoot();
    }));

    Timeline fastshoot = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
        peaShoot();
    }));
    public ImageView img = new ImageView(new Image("file:sus flower.png"));
    public Plant(Type type, int x, int y) {
        shoot.setCycleCount(-1);
        this.type = type;
        this.x = x;
        this.y = y;
        if (type == Type.Walnut){
            hp = 200;
        }
        img.setFitWidth(80);
        img.setFitHeight(80);
        img.setX(x-40);
        img.setY(y-80);
        switch (type){
            case Mine:
                this.img.setImage(new Image("file:mime.png"));
                break;
            case Walnut:
                this.img.setImage(new Image("file:walnut.png"));
                break;
            case Flower:
                this.img.setImage(new Image("file:sus flower.png"));
                break;
            case Shooter:
                this.img.setImage(new Image("file:pee shooter.png"));
                shoot.play();
                break;
            case doubleShooter:
                this.img.setImage(new Image("file:gorg.png"));
                fastshoot.play();
                break;
            case iceShooter:
                this.img.setImage(new Image("file:lodowy.png"));
                break;
        }
    }

    public void peaShoot(){
        Pea pea = new Pea(this.x,this.y);
        Main.root.getChildren().add(pea.img);
        Main.gameObjects.add(pea);
    }

    public void icePeaShoot(){
        GameObject icePea = new GameObject(this.x,this.y);
    /*    Main.root.getChildren().add(icePea);
        for (int i = 0; i < 300; i++) {
            icePea.x = icePea.x + 3;
            if (){

            }
        }*/
    }

    public void mineExplode(){
        for (int i = 0; i < Main.zombies.size(); i++) {
            Zombie z = Main.zombies.get(i);
            int distance = (int) Math.sqrt(Math.pow(Math.abs(z.x - this.x),2)+Math.pow(Math.abs(z.y - this.y),2));
            if (distance < 20){
                z.setHP(z.getHP() - (1/(distance+1))*100);
            }
        }
    }
}