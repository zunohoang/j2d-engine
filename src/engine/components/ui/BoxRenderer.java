package engine.components.ui;

import engine.components.Component;
import engine.components.Transform;
import engine.graphics.Renderer;
import engine.utils.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BoxRenderer extends Component {
    private int x, y;
    private int width;
    private int height;
    private Color color;
    private Image image;
    private String text;
    private Font font;
    private float opacity;
    private Color textColor;
    private int borderRadius = 10;

    public BoxRenderer(int width, int height, Color color, Image image, String text, float opacity, Font font, Color textColor) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.image = image;
        this.text = text;
        this.opacity = opacity;
        this.font = font;
        this.textColor = textColor;
    }

    @Override
    public void start() {
        super.start();
        this.x = (int) gameObject.transform.position.x;
        this.y = (int) gameObject.transform.position.y;
    }

    @Override
    public void draw(Renderer g) {
        super.draw(g);
        g.drawBox(x, y, width, height, color, image, text, opacity, font, textColor);
    }
}
