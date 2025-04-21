package game.scripts;

import engine.components.*;
import engine.maths.Vector2D;
import engine.physics.Physics;
import engine.utils.LOG_TYPE;
import engine.utils.Logger;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class NormalAttack extends Component {

    private Animation animation;
    private boolean isPressedLast = false;
    private long lastPressedTime = 0;
    private Rigidbody rb;
    private int key;

    public NormalAttack(int _key) {
        this.key = _key;
    }

    @Override
    public void start() {
        super.start();
        Animations animations = gameObject.getComponent(Animations.class);
        if(animations == null) {
            Logger.log(this, "Can't not get animations component", LOG_TYPE.WARN);
        }
        assert animations != null;
        System.out.println("OK");
        this.animation = animations.getAnimation("normalAttacking");
        rb = gameObject.getComponent(Rigidbody.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (engine.core.KeyInput.isKeyPressed(key)
                && !isPressedLast
                && System.currentTimeMillis() - lastPressedTime > 70
        ) {
            isPressedLast = true;
            gameObject.currentState = "normalAttacking";
            if(animation != null) {
                animation.increase();
                rb.velocity.x = 500 * (gameObject.transform.rotation.x == 0 ? 1 : -1);
                Logger.log(this, "Current frame: " + animation.getCurrentImageIndex());
            }
            lastPressedTime = System.currentTimeMillis();
            attack();
        } else {
            isPressedLast = false;
        }
    }

    public void attack() {
        List<BoxCollider> boxColliders = Physics.overlapCircleAll(gameObject.getComponent(BoxCollider.class), 10);
        for(BoxCollider boxCollider : boxColliders) {
            Logger.log(this, "GAMEOBJECT: " + boxCollider.getGameObject().getName());
                HealthPoint healthPoint = boxCollider.getGameObject().getComponent(HealthPoint.class);
                if (healthPoint != null) {
                    healthPoint.takeDamage(10);
                    boxCollider.getGameObject().transform.position.add(5, 0);
                    Logger.log(this, "Attack " + boxCollider.getGameObject().getName() + " with damage 10");
                }
        }
    }
}
