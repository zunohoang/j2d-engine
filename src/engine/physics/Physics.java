package engine.physics;

import engine.components.CircleCollider;
import engine.components.Rigidbody;
import engine.components.BoxCollider;
import engine.components.TopDownRigidbody;
import engine.maths.Vector2D;
import engine.objects.GameObject;
import engine.utils.LOG_TYPE;
import engine.utils.Logger;

public class Physics {
    public static void resolveCollision(GameObject objA, GameObject objB) {
        TopDownRigidbody rbA = objA.getComponent(TopDownRigidbody.class);
        TopDownRigidbody rbB = objB.getComponent(TopDownRigidbody.class);
        CircleCollider colliderA = objA.getComponent(CircleCollider.class);
        CircleCollider colliderB = objB.getComponent(CircleCollider.class);

        if (rbA == null || rbB == null || colliderA == null || colliderB == null) return;
        if (!colliderA.isColliding(colliderB)) return;

        float m1 = rbA.getMass(), m2 = rbB.getMass();

        // Vector vị trí
        float dx = rbB.getGameObject().transform.position.x - rbA.getGameObject().transform.position.x;
        float dy = rbB.getGameObject().transform.position.y - rbA.getGameObject().transform.position.y;
        float distanceSquared = dx * dx + dy * dy;
        if (distanceSquared == 0) return; // Tránh chia cho 0

        // Vector vận tốc
        float dvx = rbB.getVelocity().x - rbA.getVelocity().x;
        float dvy = rbB.getVelocity().y - rbA.getVelocity().y;

        // Tích vô hướng (dot product)
        float dotProduct = dvx * dx + dvy * dy;

        // Công thức tính vận tốc sau va chạm
        float factorA = (2 * m2 * dotProduct) / ((m1 + m2) * distanceSquared);
        float factorB = (2 * m1 * dotProduct) / ((m1 + m2) * distanceSquared);

        rbA.getVelocity().x += factorA * dx;
        rbA.getVelocity().y += factorA * dy;
        rbB.getVelocity().x -= factorB * dx;
        rbB.getVelocity().y -= factorB * dy;

        // Tách đối tượng để tránh dính nhau
        float distance = (float) Math.sqrt(distanceSquared);
        float overlap = (colliderA.getRadius() + colliderB.getRadius()) - distance;

        if (overlap > 0) {
            // Vector pháp tuyến (normal vector)
            float nx = dx / distance;
            float ny = dy / distance;

            // Dịch chuyển hai đối tượng dọc theo vector pháp tuyến
            float separation = overlap / 2; // Chia đều khoảng cách tách
            objA.transform.position.x -= separation * nx;
            objA.transform.position.y -= separation * ny;
            objB.transform.position.x += separation * nx;
            objB.transform.position.y += separation * ny;
        }

        Logger.log(Physics.class, "Collision resolved", LOG_TYPE.SUCCESS);
    }

    // safe view
    // đay là các hình hộp, check va chạm tường nữa, không có phải lực nếu ngang thì k di chuyển được, nếu dọc thì không khng bị rơi
    public static void resolveCollisionV2(GameObject objA, GameObject objB) {
        BoxCollider colliderA = objA.getComponent(BoxCollider.class);
        BoxCollider colliderB = objB.getComponent(BoxCollider.class);

        if (colliderA == null || colliderB == null) return;
        if (!colliderA.isColliding(colliderB)) return;

        // Lấy thông tin vị trí và kích thước
        Vector2D centerA = new Vector2D(objA.transform.position.x, objA.transform.position.y);
        Vector2D centerB = new Vector2D(objB.transform.position.x, objB.transform.position.y);
        float halfWidthA = colliderA.width / 2f;
        float halfHeightA = colliderA.height / 2f;
        float halfWidthB = colliderB.width / 2f;
        float halfHeightB = colliderB.height / 2f;

        // Tính toán overlap trên từng trục
        float overlapX = (halfWidthA + halfWidthB) - Math.abs(centerB.x - centerA.x);
        float overlapY = (halfHeightA + halfHeightB) - Math.abs(centerB.y - centerA.y);

        // Xác định hướng va chạm chính (trục có overlap nhỏ hơn)
        if (overlapX < overlapY) {
            // Va chạm ngang (trục X)
            if (centerA.x < centerB.x) {
                // Vật thể A ở bên trái B
                objA.transform.position.x = centerB.x - (halfWidthA + halfWidthB);
            } else {
                // Vật thể A ở bên phải B
                objA.transform.position.x = centerB.x + (halfWidthA + halfWidthB);
            }

            // Xử lý vật lý (nếu có Rigidbody)
            Rigidbody rbA = objA.getComponent(Rigidbody.class);
            if (rbA != null) {
                rbA.velocity.x = 0;
                // Thêm độ trễ nhỏ để tránh dính
                objA.transform.position.x += (centerA.x < centerB.x) ? -0.01f : 0.01f;
            }
        } else {
            // Va chạm dọc (trục Y)
            if (centerA.y < centerB.y) {
                // Vật thể A ở dưới B
                objA.transform.position.y = centerB.y - (halfHeightA + halfHeightB);
            } else {
                // Vật thể A ở trên B
                objA.transform.position.y = centerB.y + (halfHeightA + halfHeightB);
            }

            // Xử lý vật lý (nếu có Rigidbody)
            Rigidbody rbA = objA.getComponent(Rigidbody.class);
            if (rbA != null) {
                rbA.velocity.y = 0;
                // Thêm độ trễ nhỏ để tránh dính
                objA.transform.position.y += (centerA.y < centerB.y) ? -0.01f : 0.01f;
            }
        }

        Logger.log(Physics.class, "Collision resolved between " + objA.name + " and " + objB.name, LOG_TYPE.INFO);
    }
}
