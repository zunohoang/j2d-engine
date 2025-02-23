package engine.graphics;

import java.awt.*;

public class Renderer {

    private Graphics g;

    public Renderer(Graphics g) {
        this.g = g;
    }

    public void setGraphics(Graphics g) {
        this.g = g;
    }

    public void drawRect(int x, int y, int width, int height) {
        g.drawRect(x - width/2, y - height/2, width, height);
    }

    public void fillRect(int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }

    public void drawString(String text, int x, int y) {
        g.drawString(text, x, y);
    }

    public void setColor(Color color) {
        g.setColor(color);
    }

    public void setFont(Font font) {
        g.setFont(font);
    }

    public void drawImage(Image img, int x, int y, int width, int height) {
        g.drawImage(img, x - width/2, y - height/2, width, height, null);
    }

    public void drawImage(Image img, int x, int y) {
        g.drawImage(img, x, y, null);
    }

    // line
    public void drawLine(int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
    }

    // circle
    public void drawCircle(int x, int y, int radius) {
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public void fillCircle(int x, int y, int radius) {
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    // polygon
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        g.drawPolygon(xPoints, yPoints, nPoints);
    }

    // fill polygon
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        g.fillPolygon(xPoints, yPoints, nPoints);
    }

    // triangle
    public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        int[] xPoints = {x1, x2, x3};
        int[] yPoints = {y1, y2, y3};
        drawPolygon(xPoints, yPoints, 3);
    }

    // fill triangle
    public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        int[] xPoints = {x1, x2, x3};
        int[] yPoints = {y1, y2, y3};
        fillPolygon(xPoints, yPoints, 3);
    }

    // fillover
    public void fillOver(int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }

    // fillOval
    public void fillOval(int x, int y, int width, int height) {
        g.fillOval(x, y, width, height);
    }

    // dispose
    public void dispose() {
        g.dispose();
    }

    // ve co do mo
    public void drawTransparentImage(Image image,int x, int y, int width, int height, float opacity) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.drawImage(image, x, y, width, height, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    // ve hinh tron co do mo
    public void drawTransparentCircle(int x, int y, int radius, float opacity) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    // ve hinh vuong mo
    public void drawTransparentRect(int x, int y, int width, int height, Color color,float opacity) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    // viet chu voi color, font chu, font size
    public void drawString(String text, int x, int y, Color color, Font font, int fontSize) {
        g.setColor(color);
        g.setFont(new Font(font.getName(), font.getStyle(), fontSize));
        g.drawString(text, x, y);
    }
}
