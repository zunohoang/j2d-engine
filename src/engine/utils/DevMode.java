package engine.utils;

import engine.scenes.Scene;

public class DevMode {
    public static void run(Scene scene){
        // khi lick phim R thi reload
        if(engine.core.KeyInput.isKeyPressed(java.awt.event.KeyEvent.VK_R)) {
            engine.scenes.SceneManager.loadScene(scene);
        }
    }
}
