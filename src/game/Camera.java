    package game;

    import engine.objects.GameObject;
    import engine.components.Component;

    public class Camera extends Component {
        private GameObject target;

        public Camera(GameObject target) {
            this.target = target;
        }

        @Override
        public void update(float deltaTime) {
            super.update(deltaTime);
        }
    }
