package engine.components.ui;

import engine.components.Component;
import engine.graphics.Renderer;

import java.awt.*;

public class ColorRenderer extends Component {
    private Color color;
    private float opacity = 1.0f;

    public ColorRenderer(Color color, float opacity) {
        this.color = color;
        this.opacity = opacity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Renderer g) {
        super.draw(g);
        g.drawTransparentRect((int) gameObject.transform.position.x, (int)gameObject.transform.position.y, gameObject.getComponent(RectTransform.class).getWidth(), gameObject.getComponent(RectTransform.class).getHeight(), color, opacity);
    }
}
