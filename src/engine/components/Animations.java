package engine.components;

import engine.graphics.Renderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Animations extends Component{
    private Map<String, Animation> animations = new HashMap<>();

    public void addAnimation(String label, Animation animation) {
        animation.setGameObject(gameObject);
        animations.put(label, animation);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        animations.get(gameObject.currentState).update(deltaTime);
    }
}
