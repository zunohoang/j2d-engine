package engine.components;

import engine.maths.Vector2D;
import engine.objects.GameObject;
import engine.physics.CollisionManager;

import java.awt.*;

public class TopDownRigidbody extends Component{
    private Vector2D velocity = new Vector2D(0, 0);
    private float mass;

    public TopDownRigidbody(Vector2D velocity, float mass) {
        this.velocity = velocity;
        this.mass = mass;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        gameObject.position.add(velocity);
    }

    public float getMass() {
        return mass;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
}
