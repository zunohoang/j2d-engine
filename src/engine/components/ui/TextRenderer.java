package engine.components.ui;

import engine.components.Component;
import engine.graphics.Renderer;

import java.awt.*;

public class TextRenderer extends Component {
    private String text;
    private int fontSize;
    private Font fontName;
    private Color color;

    @Override
    public void draw(Renderer g) {
        super.draw(g);
        g.drawString(text, (int) gameObject.transform.position.x, (int) gameObject.transform.position.y, color, fontName);
    }

    public TextRenderer(String text, int fontSize, Font fontName, Color color) {
        this.text = text;
        this.fontSize = fontSize;
        this.fontName = fontName;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public Font getFontName() {
        return fontName;
    }

    public void setFontName(Font fontName) {
        this.fontName = fontName;
    }
}
