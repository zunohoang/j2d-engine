package engine.scenes;

import java.awt.*;

public class SceneManager {
    private static Scene currentScene;

    public static void loadScene(Scene newScene) {
        currentScene = newScene;
        currentScene.start();
    }

    public static void update(float deltaTime) {
        if (currentScene != null) currentScene.update(deltaTime);
    }

    public static void draw(Graphics g) {
        if (currentScene != null) currentScene.draw(g);
    }
}

