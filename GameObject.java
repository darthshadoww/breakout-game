// GameObject.java
import javafx.scene.Node;

public abstract class GameObject {
    protected Node view;
    // Other common properties like position, dimensions, etc.

    public GameObject(Node view) {
        this.view = view;
    }

    public Node getView() {
        return view;
    }

    // Abstract methods for updating the object
    public abstract void update();

    // Check if this game object intersects with another game object
    public boolean isColliding(GameObject other) {
        return view.getBoundsInParent().intersects(other.view.getBoundsInParent());
    }
}
