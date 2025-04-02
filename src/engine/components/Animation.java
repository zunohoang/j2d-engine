package engine.components;

import engine.components.ui.ImageRenderer;
import engine.graphics.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Animation extends Component {

    private List<String> imagePath;
    private float lastTime = 0;
    private float sumTime = 0;
    private int currentImageIndex = 0;

    public Animation(List<String> imagePath){
        this.imagePath = imagePath;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        sumTime += deltaTime;
        if(currentImageIndex >= imagePath.size()) currentImageIndex = 0;
        if(sumTime - lastTime > 0.1) {
        try {
            getGameObject().getComponent(ImageRenderer.class).loadImage(imagePath.get(currentImageIndex));
            lastTime = deltaTime;
            currentImageIndex += 1;
            sumTime = 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        }
    }

    @Override
    public void draw(Renderer g) {
        super.draw(g);
    }
}