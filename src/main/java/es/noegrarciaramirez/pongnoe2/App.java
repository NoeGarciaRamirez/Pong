package es.noegrarciaramirez.pongnoe2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {
    
    final short SCENE_HEIGHT = 480;//"final" define una constante, y se pone mayus y separado con "_"
    final short SCENE_WIDTH = 640;
    
    //Variables eje X
    short ballCenterX = 100;
    byte ballCurrentSpeedX = 10;
    byte ballDirectionX = 1;
    
    //Variables eje Y
    short ballCenterY = 20;
    byte ballCurrentSpeedY = 10;
    byte ballDirectionY = 1;
    
    //Variables pala
    short stickHeight = 50;
    short stickPosY = (short)((SCENE_HEIGHT-stickHeight)/2);
    byte stickCurrentSpeed = 0;
    byte stickDirection = 1;//1 abajo -1 arriba
    
    @Override
            
    public void start(Stage stage) {
        Pane root = new Pane();
        var scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.BLACK);
        stage.setScene (scene);
        stage.show();
        
        //nuevo círculo, crear un objeto de clase Circle
        Circle circleBall = new Circle();
        //llamado a métodos del objeto del objeto circleBall
        circleBall.setCenterX(10);
        circleBall.setCenterY(30);
        circleBall.setRadius(7);
        circleBall.setFill(Color.WHITE);
        
        root.getChildren().add(circleBall);
        
        //nuevo rectángulo, crear un objeto de clase Circle

        
        Rectangle rectStick = new Rectangle();
        rectStick.setWidth(10);
        rectStick.setHeight(stickHeight);
        rectStick.setX(SCENE_WIDTH - 20);
        rectStick.setY(stickPosY);
        rectStick.setFill(Color.WHITE);
        
        root.getChildren().add(rectStick);

        //Movimiento de la pala derecha
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle (final KeyEvent keyEvent){
                switch(keyEvent.getCode()){
                    case UP:
                        stickDirection = -1;
                        stickCurrentSpeed = 3;
                        break;
                    case DOWN:
                        stickDirection = 1;
                        stickCurrentSpeed = 3;
                        break;
                }
            }
        });
        
        
        
        //Movimiento de la bola        
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
                    if(ballCenterY >= 463){
                        ballDirectionY = -1;

                    } else if(ballCenterY <= 7){
                        ballDirectionY = 1;
                    }
                    
                    //Pala
                    rectStick.setY(stickPosY);
                    stickPosY += stickCurrentSpeed * stickDirection;
                    
                    if(stickPosY <= 0) {
                        stickDirection = 0;
                        stickPosY = 0;
                    } else if (stickPosY >= SCENE_HEIGHT - stickHeight) {
                        stickDirection = 0;
                        stickPosY = (short) (SCENE_HEIGHT - stickHeight);
                    }
                    
                    Shape shapeCollision = Shape.intersect(circleBall, rectStick);//Objeto es distinto de clase, cuando se pone el nombre de la clase
                    boolean colisionVacia = shapeCollision.getBoundsInLocal().isEmpty();
                    if (colisionVacia == false) {
                        System.out.println("choque");
                        ballDirectionX = -1;
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