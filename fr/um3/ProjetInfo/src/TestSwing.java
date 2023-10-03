package fr.um3.ProjetInfo.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class TestSwing {

    private static Random random = new Random();
    private static JPanel panel;
    private static int circleX = 190;  // Initial X coordinate for the circle
    private static int circleY = 190;  // Initial Y coordinate for the circle

    public static void main(String[] args) {
        JFrame frame = new JFrame("Direction Test with Swing");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillOval(circleX, circleY, 20, 20);  // Draw circle
            }
        };

        JButton moveButton = new JButton("Move");
        moveButton.addActionListener(TestSwing::moveCircleRandomly);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(moveButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static void moveCircleRandomly(ActionEvent e) {
        int direction = random.nextInt(4);
        switch (direction) {
            case 0:
                circleX += 10;  // Move right
                break;
            case 1:
                circleX -= 10;  // Move left
                break;
            case 2:
                circleY += 10;  // Move down
                break;
            case 3:
                circleY -= 10;  // Move up
                break;
        }
        panel.repaint();  // Request a repaint of the panel to show the new circle position
    }
}
