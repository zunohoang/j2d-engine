package game;

import engine.components.*;
import engine.components.ui.ImageRenderer;
import engine.maths.Vector2D;
import engine.objects.GameObject;
import engine.physics.CollisionManager;
import engine.scenes.Scene;
import engine.utils.Logger;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameScene extends Scene {
    private GameObject[] player = new ArrayList<>().toArray(new GameObject[4]);

    @Override
    public void start() {
        Logger.log(this, "Game Scene start");

        Transform transform = new Transform(new Vector2D(0, 0), 0, new Vector2D(0, 0));
        GameObject background = new GameObject("Background", transform);
        background.addComponent(new ImageRenderer("assets/images/home-bg.jpg"));

        addObject(background);

        player[0] = new GameObject("Player", new Transform(new Vector2D(100, 100), 0, new Vector2D(0, 0)));
        player[0].addComponent(new CircleCollider(30));
        player[0].addComponent(new TopDownRigidbody(new Vector2D(5, -5), 1));
        player[0].addComponent(new SpriteRenderer());
        player[0].addComponent(new CoordinateXY());

        player[1] = new GameObject("Player", new Transform(new Vector2D(200, 200), 0, new Vector2D(0, 0)));
        player[1].addComponent(new CircleCollider(50));
        player[1].addComponent(new TopDownRigidbody(new Vector2D(5, -5), 1));
        player[1].addComponent(new SpriteRenderer());
        player[1].addComponent(new CoordinateXY());

        player[2] = new GameObject("Player", new Transform(new Vector2D(300, 300), 0, new Vector2D(0, 0)));
        player[2].addComponent(new CircleCollider(50));
        player[2].addComponent(new TopDownRigidbody(new Vector2D(5, -5), 1));
        player[2].addComponent(new SpriteRenderer());
        player[2].addComponent(new CoordinateXY());

        player[3] = new GameObject("Player", new Transform(new Vector2D(400, 400), 0, new Vector2D(0, 0)));
        player[3].addComponent(new CircleCollider(50));
        player[3].addComponent(new TopDownRigidbody(new Vector2D(5, -5), 1));
        player[3].addComponent(new SpriteRenderer());
        player[3].addComponent(new CoordinateXY());

        CollisionManager.register(player[0]);
        CollisionManager.register(player[1]);
        CollisionManager.register(player[2]);
        CollisionManager.register(player[3]);

        addObject(player[0]);
        addObject(player[1]);
        addObject(player[2]);
        addObject(player[3]);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(engine.core.KeyInput.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            CollisionManager.unregisterAll();
            engine.scenes.SceneManager.loadScene(new MenuScene());
        }
    }
}
