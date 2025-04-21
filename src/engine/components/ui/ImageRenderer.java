package engine.components.ui;

import engine.components.Component;
import engine.graphics.Renderer;
import engine.maths.Vector2D;
import engine.objects.GameObject;
import engine.scenes.Scene;
import engine.scenes.SceneManager;
import engine.utils.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRenderer extends Component {
    private BufferedImage image;
    private int width = -1, height = -1;
    private boolean fullscreen = false;
    private float x, y;
    private boolean center = true;

    public ImageRenderer(){}

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ImageRenderer(String imagePath) {
        loadImage(imagePath);
    }

    public ImageRenderer(BufferedImage imagePath, float w, float h) {
        this.image = imagePath;
    }

    public ImageRenderer(String imagePath, float x, float y) {
        loadImage(imagePath);
        this.x = x;
        this.y = y;
    }

    public ImageRenderer(String imagePath, float x, float y, int w, int h, boolean center) {
        loadImage(imagePath);
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.center = center;
    }

    public ImageRenderer(String imagePath, boolean fullscreen) {
        loadImage(imagePath);
        this.fullscreen = fullscreen;
    }

    public ImageRenderer(String imagePath, int width, int height) {
        loadImage(imagePath);
        this.width = width;
        this.height = height;
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
            if(image != null && width == -1 && height == -1) {
                width = image.getWidth();
                height = image.getHeight();
            }
            if(fullscreen) {
                width = SceneManager.getWidth();
                height = SceneManager.getHeight() - 40;
            }
        }
    }

    @Override
    public void draw(Renderer g) {
        if (image != null && gameObject != null) {
            Vector2D globalPosition = getGlobalPosition(gameObject);

            if (!center) {
                if (width == -1 && height == -1) {
                    g.drawImage(image, (int) globalPosition.x + (int) x + width/2, (int) globalPosition.y + (int) y + height/2);
                } else {
                    g.drawImage(
                            image,
                            new Vector2D((int) globalPosition.x + (int) x + width/2, (int) globalPosition.y + (int) y + height/2),
                            new Vector2D(width * gameObject.transform.scale.x, height * gameObject.transform.scale.y),
                            gameObject.transform.rotation
                    );
                }
                return;
            }

            if (width == -1 && height == -1) {
                g.drawImage(image, (int) globalPosition.x + (int) x, (int) globalPosition.y + (int) y);
            } else {
                g.drawImage(
                        image,
                        new Vector2D((int) globalPosition.x + (int) x, (int) globalPosition.y + (int) y),
                        new Vector2D(width * gameObject.transform.scale.x, height * gameObject.transform.scale.y),
                        gameObject.transform.rotation
                );
            }
        }
    }

    // Hàm để cộng dồn vị trí của tất cả cha
    private Vector2D getGlobalPosition(GameObject obj) {
        Vector2D pos = new Vector2D(obj.transform.position.x, obj.transform.position.y);
        GameObject current = obj.parent;

        while (current != null) {
            pos.x += current.transform.position.x;
            pos.y += current.transform.position.y;
            current = current.parent;
        }

        return pos;
    }

}
