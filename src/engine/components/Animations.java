package engine.components;

import engine.graphics.Renderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Animations extends Component{
    private Map<String, Animation> animations = new HashMap<>();

    public Animation getAnimation(String label) {
        return animations.get(label);
    }

    public void addAnimation(String label, Animation animation, int loop) {
        animation.setGameObject(gameObject);
        animation.setLoop(loop);
        animations.put(label, animation);
    }

    public void setAnimations(Animations animations) {
        for(Map.Entry<String, Animation> entry : animations.animations.entrySet()) {
            String key = entry.getKey();
            Animation animation = entry.getValue();
            animation.setGameObject(gameObject);
            this.animations.put(key, animation);
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(animations.get(gameObject.currentState) != null) {
            animations.get(gameObject.currentState).update(deltaTime);
        }
    }
}
