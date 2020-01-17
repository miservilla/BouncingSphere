import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;


public class BouncingSphere extends Application
{
    @Override
    public void start(Stage stage)
    {
        Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 600, 600);
        scene.setFill(Color.CADETBLUE);

        PhongMaterial materialBLUE = new PhongMaterial();
        materialBLUE.setDiffuseColor(Color.BLUE);
        Sphere sphere = new Sphere(50, 50);
        sphere.setMaterial(materialBLUE);
        sphere.relocate(0,0);
        canvas.getChildren().add(sphere);


        stage.setTitle("Bouncing Sphere");
        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                new EventHandler<>()
                {
                    double dx = 7; //Sets step on x (or velocity).
                    double dy = 3; //Sets step on y.

                    @Override
                    public void handle(ActionEvent t)
                    {
                        //Moves the ball.
                        sphere.setLayoutX(sphere.getLayoutX() + dx);
                        sphere.setLayoutY(sphere.getLayoutY() + dy);

                        Bounds bounds = canvas.getBoundsInLocal();

                        if (sphere.getLayoutX() <= (bounds.getMinX() + sphere.getRadius()) ||
                                sphere.getLayoutX() >= (bounds.getMaxX() - sphere.getRadius()))
                        {
                            dx = -dx;
                        }

                        if (sphere.getLayoutY() >= (bounds.getMaxY() - sphere.getRadius()) ||
                                (sphere.getLayoutY() <= (bounds.getMinY() + sphere.getRadius())))
                        {
                            dy = -dy;
                        }

                    }
                }));


        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }


}
