import engine.core.Engine;
import engine.scenes.SceneManager;
import game.scenes.MenuScene;

public class Main {
    public static void main(String[] args) {
//        // Tạo Frame (cửa sổ chính)
//        Frame frame = new Frame("Game Engine with Rigidbody");
//        frame.setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
//        frame.setResizable(false);
//
//        // Tạo GameLoop (Canvas)
//        GameLoop gameLoop = new GameLoop();
//        gameLoop.setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
//
//        // Thêm GameLoop vào Frame
//        frame.add(gameLoop);
//
//        // Hiển thị Frame
//        frame.setVisible(true);
//
//        // Đóng ứng dụng khi đóng cửa sổ
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
//
//        // Khởi động game loop
//        gameLoop.start();

        Engine.start();
        // Tải scene đầu tiên (MenuScene)
        SceneManager.loadScene(new MenuScene());
    }
}