import javafx.scene.shape.Rectangle;

public class Paddle extends GameObject {
    private double sceneWidth; // Store the width of the scene

    public Paddle(double width, double height, double sceneWidth) {
        super(new Rectangle(width, height));
        this.sceneWidth = sceneWidth; // Initialize the scene width

    }

    @Override
    public void update() {
        // You might want to include code here for the paddle to follow the mouse,
        // respond to keyboard input, or maintain its position within the bounds of the screen.
    }

    public void move(double dx) {
        Rectangle paddleView = (Rectangle) view;
        double newX = paddleView.getX() + dx;

        // Keep the paddle within the bounds of the screen
        if (newX < 0) {
            newX = 0;
        } else if (newX > paddleView.getScene().getWidth() - paddleView.getWidth()) {
            newX = paddleView.getScene().getWidth() - paddleView.getWidth();
        }

        paddleView.setX(newX);
    }

    public void moveTo(double x) {
        double paddleWidth = ((Rectangle) view).getWidth();
        double newX = x - paddleWidth / 2; // Center the paddle on the cursor
        if (newX < 0) newX = 0;
        if (newX > sceneWidth - paddleWidth) newX = sceneWidth - paddleWidth;
        view.setLayoutX(newX);
    }

    // Additional methods if paddle has more behavior
}
