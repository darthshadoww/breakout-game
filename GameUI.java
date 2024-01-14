// GameUI.java
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameUI {
    private Stage stage;
    private GameManager gameManager;

    public GameUI(Stage stage) {
        this.stage = stage;
        Pane root = new Pane(); // Initialize the root Pane here

        this.gameManager = new GameManager(root);
        initUI(root); // Pass root to initUI

    }

    public void initUI(Pane root) {
        Scene scene = new Scene(root, 800, 600);

        // Add components to root...

        stage.setScene(scene);
        stage.setTitle("Breakout Game");
        stage.show();

        gameManager.startGame();
    }
}
