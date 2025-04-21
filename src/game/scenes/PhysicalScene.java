package game.scenes;

public class PhysicalScene extends GameScene{
    @Override
    public void start() {
        super.start();
        System.out.println("Physical Scene");
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}


//player[0] = new GameObject("Player", new Transform(new Vector2D(100, 100), new Vector3D(0, 0, 0), new Vector2D(0, 0)));
//player[0].addComponent(new CircleCollider(30));
//player[0].addComponent(new TopDownRigidbody(new Vector2D(5, -5), 1));
//player[0].addComponent(new SpriteRenderer());
//player[0].addComponent(new CoordinateXY());
//
//player[1] = new GameObject("Player", new Transform(new Vector2D(200, 200), new Vector3D(0, 0, 0), new Vector2D(0, 0)));
//player[1].addComponent(new CircleCollider(50));
//player[1].addComponent(new TopDownRigidbody(new Vector2D(5, -5), 1));
//player[1].addComponent(new SpriteRenderer());
//player[1].addComponent(new CoordinateXY());
//
//player[2] = new GameObject("Player", new Transform(new Vector2D(300, 300), new Vector3D(0, 0, 0), new Vector2D(0, 0)));
//player[2].addComponent(new CircleCollider(50));
//player[2].addComponent(new TopDownRigidbody(new Vector2D(5, -5), 1));
//player[2].addComponent(new SpriteRenderer());
//player[2].addComponent(new CoordinateXY());
//
//player[3] = new GameObject("Player3", new Transform(new Vector2D(400, 400), new Vector3D(0, 0, 0), new Vector2D(0, 0)));
//player[3].addComponent(new CircleCollider(50));
//player[3].addComponent(new SpriteRenderer());
//player[3].addComponent(new CoordinateXY());
////
////        player[4] = new GameObject("Itachi", new Transform(new Vector2D(50, 50), new Vector3D(0, 0, 0), new Vector2D( 2F, 2F)));
////        player[4].addComponent(new ImageRenderer("assets/images/player/animation/standing/itachi1.png"));
////        List<String> img1 = new ArrayList<>();
////        player[4].currentState = "STANDING";
//////        img1.add("assets/images/player/animation/standing/itachi1.png");
////        img1.add("assets/images/player/animation/standing/itachi2.png");
////        img1.add("assets/images/player/animation/standing/itachi3.png");
//////        img1.add("assets/images/player/animation/standing/itachi4.png");
////        player[4].addComponent(new Animations());
////        player[4].getComponent(Animations.class).addAnimation("STANDING", new Animation(img1));
////        List<String> img2 = new ArrayList<>();
////        img2.add("assets/images/player/animation/running/itachi11.png");
////        img2.add("assets/images/player/animation/running/itachi12.png");
////        img2.add("assets/images/player/animation/running/itachi13.png");
////        img2.add("assets/images/player/animation/running/itachi14.png");
////        img2.add("assets/images/player/animation/running/itachi15.png");
////        img2.add("assets/images/player/animation/running/itachi16.png");
////        player[4].getComponent(Animations.class).addAnimation("RUNNING", new Animation(img2));
////        player[4].addComponent(new SpriteRenderer());
////        player[4].addComponent(new BoxCollider(50, 50));
////        player[4].addComponent(new Rigidbody());
////        player[4].addComponent(new PlayerController());
////        player[4].addComponent(new CoordinateXY());
////
//        CollisionManager.register(player[0]);
//        CollisionManager.register(player[1]);
//        CollisionManager.register(player[2]);
//        CollisionManager.register(player[3]);
//////
////
//addObject(player[0]);
//addObject(player[1]);
//addObject(player[2]);
//addObject(player[3]);
////        addObject(player[4]);