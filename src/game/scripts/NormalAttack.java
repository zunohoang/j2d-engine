package game.scripts;

import engine.components.Animation;
import engine.components.Animations;
import engine.components.Component;
import engine.utils.LOG_TYPE;
import engine.utils.Logger;

import java.awt.event.KeyEvent;
import java.util.Objects;

public class NormalAttack extends Component {

    private Animation animation;
    private boolean isPressedLast = false;
    private long lastPressedTime = 0;

    @Override
    public void start() {
        super.start();
        Animations animations = gameObject.getComponent(Animations.class);
        if(animations == null) {
            Logger.log(this, "Can't not get animations component", LOG_TYPE.WARN);
        }
        assert animations != null;
        System.out.println("OK");
        this.animation = animations.getAnimation("normalAttacking");
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (engine.core.KeyInput.isKeyJustPressed(KeyEvent.VK_J)
                && !isPressedLast
                && System.currentTimeMillis() - lastPressedTime > 500
        ) {
            isPressedLast = true;
            gameObject.currentState = "normalAttacking";
            if(animation != null) {
                animation.increase();
                Logger.log(this, "Current frame: " + animation.getCurrentImageIndex());
            }
            lastPressedTime = System.currentTimeMillis();
        } else {
            isPressedLast = false;
        }
    }
}
