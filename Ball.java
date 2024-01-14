import javafx.scene.shape.Circle;

public class Ball extends GameObject {
    private double velocityX = 1.5;
    private double velocityY = -1.5;

    public Ball(double radius) {
        super(new Circle(radius));
    }
    public double getPositionX() {
        Circle ballView = (Circle) view;
        return ballView.getCenterX();
    }
    public double getPositionY() {
        Circle ballView = (Circle) view;
        return ballView.getCenterY();
    }

    @Override
    public void update() {
        Circle ballView = (Circle) view;
        ballView.setCenterX(ballView.getCenterX() + velocityX);
        ballView.setCenterY(ballView.getCenterY() + velocityY);

        // This is where you would handle the ball's interaction with the game world.
        // You might want to include collision detection with the walls, paddle, and bricks here,
        // or you could handle that in the GameManager class.
    }


    public void reverseVelocityY() {
        velocityY = -velocityY;
    }

    public void reverseVelocityX() {
        velocityX = -velocityX;
    }

    // Additional getters or setters if needed
}
