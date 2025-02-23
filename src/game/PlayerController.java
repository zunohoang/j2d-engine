package game;

import engine.core.KeyInput;
import engine.components.Rigidbody;
import engine.components.Component;

import java.awt.event.KeyEvent;

public class PlayerController extends Component {
    private float speed = 200;

    @Override
    public void update(float deltaTime) {
        if (KeyInput.isKeyPressed(KeyEvent.VK_LEFT)) {
            gameObject.transform.position.x -= speed * deltaTime;
        }
        if (KeyInput.isKeyPressed(KeyEvent.VK_RIGHT)) {
            gameObject.transform.position.x += speed * deltaTime;
        }
    }
}
