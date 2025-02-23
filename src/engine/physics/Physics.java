package engine.physics;

import engine.components.CircleCollider;
import engine.components.Rigidbody;
import engine.components.BoxCollider;
import engine.components.TopDownRigidbody;
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
}
