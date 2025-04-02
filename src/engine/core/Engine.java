package engine.core;

import engine.physics.CollisionManager;
import engine.scenes.SceneManager;
import engine.utils.LOG_TYPE;
import engine.utils.Logger;
import game.GameConfig;

import javax.swing.*;
import java.awt.*;

public class Engine {
    public static void start() {
        JFrame frame = new JFrame("My Engine");
        GameLoop gamePanel = new GameLoop();
        frame.add(gamePanel);
        frame.setFocusable(true);
        frame.requestFocus();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        int contentWidth = GameConfig.SCREEN_WIDTH;
        int contentHeight = GameConfig.SCREEN_HEIGHT;
        Insets insets = frame.getInsets();
        int frameWidth = contentWidth + insets.left + insets.right;
        int frameHeight = contentHeight + insets.top + insets.bottom;
        frame.setSize(frameWidth, frameHeight);
        SwingUtilities.invokeLater(gamePanel::start);
        Logger.log(Engine.class, "Engine Started", LOG_TYPE.SUCCESS);
        SceneManager.setFrame(frame);
    }
}
