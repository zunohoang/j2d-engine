package engine.components;

import engine.objects.GameObject;

public class CircleCollider extends Component {
    public int radius;

    public CircleCollider(int radius) {
        this.radius = radius;
    }

    public boolean isColliding(CircleCollider other) {
        float dx = gameObject.position.x - other.gameObject.position.x;
        float dy = gameObject.position.y - other.gameObject.position.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        return distance < this.radius + other.radius;
    }

    public int getRadius() {
        return radius;
    }
}
