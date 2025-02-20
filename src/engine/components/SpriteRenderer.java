package engine.components;

import java.awt.*;

public class SpriteRenderer extends Component {
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) gameObject.position.x, (int) gameObject.position.y, 50, 50);
    }
}
