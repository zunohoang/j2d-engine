package game.scenes;

import engine.graphics.UILayoutParser;
import engine.scenes.Scene;
import engine.scenes.SceneManager;
import engine.utils.DevMode;
import engine.utils.Logger;

public class MenuScene extends Scene {
    @Override
    public void start() {
        Logger.log(this ,"Menu Scene start");

        UILayoutParser.loadLayout("assets/layout/menu.xml", this);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        DevMode.run(this);
        if(engine.core.KeyInput.isKeyPressed(java.awt.event.KeyEvent.VK_ENTER)) {
            SceneManager.loadScene(new GameScene());
        }
    }
}
