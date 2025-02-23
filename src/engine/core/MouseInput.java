package engine.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import engine.components.Transform;
import engine.components.ui.Button;
import engine.components.ui.RectTransform;
import engine.utils.LOG_TYPE;
import engine.utils.Logger;

public class MouseInput implements MouseListener {

    // Các biến để lưu trạng thái chuột
    private static int mouseX, mouseY; // Vị trí chuột
    private static boolean mousePressed; // Trạng thái nhấn chuột

    public MouseInput() {
        // Khởi tạo các giá trị mặc định
        mouseX = 0;
        mouseY = 0;
        mousePressed = false;
    }

    // Phương thức để kiểm tra xem chuột có đang nhấn vào một button hay không
    public static boolean isPressedButton(Button button) {
        if (mousePressed) {
            int x = (int) button.getGameObject().transform.position.x;
            int y = (int) button.getGameObject().transform.position.y;
            int width = button.getGameObject().getComponent(RectTransform.class).getWidth();
            int height = button.getGameObject().getComponent(RectTransform.class).getHeight();
            if (mouseX >= x - width / 2 && mouseX <= x + width / 2 && mouseY >= y - height / 2 && mouseY <= y + height / 2) {
                return true;
            }
        }
        return false;
    }

    // Phương thức để lấy vị trí X của chuột
    public int getMouseX() {
        return mouseX;
    }

    // Phương thức để lấy vị trí Y của chuột
    public int getMouseY() {
        return mouseY;
    }

    // Phương thức để kiểm tra xem chuột có đang được nhấn hay không
    public boolean isMousePressed() {
        return mousePressed;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Xử lý sự kiện khi chuột được nhấp (nhấn và nhả)
        Logger.log(MouseInput.class, "Mouse clicked at: (" + e.getX() + ", " + e.getY() + ")", LOG_TYPE.INFO);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Xử lý sự kiện khi chuột được nhấn
        mousePressed = true;
        mouseX = e.getX();
        mouseY = e.getY();
        Logger.log(MouseInput.class, "Mouse pressed at: (" + mouseX + ", " + mouseY + ")", LOG_TYPE.INFO);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Xử lý sự kiện khi chuột được nhả
        mousePressed = false;
        Logger.log(MouseInput.class, "Mouse released at: (" + e.getX() + ", " + e.getY() + ")", LOG_TYPE.INFO);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Xử lý sự kiện khi chuột đi vào vùng component
        Logger.log(MouseInput.class, "Mouse entered component", LOG_TYPE.INFO);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Xử lý sự kiện khi chuột rời khỏi vùng component
        Logger.log(MouseInput.class, "Mouse exited component", LOG_TYPE.INFO);
    }
}