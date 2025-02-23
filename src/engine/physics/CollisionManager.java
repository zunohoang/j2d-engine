package engine.physics;

import engine.components.*;
import engine.objects.GameObject;
import engine.utils.LOG_TYPE;
import engine.utils.Logger;
import game.GameConfig;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {
    private static List<GameObject> collidableObjects = new ArrayList<>();

    public static void register(GameObject obj) {
        collidableObjects.add(obj);
    }

    public static void unregisterAll(){
        collidableObjects.clear();
    }

    // Giai phong doi tuong khi khong con su dung
    public static void unregister(GameObject obj) {
        collidableObjects.remove(obj);
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
                    Logger.log(CollisionManager.class, "Collision detected", LOG_TYPE.INFO);
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
        float screenWidth = GameConfig.SCREEN_WIDTH;
        float screenHeight = GameConfig.SCREEN_HEIGHT;

        // Phản xạ theo biên trái
        if (collider.getGameObject().transform.position.x - collider.getRadius() < 0) {
            obj.transform.position.x = collider.getRadius();  // Đặt lại vị trí sát mép tường
            rb.getVelocity().x = -rb.getVelocity().x;  // Đổi hướng & giảm tốc 20%
        }

        // Phản xạ theo biên phải
        if (collider.getGameObject().transform.position.x + collider.getRadius() > screenWidth) {
            obj.transform.position.x = screenWidth - collider.getRadius();
            rb.getVelocity().x = -rb.getVelocity().x;
        }

        // Phản xạ theo biên trên
        if (collider.getGameObject().transform.position.y - collider.getRadius() < 0) {
            obj.transform.position.y = collider.getRadius();
            rb.getVelocity().y = -rb.getVelocity().y;
        }

        // Phản xạ theo biên dưới
        if (collider.getGameObject().transform.position.y + collider.getRadius() > screenHeight) {
            obj.transform.position.y = screenHeight - collider.getRadius();
            rb.getVelocity().y = -rb.getVelocity().y;
        }
    }

}

