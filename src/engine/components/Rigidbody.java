package engine.components;

import engine.maths.Vector2D;

public class Rigidbody extends Component {
    public Vector2D velocity = new Vector2D(0, 0);
    public Vector2D force = new Vector2D(0, 0);
    public float mass = 1f;
    public boolean useGravity = true;
    public boolean isKinematic = false;

    private static final Vector2D GRAVITY = new Vector2D(0, 980); // pixels/s^2

    @Override
    public void update(float deltaTime) {
        if (isKinematic) return;


        force.add(GRAVITY.mul(mass));  // Thêm trọng lực vào lực tổng

        // Tính gia tốc từ lực và cập nhật vận tốc
        Vector2D acceleration = force.div(mass);
        velocity.add(acceleration.mul(deltaTime));

        // Cập nhật vị trí của đối tượng
        gameObject.transform.position.add(velocity.mul(deltaTime));

        // Reset lực sau mỗi frame
        force = new Vector2D(0, 0);
    }


    // Hàm nhảy
    public void jump(float jumpForce) {
        velocity.y = -jumpForce;  // Đặt vận tốc theo chiều ngược lại của trọng lực để nhảy lên
    }

    // Hàm để áp dụng lực (ví dụ: lực va chạm hoặc lực khác)
    public void applyForce(Vector2D force) {
        this.force.add(force);
    }
}
