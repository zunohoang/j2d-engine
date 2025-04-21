package engine.maths;

public class Vector2D {
    public float x;
    public float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2D zero() {
        return new Vector2D(0, 0);
    }

    public Vector2D add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
        return other;
    }

    public void subtract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
    }

    public void multiply(Vector2D other) {
        this.x *= other.x;
        this.y *= other.y;
    }

    public void multiply(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public void divide(Vector2D other) {
        this.x /= other.x;
        this.y /= other.y;
    }

    public void add(float v, float v1) {
        this.x += v;
        this.y += v1;
    }

    public void set(Vector2D velocity) {
        this.x = velocity.x;
        this.y = velocity.y;
    }

    public void set(int i, int i1) {
        this.x = i;
        this.y = i1;
    }

    public boolean isZero() {
        return this.x == 0 && this.y == 0;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector2D normalize() {
        float length = length();
        if (length == 0) return new Vector2D(0, 0);
        return new Vector2D(x / length, y / length);
    }

    public float lengthSquared() {
        return x * x + y * y;
    }

    public Vector2D mul(float mass) {
        return new Vector2D(x * mass, y * mass);
    }

    public Vector2D div(float mass) {
        if (mass == 0) return new Vector2D(0, 0);
        return new Vector2D(x / mass, y / mass);
    }
}
