package engine.physics;

import engine.components.CircleCollider;
import engine.components.Rigidbody;
import engine.components.BoxCollider;
import engine.components.TopDownRigidbody;
import engine.objects.GameObject;

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
        float dx = rbB.getGameObject().position.x - rbA.getGameObject().position.x;
        float dy = rbB.getGameObject().position.y - rbA.getGameObject().position.y;
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

        System.out.println("Xử lý va chạm giữa " + objA.name + " và " + objB.name);
    }
}
