import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600); // Set the size of your scene here
        primaryStage.setScene(scene);

        GameManager gameManager = new GameManager(root); // Pass the initialized root pane here

        // Add mouse event handler to the scene to control the paddle with the mouse
        scene.setOnMouseMoved(event -> {
            gameManager.getPaddle().moveTo(event.getX());
        });
        gameManager.startGame(); // Now the root pane is non-null and its properties can be accessed
        primaryStage.setScene(scene);
        primaryStage.setTitle("Breakout Game");
        primaryStage.show(); // Now display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}
