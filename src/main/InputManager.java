package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class InputManager implements KeyListener {
   public boolean up, down, left, right = false;


    public InputManager() {
        //Constructor
    }

    public void keyPressed(KeyEvent e) {
        //Handle key press
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            up = true;
        } else if (code == KeyEvent.VK_S) {
            down = true;
        } else if (code == KeyEvent.VK_A) {
            left = true;
        } else if (code == KeyEvent.VK_D) {
            right = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        //Handle key release
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            up = false;
        } else if (code == KeyEvent.VK_S) {
            down = false;
        } else if (code == KeyEvent.VK_A) {
            left = false;
        } else if (code == KeyEvent.VK_D) {
            right = false;
        }
    }

    public void keyTyped(KeyEvent e) {
        //Handle key typed
    }
}
