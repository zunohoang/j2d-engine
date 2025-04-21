package game.scripts;

import engine.components.*;
import engine.core.KeyInput;
import engine.interfaces.ICollisionListener;
import engine.maths.Vector2D;
import engine.maths.Vector3D;
import engine.objects.Camera;
import engine.objects.GameObject;
import engine.utils.Logger;
import game.configs.GameConfig;

import java.awt.event.KeyEvent;
import java.util.Objects;

public class PlayerController extends Component implements ICollisionListener {
    private Rigidbody rb;
    private Animation animationJumping;
    private float moveSpeed = 300f;
    private float jumpForce = -600f; // âm vì trục y hướng xuống
    private int jumpCount = 0;
    private final int maxJumps = 2;  // Số lần nhảy tối đa
    private Vector2D startJump;
    private boolean isUpWall = false;
    private long lastTimeJ = 0;
    private long lastTimeW = 0;

    private int left, right, bottom, top, normalSkillKey;

    public PlayerController(int left, int right, int top, int bottom, int normalSkillKey){
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
        this.normalSkillKey = normalSkillKey;
    }

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
        if (KeyInput.isKeyPressed(left)) {
            if(!KeyInput.isKeyPressed(normalSkillKey)) {
                move -= 1f;
                if (jumpCount == 0) gameObject.currentState = "running";
                else gameObject.currentState = "jumping";
            } else move -= 0.2f;
            gameObject.transform.rotation = new Vector3D(180, 0, 0);
        } else if (KeyInput.isKeyPressed(right)) {
            if(!KeyInput.isKeyPressed(normalSkillKey)) {
                move += 1f;
                if (jumpCount == 0) gameObject.currentState = "running";
                else gameObject.currentState = "jumping";
            } else move += 0.2f;
            gameObject.transform.rotation = new Vector3D(0, 0, 0);
        } else if(jumpCount > 0) {
            gameObject.currentState = "jumping";
        } else  {
            if(!KeyInput.isKeyPressed(normalSkillKey) && System.currentTimeMillis() - lastTimeJ > 100) {
                gameObject.currentState = "standing";
            }
            if(KeyInput.isKeyPressed(normalSkillKey)) {
                lastTimeJ = System.currentTimeMillis();
            }
        }

        // Di chuyển ngang
        rb.velocity.x = move * moveSpeed;

        // Nhảy (double jump)
        if (KeyInput.isKeyPressed(top) && jumpCount < maxJumps && System.currentTimeMillis() - lastTimeW > 100) {
            startJump = new Vector2D(gameObject.transform.position.x, gameObject.transform.position.y);
            animationJumping.resetLoop();
            rb.velocity.y = jumpForce;  // Áp dụng lực nhảy
            jumpCount++;  // Tăng số lần nhảy
            lastTimeW = System.currentTimeMillis();
        }
    }

    public void attack() {

    }

    @Override
    public void onCollision(GameObject other) {
        try {
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

            if (other.getName().equals("Ground")) {
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
                        if (other.getName().equals("Ground") && jumpCount > 0) {
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
            if (other.getName().equals("GroundInSky")) {
                // chi khong the va cham tu tren xuong con duoi len van duoc
                Logger.log(this, "MyBottom: " + myBottom + " OtherTop: " + otherTop);

                if (!KeyInput.isKeyPressed(bottom) && myBottom <= otherTop + 10) {
                    Logger.log(this, "MyBottom: " + myBottom + " OtherTopX: " + otherTop);
                    // Va chạm từ phía trên (rơi xuống)
                    if (rb.velocity.y >= 0) {
                        gameObject.transform.position.y = otherTop - myCollider.height / 2;
                        rb.velocity.y = 0; // Dừng chuyển động theo trục Y

                        // Reset jump count khi chạm đất
                        if (other.getName().equals("GroundInSky") && jumpCount > 0) {
                            gameObject.currentState = "standing";
                        }

                        if (other.getName().equals("GroundInSky")) {
                            jumpCount = 0;
                        }
                    }
                }
            }
        } catch (Exception e){
            Logger.log(this, "ERROR: " + e.getMessage());
        }
    }

}
