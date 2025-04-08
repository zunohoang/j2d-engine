package engine.interfaces;

import engine.objects.GameObject;

public interface ICollisionListener {
    void onCollision(GameObject other);
}
