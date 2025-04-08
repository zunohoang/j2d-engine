package game.scripts;

import engine.components.*;
import engine.core.KeyInput;
import engine.interfaces.ICollisionListener;
import engine.maths.Vector2D;
import engine.maths.Vector3D;
import engine.objects.Camera;
import engine.objects.GameObject;
import engine.utils.Logger;

import java.awt.event.KeyEvent;

public class PlayerController extends Component implements ICollisionListener {
    private Rigidbody rb;
    private Animation animationJumping;
    private float moveSpeed = 300f;
    private float jumpForce = -400f; // âm vì trục y hướng xuống
    private int jumpCount = 0;
    private final int maxJumps = 2;  // Số lần nhảy tối đa

    @Override
    public void start() {
        rb = gameObject.getComponent(Rigidbody.class);
        if (rb == null) {
            throw new RuntimeException("PlayerMovement requires RigidBody2D");
        }
        Animations animations = gameObject.getComponent(Animations.class);
        if (animations == null) {
            throw new RuntimeException("PlayerMovement requires Animations");
        }
        animationJumping = animations.getAnimation("jumping");
        if (animationJumping == null) {
            throw new RuntimeException("PlayerMovement requires Animation");
        }
    }

    @Override
    public void update(float deltaTime) {
        float move = 0;

        // Di chuyển trái/phải
        if (KeyInput.isKeyPressed(KeyEvent.VK_A)) {
            move -= 1;
            gameObject.currentState = "running";
            gameObject.transform.rotation = new Vector3D(180, 0, 0);
        } else if (KeyInput.isKeyPressed(KeyEvent.VK_D)) {
            move += 1;
            gameObject.currentState = "running";
            gameObject.transform.rotation = new Vector3D(0, 0, 0);
        } else if(jumpCount > 0) {
            gameObject.currentState = "jumping";
        } else {
            gameObject.currentState = "standing";
        }

        // Di chuyển ngang
        rb.velocity.x = move * moveSpeed;

        // Nhảy (double jump)
        if (KeyInput.isKeyJustPressed(KeyEvent.VK_W) && jumpCount < maxJumps) {
            animationJumping.resetLoop();
            rb.velocity.y = jumpForce;  // Áp dụng lực nhảy
            jumpCount++;  // Tăng số lần nhảy
        }

        // Cập nhật vị trí camera theo player
        Camera.getCamera().setPosition(new Vector2D(gameObject.transform.position.x, gameObject.transform.position.y));
    }

    @Override
    public void onCollision(GameObject other) {
//        Logger.log(this, "Collision with: " + other.getName());

        // Lấy collider của đối tượng hiện tại và đối tượng va chạm
        BoxCollider myCollider = gameObject.getComponent(BoxCollider.class);
        BoxCollider otherCollider = other.getComponent(BoxCollider.class);

        // Tính toán vị trí và kích thước
        float myLeft = gameObject.transform.position.x - myCollider.width / 2;
        float myRight = gameObject.transform.position.x + myCollider.width / 2;
        float myTop = gameObject.transform.position.y - myCollider.height / 2;
        float myBottom = gameObject.transform.position.y + myCollider.height / 2;

        float otherLeft = other.transform.position.x - otherCollider.width / 2;
        float otherRight = other.transform.position.x + otherCollider.width / 2;
        float otherTop = other.transform.position.y - otherCollider.height / 2;
        float otherBottom = other.transform.position.y + otherCollider.height / 2;

        // Tính toán độ xuyên sâu (penetration) theo các hướng
        float overlapX = Math.min(myRight - otherLeft, otherRight - myLeft);
        float overlapY = Math.min(myBottom - otherTop, otherBottom - myTop);

        // Xác định hướng va chạm (hướng có overlap nhỏ hơn)
        if (overlapX < overlapY) {
            // Va chạm ngang (trái/phải)
            if (myLeft < otherLeft) {
                // Va chạm từ bên trái
                gameObject.transform.position.x = otherLeft - myCollider.width / 2;
                rb.velocity.x = 0; // Dừng chuyển động theo trục X
            } else {
                // Va chạm từ bên phải
                gameObject.transform.position.x = otherRight + myCollider.width / 2;
                rb.velocity.x = 0; // Dừng chuyển động theo trục X
            }
        } else {
            // Va chạm dọc (trên/dưới)
            if (myTop < otherTop) {
                // Va chạm từ phía trên (rơi xuống)
                gameObject.transform.position.y = otherTop - myCollider.height / 2;
                rb.velocity.y = 0; // Dừng chuyển động theo trục Y

                // Reset jump count khi chạm đất
                if(other.getName().equals("Ground") && jumpCount > 0) {
                    gameObject.currentState = "standing";
                }

                if (other.getName().equals("Ground")) {
                    jumpCount = 0;
                }
            } else {
                // Va chạm từ phía dưới (nhảy lên chạm trần)
                gameObject.transform.position.y = otherBottom + myCollider.height / 2;
                rb.velocity.y = 0; // Dừng chuyển động theo trục Y
            }
        }
    }
}
