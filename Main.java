package sample;

import javafx.animation.*;
import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    TranslateTransition translate;
    PauseTransition pauseTransition;
    SequentialTransition seqTransition;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();

        CarPane carPane = new CarPane(); // create a car pane

        Image image = new Image("file:batmobile2.png");
        ImageView car = new ImageView(image);

        car.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DOWN:
                    car.setY(car.getY() + 10);
                    break;
                case UP:
                    car.setY(car.getY() - 10);
                    break;
                case LEFT:
                    car.setX(car.getX() - 10);
                    break; //remember to change back to car
                case RIGHT:
                    car.setX(car.getX() + 10);
                    break; //remember to change back to car
                default:
            }
        });

        car.setFitHeight(200);
        car.setFitWidth(200);
        car.setPreserveRatio(true);
        car.relocate(100, 660);
        pane.getChildren().add(car);

        translate = TranslateTransitionBuilder
                .create()
                .duration(new Duration(7 * 1000))
                .node(car)
                .toX(725)
                .autoReverse(false)
                .cycleCount(Timeline.INDEFINITE)
                .interpolator(Interpolator.EASE_BOTH)
                .build();

        //Create a scene and place it in the stage
        Scene scene = new Scene(pane, 850, 730);
        String image2 = this.getClass().getResource("road1.jpg").toExternalForm();
        pane.setStyle(" -fx-background-image: url('" + image2 + "');  "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: stretch;");
        primaryStage.setTitle("Gran Turismo"); // set the stage title
        primaryStage.setScene(scene); //place the scene in the stage
        primaryStage.show(); // Display the stage
        translate.play();
        car.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
