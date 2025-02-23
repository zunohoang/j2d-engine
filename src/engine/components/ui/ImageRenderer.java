package engine.components.ui;

import engine.components.Component;
import engine.graphics.Renderer;
import engine.scenes.Scene;
import engine.scenes.SceneManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRenderer extends Component {
    private BufferedImage image;
    private int width = 0, height = 0;

    public ImageRenderer(String imagePath) {
        loadImage(imagePath);
    }

    public ImageRenderer(BufferedImage image, int width, int height) {
        this.image = image;
    }

    // Tải ảnh từ file
    private void loadImage(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        super.start();
        if (gameObject != null) {
            RectTransform rectTransform = (RectTransform) gameObject.getComponent(RectTransform.class);
            if (rectTransform != null) {
                width = rectTransform.getWidth();
                height = rectTransform.getHeight();
            }
        }
    }

    @Override
    public void draw(Renderer g) {
        if (image != null && gameObject != null) {
            g.drawImage(image, (int) gameObject.transform.position.x, (int) gameObject.transform.position.y, width, height);
        }
    }
}
