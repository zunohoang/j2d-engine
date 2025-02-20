package engine.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class KeyInput implements KeyListener {
    private static HashSet<Integer> keys = new HashSet<>();

    public static boolean isKeyPressed(int keyCode) {
        return keys.contains(keyCode);
    }

    public static void setKeyPressed(int keyCode, boolean pressed) {
        if (pressed) keys.add(keyCode);
        else keys.remove(keyCode);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setKeyPressed(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setKeyPressed(e.getKeyCode(), false);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
