package engine.components.ui;

import engine.components.Component;
import engine.components.Transform;
import engine.core.KeyInput;
import engine.core.MouseInput;

public class Button extends Component {
    public String text;
    public Runnable onClick;

    private boolean isHovered = false;
    private boolean isPressed = false;

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(MouseInput.isPressedButton(this)){
            isPressed = true;
            onClick.run();
        } else {
            isPressed = false;
        }
    }

    public Button(Runnable onClick) {
        this.onClick = onClick;
    }

    public boolean isHovered() {
        return isHovered;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setHovered(boolean hovered) {
        isHovered = hovered;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }
}
