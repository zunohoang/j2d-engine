package engine.components;

import engine.objects.GameObject;

public class CircleCollider extends Component {
    public int radius;

    public CircleCollider(int radius) {
        this.radius = radius;
    }

    public boolean isColliding(CircleCollider other) {
        float dx = gameObject.transform.position.x - other.gameObject.transform.position.x;
        float dy = gameObject.transform.position.y - other.gameObject.transform.position.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        return distance < this.radius + other.radius;
    }

    public int getRadius() {
        return radius;
    }
}
