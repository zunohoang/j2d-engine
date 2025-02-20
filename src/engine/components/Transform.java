package engine.components;

public class Transform extends Component {
    public float x, y;

    public Transform(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void move(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

}
