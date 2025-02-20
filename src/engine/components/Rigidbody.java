package engine.components;

import engine.maths.Vector2D;

public class Rigidbody extends Component {
    public Vector2D velocity = new Vector2D(0, 0);
    private float gravity = 500;

    @Override
    public void update(float deltaTime) {
        velocity.y += gravity * deltaTime; // Thêm trọng lực vào vận tốc
        gameObject.position.add(new Vector2D(velocity.x * deltaTime, velocity.y * deltaTime));

        // Giữ nhân vật không rơi qua màn hình
        if (gameObject.position.y > 500) {
            gameObject.position.y = 500;
            velocity.y = 0;
        }
    }
}

