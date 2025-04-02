package engine.components.ui;

import engine.components.Component;
import engine.graphics.Renderer;
import engine.maths.Vector2D;
import engine.scenes.Scene;
import engine.scenes.SceneManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRenderer extends Component {
    private BufferedImage image;
    private int width = -1, height = -1;

    public ImageRenderer(String imagePath) {
        loadImage(imagePath);
    }

    public ImageRenderer(BufferedImage image, int width, int height) {
        this.image = image;
    }

    // Tải ảnh từ file
    public void loadImage(String path) {
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
            if(image != null) {
                width = image.getWidth();
                height = image.getHeight();
            }
        }
    }

    @Override
    public void draw(Renderer g) {
        if (image != null && gameObject != null) {
            if(width == -1 && height == -1) {
                g.drawImage(image, (int) gameObject.transform.position.x, (int) gameObject.transform.position.y);
//            } else g.drawImage(image, (int) gameObject.transform.position.x, (int) gameObject.transform.position.y, (int) (width * gameObject.transform.scale.x), (int) (height * gameObject.transform.scale.y), gameObject);
            } else {
                g.drawImage(image, gameObject.transform.position, new Vector2D(width * gameObject.transform.scale.x, height * gameObject.transform.scale.y), gameObject.transform.rotation);
            }
        }
    }
}
