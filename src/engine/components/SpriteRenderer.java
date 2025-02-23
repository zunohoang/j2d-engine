package engine.components;

import engine.graphics.Renderer;
import engine.maths.Vector2D;

import java.awt.*;

public class SpriteRenderer extends Component {
    private BoxCollider boxCollider;
    private CircleCollider circleCollider;

    @Override
    public void start() {
        super.start();
        // kiem tra xem no la box hay circle collider
        if(gameObject.getComponent(BoxCollider.class) != null) {
            BoxCollider collider = gameObject.getComponent(BoxCollider.class);
            boxCollider = collider;
        } else if(gameObject.getComponent(CircleCollider.class) != null) {
            CircleCollider collider = gameObject.getComponent(CircleCollider.class);
            circleCollider = collider;
        }
    }

    @Override
    public void draw(Renderer g) {
        g.setColor(Color.RED);
        if(boxCollider != null) {
            g.drawRect((int) gameObject.transform.position.x, (int) gameObject.transform.position.y, (int) boxCollider.width, (int) boxCollider.height);
        } else if(circleCollider != null) {
            g.fillCircle((int) gameObject.transform.position.x, (int) gameObject.transform.position.y, (int) circleCollider.radius);
        }
    }
}
