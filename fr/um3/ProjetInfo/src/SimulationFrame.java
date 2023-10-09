package fr.um3.ProjetInfo.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame {
    private Generation generation;
    private DrawingPanel drawingPanel;

    public SimulationFrame(Generation generation) {
        this.generation = generation;

        this.setTitle("Simulation Cellulaire");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.drawingPanel = new DrawingPanel();
        this.add(drawingPanel, BorderLayout.CENTER);

        // Configurer un Timer pour mettre à jour la génération et redessiner le panel toutes les secondes (1000 ms)
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGeneration();
                drawingPanel.repaint();
            }
        });
        timer.start(); // démarre le Timer
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
            g.setColor(Color.GRAY);
            Position pos = gb.getPositionGlobBlanc();
            g.fillOval(pos.getX(), pos.getY(), 10, 10);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Generation generation = new Generation();

            for(int i=0; i<200; i++){
                Position pos = new Position(550, 550);
                Bacterie b = new Bacterie(pos, Etat.RECHERCHE_NOURRITURE);
                generation.getListBact().add(b);
            }

            Position posGB = new Position(500, 600);
            GlobuleBlanc gb = new GlobuleBlanc(posGB, Etat.PATROUILLE);
            //generation.getListGlobblanc().add(gb);

            SimulationFrame frame = new SimulationFrame(generation);
            frame.setVisible(true);
        });
    }
}

