package engine.scenes;

import engine.objects.GameObject;
import engine.physics.CollisionManager;
import engine.utils.DevMode;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    protected List<GameObject> gameObjects = new ArrayList<>();
    protected List<GameObject> pendingObjects = new ArrayList<>();
    private List<GameObject> renderObjects = new ArrayList<>(); // Danh sách an toàn cho render

    protected Thread physicsThread = new Thread(() -> {
        while (true) {
            CollisionManager.checkCollisions();
            try {
                Thread.sleep(10); // Kiểm tra va chạm mỗi 10ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    public abstract void start(); // Khởi tạo đối tượng trong scene

    public void update(float deltaTime) {
        // Cập nhật gameObjects một cách an toàn
        synchronized (pendingObjects) {
            gameObjects.addAll(pendingObjects);
            pendingObjects.clear();
        }

        for (GameObject obj : gameObjects) {
            obj.update(deltaTime);
        }

        // Cập nhật danh sách renderObjects an toàn
        synchronized (renderObjects) {
            renderObjects = new ArrayList<>(gameObjects); // Clone danh sách
        }
    }

    public void fixedUpdate(float deltaTime) {
        for (GameObject obj : gameObjects) {
            obj.fixedUpdate(deltaTime);
        }
    }

    public void lateUpdate(float deltaTime) {
        for (GameObject obj : gameObjects) {
            obj.lateUpdate(deltaTime);
        }
    }

    public void draw(Graphics g) {
        synchronized (renderObjects) {
            for (GameObject obj : renderObjects) {
                obj.draw(g);
            }
        }
    }

    public void addObject(GameObject obj) {
        synchronized (pendingObjects) {
            pendingObjects.add(obj);
        }
    }

    public void removeObject(GameObject obj) {
        synchronized (pendingObjects) {
            pendingObjects.remove(obj);
        }
    }
}
