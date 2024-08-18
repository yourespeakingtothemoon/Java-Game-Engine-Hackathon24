package main;

import javax.swing.JFrame;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame("Preeow World");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GPanel panel = new GPanel();
        window.add(panel);
        window.pack();
        window.setVisible(true);
        panel.initalizeThread();
    }

}
