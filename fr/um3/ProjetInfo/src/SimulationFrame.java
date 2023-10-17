package fr.um3.ProjetInfo.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SimulationFrame extends JFrame implements KeyListener {
    private BufferedImage bacterieImage;
    private BufferedImage gbImage;
    private Generation generation;
    private DrawingPanel drawingPanel;
    private Timer timer;
    private boolean isRunning;

    public SimulationFrame(Generation generation) {


        this.generation = generation;
        isRunning = true; // simulation starts in the running state

        this.setTitle("Simulation Cellulaire");
        this.setSize(Position.width, Position.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.drawingPanel = new DrawingPanel();
        this.add(drawingPanel, BorderLayout.CENTER);

        // Configurer un Timer pour mettre à jour la génération et redessiner le panel toutes les secondes (1000 ms)
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

        timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isRunning) { // only update if the simulation is running
                    updateGeneration();
                    drawingPanel.repaint();
                }
            }
        });
        timer.start();

        try {
            bacterieImage = ImageIO.read(new File("Image/icone_bacterie.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            gbImage = ImageIO.read(new File("Image/icone_globule_blanc.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateGeneration() {
        generation.generation();
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Bacterie b : generation.getListBact()) {
                drawBacterie(g, b);
            }
            for (GlobuleBlanc gb : generation.getListGlobblanc()) {
                drawGlobuleBlanc(g, gb);
            }
        }

        private void drawBacterie(Graphics g, Bacterie b) {
            Graphics2D g2d = (Graphics2D) g;

            Position pos = b.getPositionBact();

            // Dimensions souhaitées pour l'image
            int imageWidth = 40;
            int imageHeight = 40;

            g2d.drawImage(bacterieImage, pos.getX(), pos.getY(), imageWidth, imageHeight, null);
        }

        private void drawGlobuleBlanc(Graphics g, GlobuleBlanc gb) {
            Graphics2D g2d = (Graphics2D) g;

            Position pos = gb.getPositionGlobBlanc();

            // Dimensions souhaitées pour l'image
            int imageWidth = 40;
            int imageHeight = 40;

            g2d.drawImage(gbImage, pos.getX(), pos.getY(), imageWidth, imageHeight, null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) { // check if space bar was pressed
            isRunning = !isRunning; // toggle the running state
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Generation generation = new Generation();

            int add = 0;

            for(int i=0; i<10; i++){

                Position pos = new Position(500, 500);
                Bacterie b = new Bacterie(pos, Etat.RECHERCHE_NOURRITURE);
                generation.getListBact().add(b);
                add += 20  ;
            }

            Position posGB = new Position(600, 600);
            GlobuleBlanc gb = new GlobuleBlanc(posGB, Etat.PATROUILLE);
            generation.getListGlobblanc().add(gb);



            SimulationFrame frame = new SimulationFrame(generation);
            frame.setVisible(true);
        });
    }


}

