package engine.physics;

import engine.components.BoxCollider;
import engine.components.CircleCollider;
import engine.components.Rigidbody;
import engine.components.TopDownRigidbody;
import engine.objects.GameObject;
import java.util.ArrayList;
import java.util.List;

public class CollisionManager {
    private static List<GameObject> collidableObjects = new ArrayList<>();

    public static void register(GameObject obj) {
        collidableObjects.add(obj);
    }

    public static void checkCollisions() {
        for (int i = 0; i < collidableObjects.size(); i++) {
            GameObject objA = collidableObjects.get(i);
            CircleCollider colliderA = objA.getComponent(CircleCollider.class);

            if (colliderA == null) continue;

            for (int j = i + 1; j < collidableObjects.size(); j++) {
                GameObject objB = collidableObjects.get(j);
                CircleCollider colliderB = objB.getComponent(CircleCollider.class);

                if (colliderB == null) continue;

                if (colliderA.isColliding(colliderB)) {
                    System.out.println(objA.name + " va chạm với " + objB.name);
                    Physics.resolveCollision(objA, objB);
                }
            }
        }
        for(GameObject obj : collidableObjects) {
            resolveCollisionWall(obj);
        }
    }

    // resolve collision wall
    public static void resolveCollisionWall(GameObject obj) {
        CircleCollider collider = obj.getComponent(CircleCollider.class);
        TopDownRigidbody rb = obj.getComponent(TopDownRigidbody.class);

        if (collider == null || rb == null) return;

        // Giả sử màn hình có kích thước 800x600
        float screenWidth = 400;
        float screenHeight = 400;

        // Phản xạ theo biên trái
        if (collider.getGameObject().position.x - collider.getRadius() < 0) {
            obj.position.x = collider.getRadius();  // Đặt lại vị trí sát mép tường
            rb.getVelocity().x = -rb.getVelocity().x;  // Đổi hướng & giảm tốc 20%
        }

        // Phản xạ theo biên phải
        if (collider.getGameObject().position.x + collider.getRadius() > screenWidth) {
            obj.position.x = screenWidth - collider.getRadius();
            rb.getVelocity().x = -rb.getVelocity().x;
        }

        // Phản xạ theo biên trên
        if (collider.getGameObject().position.y - collider.getRadius() < 0) {
            obj.position.y = collider.getRadius();
            rb.getVelocity().y = -rb.getVelocity().y;
        }

        // Phản xạ theo biên dưới
        if (collider.getGameObject().position.y + collider.getRadius() > screenHeight) {
            obj.position.y = screenHeight - collider.getRadius();
            rb.getVelocity().y = -rb.getVelocity().y;
        }
    }

}

