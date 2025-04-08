package engine.components;

import engine.components.ui.ImageRenderer;
import engine.graphics.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Animation extends Component {

    private List<String> imagePath;
    private int loop = 1;
    private int currentLoop = 0;
    private float lastTime = 0;
    private float sumTime = 0;
    private int currentImageIndex = 0;

    public Animation(List<String> imagePath){
        this.imagePath = imagePath;
    }

    public void increase(){
        currentImageIndex += 1;
        if(currentImageIndex >= imagePath.size()) currentImageIndex = 0;
    }

    public int getCurrentImageIndex(){
        return this.currentImageIndex;
    }

    public void setCurrentImageIndex(int currentImageIndex) {
        this.currentImageIndex = currentImageIndex;
    }

    public int getLength(){
        return imagePath.size();
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }

    public void resetLoop() {
        currentLoop = 0;
        currentImageIndex = 0;
        lastTime = 0;
        sumTime = 0;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(loop == -2) {
            getGameObject().getComponent(ImageRenderer.class).loadImage(imagePath.get(currentImageIndex));
        }
        sumTime += deltaTime;
        if(currentImageIndex >= imagePath.size()) {
            if(loop != -1) {
                currentLoop += 1;
                if (currentLoop >= loop) {
                    currentLoop = 0;
                    return;
                }
            }
            currentImageIndex = 0;
        }
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