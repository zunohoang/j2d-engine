package engine.components;

public class BoxCollider extends Component {
    public int width, height;

    public BoxCollider(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isColliding(BoxCollider other) {
        float thisLeft = gameObject.transform.position.x - width / 2;
        float thisRight = gameObject.transform.position.x + width / 2;
        float thisTop = gameObject.transform.position.y - height / 2;
        float thisBottom = gameObject.transform.position.y + height / 2;

        float otherLeft = other.gameObject.transform.position.x - other.width / 2;
        float otherRight = other.gameObject.transform.position.x + other.width / 2;
        float otherTop = other.gameObject.transform.position.y - other.height / 2;
        float otherBottom = other.gameObject.transform.position.y + other.height / 2;

        return !(thisLeft > otherRight || thisRight < otherLeft || thisTop > otherBottom || thisBottom < otherTop);
    }

    public boolean isCollidingWith(BoxCollider colB) {
        float thisLeft = gameObject.transform.position.x - width / 2;
        float thisRight = gameObject.transform.position.x + width / 2;
        float thisTop = gameObject.transform.position.y - height / 2;
        float thisBottom = gameObject.transform.position.y + height / 2;

        float colBLeft = colB.gameObject.transform.position.x - colB.width / 2;
        float colBRight = colB.gameObject.transform.position.x + colB.width / 2;
        float colBTop = colB.gameObject.transform.position.y - colB.height / 2;
        float colBBottom = colB.gameObject.transform.position.y + colB.height / 2;

        return !(thisLeft > colBRight || thisRight < colBLeft || thisTop > colBBottom || thisBottom < colBTop);
    }
}
