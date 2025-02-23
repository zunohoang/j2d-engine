package engine.scenes;

import java.awt.*;

public class SceneManager {
    private static Scene currentScene;
    private static Frame frame;

    public static void loadScene(Scene newScene) {
        currentScene = newScene;
        currentScene.start();
    }

    public static void setFrame(Frame _frame){
        frame = _frame;
    }

    public static int getWidth(){
        return frame.getWidth();
    }

    public static int getHeight(){
        return frame.getHeight();
    }

    public static void update(float deltaTime) {
        if (currentScene != null) currentScene.update(deltaTime);
    }

    public static void fixedUpdate(float deltaTime) {
        if (currentScene != null) currentScene.fixedUpdate(deltaTime);
    }

    public static void lateUpdate(float deltaTime) {
        if (currentScene != null) currentScene.lateUpdate(deltaTime);
    }

    public static void draw(Graphics g) {
        if (currentScene != null) currentScene.draw(g);
    }
}

