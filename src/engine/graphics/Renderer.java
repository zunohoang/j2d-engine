package engine.graphics;

import engine.maths.Vector2D;
import engine.maths.Vector3D;
import engine.objects.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

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

    public void drawImage(Image img, Vector2D position, Vector2D size, Vector3D rotation) {
        Graphics2D g2d = (Graphics2D) g;

        int x = (int) position.x;
        int y = (int) position.y;
        int w = (int) size.x;
        int h = (int) size.y;

        // Xác định tâm của ảnh (điểm (x, y) sẽ là tâm)
        int centerX = x;
        int centerY = y;

        // Lưu trạng thái hiện tại của Graphics2D
        AffineTransform oldTransform = g2d.getTransform();

        // Tạo một transform mới
        AffineTransform transform = new AffineTransform();

        // Dịch đến vị trí vẽ (đặt tâm ảnh ở (x, y))
        transform.translate(centerX, centerY);

        // Xoay quanh tâm ảnh
        if (rotation.z != 0) {
            transform.rotate(Math.toRadians(rotation.z));
        }

        // Lật ảnh nếu cần
        int flipX = (rotation.x != 0) ? -1 : 1; // Lật ngang nếu rotation.x khác 0
        int flipY = (rotation.y != 0) ? -1 : 1; // Lật dọc nếu rotation.y khác 0
        transform.scale(flipX, flipY);

        // Dịch ngược lại để vẽ đúng vị trí từ tâm ra ngoài
        transform.translate(-w / 2.0, -h / 2.0);

        // Áp dụng transform và vẽ ảnh
        g2d.setTransform(transform);
        g2d.drawImage(img, 0, 0, w, h, null);

        // Khôi phục transform ban đầu
        g2d.setTransform(oldTransform);
    }

    // xuay 180
    public void drawImage(Image img, int x, int y, int width, int height, GameObject gameObject) {
        boolean isFlip = true;  // Changed Boolean to primitive boolean
        Graphics2D g2d = (Graphics2D) g;

        if (isFlip) {
            // Save the original transform
            AffineTransform originalTransform = g2d.getTransform();

            // Create a flip transform (horizontal flip)
            AffineTransform flipTransform = new AffineTransform();
            flipTransform.translate(x + width/2, y + height/2);  // Move to center position
            flipTransform.scale(-1, 1);  // Flip horizontally
            flipTransform.translate(0, 0);  // Adjust for drawing

            // Apply the transform
            g2d.setTransform(flipTransform);

            // Draw the image (now flipped)
            g2d.drawImage(img, x - width/2, y - height/2, width, height, null);

            // Restore the original transform
            g2d.setTransform(originalTransform);
        } else {
            // Normal drawing without flip
            g2d.drawImage(img, x - width/2, y - height/2, width, height, null);
        }
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
    public void drawString(String text, int x, int y, Color color, Font font) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, x, y);
    }

    // draw box co opacity, text, color, image
    public void drawBox(int x, int y, int width, int height, Color color, Image image, String text, float opacity, Font font, Color textColor) {
        if (color != null) {
            drawTransparentRect(x - width/2, y - height/2, width, height, color, opacity);
        }
        if (image != null) {
            drawTransparentImage(image, x - width/2, y - height/2, width, height, opacity);
        }
        if (text != null) {
            drawString(text, x - width/2, y - height/2, textColor, font);
        }
    }
}
