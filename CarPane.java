package sample;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.animation.KeyFrame;

/**
 * Created by dean on 7/28/16.
 */
public class CarPane extends Pane{
    public final double radius = 20;
    private double x = 200, y = 200;
    private double dx = 1, dy = 1;
    private Circle circle = new Circle(x, y, radius);
    private Timeline animation;
    Image image = new Image("file:batmobile2.png");
    ImageView car = new ImageView(image);

    public CarPane() {
        getChildren().add(car); // Place a car into this pane
        // Create an animation for moving the car
        animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> moveCar()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
        car.requestFocus();
        car.setFitHeight(200);
        car.setFitWidth(200);
        car.setPreserveRatio(true);
        car.setOnKeyPressed(e -> { //remember to change back to car
            switch (e.getCode()) {
                case DOWN: car.setY(car.getY() + 10); break;
                case UP: car.setY(car.getY() - 10); break;
                case LEFT: car.setX(car.getX() - 10);
                System.out.println(car.getX()); break; //remember to change back to car
                case RIGHT: car.setX(car.getX() + 10); break; //remember to change back to car
                default:

            }
        });
    }

    public void play() {
        animation.play();
    }
    public void pause() {
        animation.pause();
    }
    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.1);
    }
    public void decreaseSpeed() {
        animation.setRate(
                animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }
    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }
    protected void moveCar() {
// Check boundaries
        if (x < 200 || x > getWidth() - 200) {
            dx *= -1; // Change ball move direction
        }
        if (y < 200 || y > getHeight() - 200) {
            dy *= -1; // Change ball move direction
        }
// Adjust ball position
        x += dx;
        y += dy;
        //car.setCenterX(x);
        //car.setCenterY(y);
    }
}
