import javax.swing.*;
import java.awt.*;

    class Puzzle {
    public static void main(String[] args) {
        JFrame frame = new JFrame("루피 퍼즐 맞추기");
        frame.setLocation(500, 200);
        frame.setPreferredSize(new Dimension(600 + 200, 600));
        Container contentPane = frame.getContentPane();
        ImagePanel imagePanel = new ImagePanel();
        imagePanel.addMouseListener(new MouseHandler(imagePanel));
        contentPane.add(imagePanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
