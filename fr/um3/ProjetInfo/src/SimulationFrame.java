package fr.um3.ProjetInfo.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SimulationFrame extends JFrame implements KeyListener {
    private Generation generation;
    private DrawingPanel drawingPanel;
    private Timer timer;
    private boolean isRunning;

    public SimulationFrame(Generation generation) {
        this.generation = generation;
        isRunning = true; // simulation starts in the running state

        this.setTitle("Simulation Cellulaire");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.drawingPanel = new DrawingPanel();
        this.add(drawingPanel, BorderLayout.CENTER);

        // Configurer un Timer pour mettre à jour la génération et redessiner le panel toutes les secondes (1000 ms)
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isRunning) { // only update if the simulation is running
                    updateGeneration();
                    drawingPanel.repaint();
                }
            }
        });
        timer.start();
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
            g.setColor(Color.BLUE);
            Position pos = b.getPositionBact();
            g.fillRect(pos.getX(), pos.getY(), 20, 20);
        }

        private void drawGlobuleBlanc(Graphics g, GlobuleBlanc gb) {
            g.setColor(Color.RED);
            Position pos = gb.getPositionGlobBlanc();
            g.fillRect(pos.getX(), pos.getY(), 20, 20);
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

            for(int i=0; i<300; i++){
                Position pos = new Position(300, 300);
                Bacterie b = new Bacterie(pos, Etat.RECHERCHE_NOURRITURE);
                generation.getListBact().add(b);
            }

            Position posGB = new Position(600, 600);
            GlobuleBlanc gb = new GlobuleBlanc(posGB, Etat.PATROUILLE);
            generation.getListGlobblanc().add(gb);



            SimulationFrame frame = new SimulationFrame(generation);
            frame.setVisible(true);
        });
    }


}

