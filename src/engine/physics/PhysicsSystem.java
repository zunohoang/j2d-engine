package engine.physics;


import engine.components.Rigidbody;
import engine.components.BoxCollider;
import engine.objects.GameObject;
import engine.scenes.SceneManager;
import engine.utils.Logger;
import engine.utils.LOG_TYPE;

public class PhysicsSystem {
    public static final float GRAVITY = 980f; // Đơn vị: pixels/s²

    public static void update(float deltaTime) {
        // Cập nhật tất cả rigidbody trong scene
        for (GameObject obj : SceneManager.getCurrentScene().getGameObjects()) {
            Rigidbody rb = obj.getComponent(Rigidbody.class);
            if (rb != null) {
                // Xử lý trước khi update vật lý
                beforePhysicsUpdate(rb);

                // Cập nhật vật lý
                rb.update(deltaTime);

                // Xử lý sau khi update vật lý
                afterPhysicsUpdate(rb);
            }
        }
    }

    private static void beforePhysicsUpdate(Rigidbody rb) {
        // Reset trạng thái grounded mỗi frame
    }

    private static void afterPhysicsUpdate(Rigidbody rb) {
        // Kiểm tra và xử lý va chạm với mặt đất
        checkGroundCollision(rb);
    }

    private static void checkGroundCollision(Rigidbody rb) {
        // Giả sử có phương thức kiểm tra va chạm với mặt đất
        BoxCollider collider = rb.getGameObject().getComponent(BoxCollider.class);
    }

    public static void resolveCollision(GameObject objA, GameObject objB) {
        // Xử lý va chạm giữa các vật thể
        // ... (implementation tương tự như trước)
    }
}