import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameManager {
    private Pane root;
    private Ball ball;
    private Paddle paddle;
    private List<Brick> bricks;
    private AnimationTimer gameLoop;
    public Paddle getPaddle() {
        return paddle;
    }


    public GameManager(Pane root) {
        this.root = root;

        ball = new Ball(10); // Assuming the radius of the ball is 10
        paddle = new Paddle(100, 20, root.getWidth()); // Assuming the size of the paddle is 100x20

        resetBallAndPaddle();
        bricks = createBricks(); // Create bricks
    }

    public void startGame() {
        root.getChildren().add(ball.getView());
        root.getChildren().add(paddle.getView());
        bricks.forEach(brick -> root.getChildren().add(brick.getView()));

        setupGameLoop();
        gameLoop.start();
    }

    private void resetBallAndPaddle() {
        paddle.getView().setLayoutY(root.getHeight() - paddle.getView().getBoundsInLocal().getHeight() - 30);
        ball.getView().setLayoutX(root.getWidth() / 2);
        ball.getView().setLayoutY(paddle.getView().getLayoutY() - 20); // Position the ball just above the paddle
    }

    private List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        Random rand = new Random();
        int rows = 5;
        int columns = 10;
        double spacing = 5;
        double brickWidth = (root.getWidth() - (columns + 1) * spacing) / columns;
        double brickHeight = 20;
        Color brickColor = Color.PINK;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (rand.nextBoolean()) { // Randomly decide whether to place a brick
                    double x = spacing + col * (brickWidth + spacing);
                    double y = spacing + row * (brickHeight + spacing);
                    Brick brick = new Brick(brickWidth, brickHeight, brickColor);
                    brick.getView().setLayoutX(x);
                    brick.getView().setLayoutY(y);
                    bricks.add(brick);
                }
            }
        }
        return bricks;
    }

    private void setupGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ball.update();
                paddle.update();

                // Collision with bricks
                for (Iterator<Brick> iterator = bricks.iterator(); iterator.hasNext(); ) {
                    Brick brick = iterator.next();
                    if (ball.isColliding(brick)) {
                        iterator.remove();
                        root.getChildren().remove(brick.getView());
                        ball.reverseVelocityY();
                        break; // Break to avoid concurrent modification issues
                    }
                }

                // Collision with walls
                if (ball.getPositionX() <= 0 || ball.getPositionX() >= root.getWidth()) {
                    ball.reverseVelocityX();
                }
                if (ball.getPositionY() <= 0) {
                    ball.reverseVelocityY();
                }

                // Ball falling off the screen (game over)
                //if (ball.getPositionY() >= root.getHeight()) {
                //gameLoop.stop();
                //// Implement game over logic here
                //}
                // Collision with paddle
                if (ball.isColliding(paddle)) {
                    ball.reverseVelocityY();
                    // Adjust ball position to avoid sticking to the paddle
                    ball.getView().setLayoutY(paddle.getView().getLayoutY() - ball.getView().getBoundsInLocal().getHeight());
                }
            }
        };
    }
    public void resetGame() {
        // Reset ball position and velocity
        resetBallAndPaddle();

        // Clear existing bricks and create new ones
        bricks.clear();
        // Remove only Rectangle nodes that are bricks from the root
//        root.getChildren().removeIf(node -> node instanceof Rectangle && bricks.contains(node));

        bricks.addAll(createBricks());
        bricks.forEach(brick -> root.getChildren().add(brick.getView()));

        // Restart the game loop if it's stopped
        if (!gameLoop.isRunning()) {
            gameLoop.start();
        }
    }

}

