package engine.scenes;

import engine.objects.GameObject;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    protected List<GameObject> gameObjects = new ArrayList<>();

    public abstract void start(); // Khởi tạo đối tượng trong scene
    public void update(float deltaTime) {
        for (GameObject obj : gameObjects) obj.update(deltaTime);
    }
    public void draw(Graphics g) {
        for (GameObject obj : gameObjects) obj.draw(g);
    }
    public void addObject(GameObject obj) {
        gameObjects.add(obj);
    }

}

