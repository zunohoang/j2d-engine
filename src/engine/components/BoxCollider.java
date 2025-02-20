package engine.components;

public class BoxCollider extends Component {
    public int width, height;

    public BoxCollider(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isColliding(BoxCollider other) {
        return gameObject.position.x < other.gameObject.position.x + other.width &&
                gameObject.position.x + width > other.gameObject.position.x &&
                gameObject.position.y < other.gameObject.position.y + other.height &&
                gameObject.position.y + height > other.gameObject.position.y;
    }
}
