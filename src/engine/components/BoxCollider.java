package engine.components;

public class BoxCollider extends Component {
    public int width, height;

    public BoxCollider(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isColliding(BoxCollider other) {
        return gameObject.transform.position.x < other.gameObject.transform.position.x + other.width &&
                gameObject.transform.position.x + width > other.gameObject.transform.position.x &&
                gameObject.transform.position.y < other.gameObject.transform.position.y + other.height &&
                gameObject.transform.position.y + height > other.gameObject.transform.position.y;
    }
}
