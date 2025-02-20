package game;

import engine.components.CircleCollider;
import engine.components.TopDownRigidbody;
import engine.maths.Vector2D;
import engine.objects.GameObject;
import engine.components.Rigidbody;
import engine.physics.CollisionManager;
import engine.scenes.Scene;
import engine.components.SpriteRenderer;

public class GameScene extends Scene {
    private GameObject player1, player2;

    @Override
    public void start() {
        System.out.println("GameScene Loaded");

        player1 = new GameObject("Player", 100, 100);
        player1.addComponent(new CircleCollider(25));
        player1.addComponent(new TopDownRigidbody(new Vector2D(3, 2), 100));
        player1.addComponent(new SpriteRenderer());

        player2 = new GameObject("Player", 200, 200);
        player2.addComponent(new CircleCollider(25));
        player2.addComponent(new TopDownRigidbody(new Vector2D(5, 6), 50));
        player2.addComponent(new SpriteRenderer());

        CollisionManager.register(player1);
        CollisionManager.register(player2);

        addObject(player1);
        addObject(player2);
    }
}
