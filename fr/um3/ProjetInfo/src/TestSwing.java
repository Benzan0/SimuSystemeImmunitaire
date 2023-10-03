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
        int direction = random.nextInt(3);
        switch (direction) {
            case 0:
                int temp = random.nextInt(3);
                circleX += 10;  // Move right
                if(temp == 1)
                    circleY += 10;
                else if(temp == 2)
                    circleY -=10;
                break;
            case 1:
                int temp2 = random.nextInt(3);
                circleX -= 10;  // Move right
                if(temp2 == 1)
                    circleY += 10;
                else if(temp2 == 2)
                    circleY -=10;
                break;
            case 2:
                int temp3 = random.nextInt(3);
                if(temp3 == 1)
                    circleY += 10;
                else if(temp3 == 2)
                    circleY -=10;
                break;
        }
        panel.repaint();  // Request a repaint of the panel to show the new circle position
    }
}
