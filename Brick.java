import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick extends GameObject {

    public Brick(double width, double height, Color color) {
        super(new Rectangle(width, height, color));
    }

    @Override
    public void update() {
        // Bricks in Breakout are generally static so you might not need to update them.
        // The update method could be used for effects like a brick flashing when hit.
    }

    // Additional methods if bricks have more behavior
}
