package es.noegrarciaramirez.pongnoe2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {
    
    //Variables eje X
    int ballCenterX = 20;
    int ballCurrentSpeedX = 5;
    int ballDirectionX = 1;
    
    //Variables eje Y
    int ballCenterY = 20;
    int ballCurrentSpeedY = 5;
    int ballDirectionY = 1;
    
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        var scene = new Scene(root, 640, 480);
        scene.setFill(Color.BLACK);
        stage.setScene (scene);
        stage.show();
        
        //nuevo circulo, crear un objeto de clase Circle
        Circle circleBall = new Circle();
        //llamado a métodos del objeto del objeto circleBall
        circleBall.setCenterX(10);
        circleBall.setCenterY(30);
        circleBall.setRadius(7);
        circleBall.setFill(Color.WHITE);
        
        
        root.getChildren().add(circleBall);
        
                
        Timeline timeline = new Timeline(
            // 0.017 ~= 60 FPS
            new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {/*lo de dentro del handle se ejecutara X veces por segundo*/
                    //Eje X
                    circleBall.setCenterX(ballCenterX);
                    ballCenterX += ballCurrentSpeedX * ballDirectionX;
                    if(ballCenterX >= 633){
                        ballDirectionX = -1;

                    } else if(ballCenterX <= 7){
                        ballDirectionX = 1;
                    }
                    //Eje Y
                    circleBall.setCenterY(ballCenterY);
                    ballCenterY += ballCurrentSpeedY * ballDirectionY;
                    if(ballCenterX >= 474){
                        ballDirectionX = -1;

                    } else if(ballCenterX <= 7){
                        ballDirectionX = 1;
                    }                    
                }
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }

}