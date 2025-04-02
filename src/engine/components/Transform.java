package engine.components;

import engine.maths.Position;
import engine.maths.Vector2D;
import engine.maths.Vector3D;

public class Transform extends Component {
    public Vector2D position;
    public Vector3D rotation;
    public Vector2D scale;

    public Transform(Vector2D position, Vector3D rotation, Vector2D scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }
}
