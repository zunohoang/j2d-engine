package game;

import engine.components.CoordinateXY;
import engine.components.SpriteRenderer;
import engine.components.Transform;
import engine.components.ui.Button;
import engine.components.ui.ImageRenderer;
import engine.components.ui.RectTransform;
import engine.graphics.UILayoutParser;
import engine.maths.Vector2D;
import engine.objects.GameObject;
import engine.scenes.Scene;
import engine.scenes.SceneManager;
import engine.utils.Logger;

public class MenuScene extends Scene {
    @Override
    public void start() {
        Logger.log(this ,"Menu Scene start");

//        Transform transform = new Transform(new Vector2D((float) GameConfig.SCREEN_WIDTH /2, (float) GameConfig.SCREEN_HEIGHT /2), 0, new Vector2D(0, 0));
//        GameObject background = new GameObject("Background", transform);
//        background.addComponent(new RectTransform(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
//        background.addComponent(new ImageRenderer("assets/images/home-bg.jpg"));
//        addObject(background);
//
//        Transform transform1 = new Transform(new Vector2D(600, 600), 0, new Vector2D(0, 0));
//        GameObject button = new GameObject("Button", transform1);
//        button.addComponent(new RectTransform(200, 100));
//        button.addComponent(new ImageRenderer("assets/images/game-type-1.jpg"));
//        button.addComponent(new Button(() -> {
//            // script khi click button
//            SceneManager.loadScene(new GameScene());
//        }));
//
//        Transform transform2 = new Transform(new Vector2D(100, 100  ), 0, new Vector2D(0, 0));
//        GameObject ball = new GameObject("Ball", transform2);
//        ball.addComponent(new RectTransform(100, 100));
//        ball.addComponent(new ImageRenderer("assets/images/ball.png"));
//
//        Transform transform3 = new Transform(new Vector2D(100, 100  ), 0, new Vector2D(0, 0));
//        GameObject boxUser = new GameObject("Box User", transform3);
//        boxUser.addComponent(new RectTransform(100, 100));
//        boxUser.addComponent(new ImageRenderer("assets/images/arrow.png"));
//
//
//        addObject(boxUser);
//        addObject(button);
//        addObject(ball);

        UILayoutParser.loadLayout("assets/layout/menu.xml", this);

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(engine.core.KeyInput.isKeyPressed(java.awt.event.KeyEvent.VK_ENTER)) {
            SceneManager.loadScene(new GameScene());
        }
    }
}
