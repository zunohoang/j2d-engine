package game.scenes;

import engine.components.*;
import engine.components.ui.ImageRenderer;
import engine.graphics.animation.AnimationRefactor;
import engine.maths.Vector2D;
import engine.maths.Vector3D;
import engine.objects.Camera;
import engine.objects.GameObject;
import engine.physics.CollisionManager;
import engine.scenes.Scene;
import engine.utils.Logger;
import game.configs.GameConfig;
import game.gameobjects.HUBUpdate;
import game.scripts.HealthPoint;
import game.scripts.NormalAttack;
import game.scripts.PlayerController;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class GameScene extends Scene {
    private GameObject playerx, playery;
    private GameObject head;
    @Override
    public void start() {
        Logger.log(this, "Game Scene start");

        Transform transform = new Transform(new Vector2D(0, 0), new Vector3D(0, 0, 0), new Vector2D(1, 1));
        GameObject background = new GameObject("Background", transform);
        background.addComponent(new ImageRenderer("assets/images/round-bg/UchihaHideout-0.png", true));
        background.addComponent(new SpriteRenderer());
        background.addComponent(new CoordinateXY());
        Camera.getCamera().setPosition(new Vector2D(0, 0));

        addObject(background);

        GameObject titlePlayer1 = new GameObject("Title-Player1", new Transform(new Vector2D(0, 0), new Vector3D(0, 0, 0), new Vector2D( 1, 1)));
        titlePlayer1.addComponent(new ImageRenderer("assets/images/characters/naruto/profile/ItemsEffectsIcons-40.png", 0f, -50f));

        GameObject titlePlayer2 = new GameObject("Title-Player2", new Transform(new Vector2D(0, 0), new Vector3D(0, 0, 0), new Vector2D( 1, 1)));
        titlePlayer2.addComponent(new ImageRenderer("assets/images/characters/itachi/profile/ItemsEffectsIcons-52.png", 0f, -50f));

        playerx = new GameObject("Player", new Transform(new Vector2D(50, 50), new Vector3D(0, 0, 0), new Vector2D( 1, 1)));
        playerx.addComponent(new ImageRenderer("assets/images/characters/naruto/animation/standing/Naruto-101.png"));
        playerx.addObject(titlePlayer1);
        playerx.addComponent(new HealthPoint(100));
        playerx.currentState = "standing";
        playerx.addComponent(new Animations());
        playerx.addComponent(new BoxCollider(50, 60));
        playerx.addComponent(new SpriteRenderer());
        playerx.addComponent(new Rigidbody());
        playerx.addComponent(new CoordinateXY());
        playerx.getComponent(Animations.class).setAnimations(AnimationRefactor.loadAnimation("Naruto"));

        playerx.addComponent(new PlayerController(KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_J));
        playerx.addComponent(new NormalAttack(KeyEvent.VK_J));

        playery = new GameObject("Player", new Transform(new Vector2D(100, 50), new Vector3D(0, 0, 0), new Vector2D( 1.5f, 1)));
        playery.addComponent(new ImageRenderer("assets/images/characters/itachi/animation/standing/Itachi-97.png"));
        playery.addObject(titlePlayer2);
        playery.currentState = "standing";
        playery.addComponent(new Animations());
        playery.addComponent(new BoxCollider(50, 78));
        playery.addComponent(new SpriteRenderer());
        playery.addComponent(new Rigidbody());
        playery.addComponent(new CoordinateXY());
        playery.getComponent(Animations.class).setAnimations(AnimationRefactor.loadAnimation("Itachi"));
        playery.addComponent(new HealthPoint(100));
        playery.addComponent(new PlayerController(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_NUMPAD4));
        playery.addComponent(new NormalAttack(KeyEvent.VK_NUMPAD4));

        CollisionManager.register(playery);
        addObject(playery);

        CollisionManager.register(playerx);
        addObject(playerx);

        GameObject gameHeaderLeft = new GameObject("Left", new Transform(new Vector2D(-GameConfig.SCREEN_WIDTH/2f + 150, 0), new Vector3D(0, 0, 0), new Vector2D( 1, 1)));
        gameHeaderLeft.addComponent(new ImageRenderer("assets/images/ItemsEffectsIcons-30.png", 300,70));
        GameObject healthLeft = new GameObject("Health1", new Transform(new Vector2D(30,5), new Vector3D(0,0,0), new Vector2D(1,1)));
        healthLeft.addComponent(new ImageRenderer("assets/images/ItemsEffectsIcons-34.png",-112,-10, 227,20, false));
        gameHeaderLeft.addObject(healthLeft);
        GameObject gameHeaderRight = new GameObject("Right", new Transform(new Vector2D(GameConfig.SCREEN_WIDTH/2f - 150, 0), new Vector3D(180, 0, 0), new Vector2D( 1, 1)));
        gameHeaderRight.addComponent(new ImageRenderer("assets/images/ItemsEffectsIcons-29.png", 300,70));
        GameObject healthRight = new GameObject("Health2", new Transform(new Vector2D(-30,5), new Vector3D(180,0,0), new Vector2D(1,1)));
        healthRight.addComponent(new ImageRenderer("assets/images/ItemsEffectsIcons-35.png",-114,-10, 227,20, false));
        gameHeaderRight.addObject(healthRight);
        head = new GameObject("GameHeader", new Transform(new Vector2D(0, -GameConfig.SCREEN_HEIGHT/2f + 50), new Vector3D(0,0,0), new Vector2D(1,1)));
        head.addObject(gameHeaderLeft);
        head.addObject(gameHeaderRight);
//        head.addComponent(new HUBUpdate(playerx, playery));
        head.addComponent(new CoordinateXY());
        addObject(head);

        GameObject boxWall = new GameObject("Ground", new Transform(new Vector2D(100, 420), new Vector3D(0, 0, 0), new Vector2D(1, 1)));
        boxWall.addComponent(new BoxCollider(2000, 100));
        boxWall.addComponent(new SpriteRenderer());
        boxWall.addComponent(new CoordinateXY());

        CollisionManager.register(boxWall);
        addObject(boxWall);

        GameObject boxWall2 = new GameObject("Ground", new Transform(new Vector2D(-GameConfig.SCREEN_WIDTH/2f, -GameConfig.SCREEN_HEIGHT/2f), new Vector3D(0, 0, 0), new Vector2D(1, 1)));
        boxWall2.addComponent(new BoxCollider(20, 2000));
        boxWall2.addComponent(new SpriteRenderer());
        boxWall2.addComponent(new CoordinateXY());

        CollisionManager.register(boxWall2);
        addObject(boxWall2);

        GameObject boxWall3 = new GameObject("Ground", new Transform(new Vector2D(GameConfig.SCREEN_WIDTH/2f, GameConfig.SCREEN_HEIGHT/2f), new Vector3D(0, 0, 0), new Vector2D(1, 1)));
        boxWall3.addComponent(new BoxCollider(20, 2000));
        boxWall3.addComponent(new SpriteRenderer());
        boxWall3.addComponent(new CoordinateXY());

        CollisionManager.register(boxWall3);
        addObject(boxWall3);

        GameObject GroundInSky = new GameObject("GroundInSky", new Transform(new Vector2D(500, 176), new Vector3D(0, 0, 0), new Vector2D(1, 1)));
        GroundInSky.addComponent(new BoxCollider(500, 50));
        GroundInSky.addComponent(new SpriteRenderer());
        GroundInSky.addComponent(new CoordinateXY());

        CollisionManager.register(GroundInSky);
        addObject(GroundInSky);

        GameObject GroundInSky2 = new GameObject("GroundInSky", new Transform(new Vector2D(-500, 176), new Vector3D(0, 0, 0), new Vector2D(1, 1)));
        GroundInSky2.addComponent(new BoxCollider(500, 50));
        GroundInSky2.addComponent(new SpriteRenderer());
        GroundInSky2.addComponent(new CoordinateXY());

        CollisionManager.register(GroundInSky2);
        addObject(GroundInSky2);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Camera.getCamera().setPosition(new Vector2D(playerx.transform.position.x, playerx.transform.position.y));

        if(engine.core.KeyInput.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            CollisionManager.unregisterAll();
            engine.scenes.SceneManager.loadScene(new MenuScene());
        }
    }
}