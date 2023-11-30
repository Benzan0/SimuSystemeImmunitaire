package fr.um3.ProjetInfo.src.PackageInterfaceGraphique;

import fr.um3.ProjetInfo.src.PackageCellule.*;
import fr.um3.ProjetInfo.src.PackageConstructionSimu.Generation;
import fr.um3.ProjetInfo.src.PackageInterfaceGraphique.InitialWindow;
import fr.um3.ProjetInfo.src.PackageMapping.Plaie;
import fr.um3.ProjetInfo.src.PackageConstructionSimu.Position;
import fr.um3.ProjetInfo.src.PackageMapping.Rectangle;

        import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SimulationFrame extends JFrame implements KeyListener {
    private BufferedImage bacterieImage;
    private BufferedImage gbImage;
    private BufferedImage cmImage;
    private BufferedImage toxineImage;
    private JTextField nombreBact;

    private JPanel panel = new JPanel();
    private JTextField bactCountText = new JTextField("Nombre de globule blanc : ");
    private JTextField gbCountText = new JTextField("Nombre de globule blanc : ");
    private JTextField mutCountText = new JTextField("Nombre de globule blanc : ");


    private BufferedImage nutriImage;
    private Generation generation;
    private DrawingPanel drawingPanel;
    private Timer timer;
    private boolean isRunning;
    public static ArrayList<Rectangle> vaisseauSimu = Plaie.creationVaissaux();
    private static Boolean isVaisseauRemoved = false;
    public static final Rectangle paroiVaisseau = new Rectangle(new Position(0,700),1000,20);


    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

    public SimulationFrame(Generation generation) {


        this.generation = generation;
        isRunning = true;

        this.setTitle("Simulation Cellulaire");
        this.setSize(Position.width, Position.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.drawingPanel = new DrawingPanel();
        this.add(drawingPanel, BorderLayout.CENTER);

        drawingPanel.add(gbCountText);
        drawingPanel.add(bactCountText);
        drawingPanel.add(mutCountText);


        // Configurer un Timer pour mettre à jour la génération et redessiner le panel toutes les 100 ms
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

        timer = new Timer(100, e -> {
            if(isRunning) {
                setContentPane(drawingPanel);
                updateGeneration();
                drawingPanel.repaint();

                int gbCount = 0;
                int bactCount = 0;
                int mutCount = 0;

                for(Cellule c : generation.getGrille().getListCellules()){
                    if(c instanceof GlobuleBlanc)
                        gbCount++;
                    if(c instanceof Bacterie)
                        bactCount++;
                    if(c instanceof CelluleMutante)
                        mutCount++;
                }

                gbCountText.setText("Nombre de globule blanc : "+gbCount);
                bactCountText.setText("Nombre de bactéries : "+bactCount);
                mutCountText.setText("Nombre de Cellule Mutante : "+mutCount);

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

        try {
            cmImage = ImageIO.read(new File("Image/Cellule_Cancereuse.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            toxineImage = ImageIO.read(new File("Image/flaque_verte.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            nutriImage = ImageIO.read(new File("Image/icone_nutri.png"));
        } catch (IOException e){
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

            for(Nutriment n : generation.getGrille().getListNutriments()){
                drawNutriment(g,n);
            }

            for(Toxine t : generation.getGrille().getListToxines()){
                drawToxine(g,t);
            }

            for(Cellule c : generation.getGrille().getListCellules()) {
                if (c instanceof Bacterie) {
                    Bacterie b = (Bacterie) c;
                    drawBacterie(g, b);
                }
                if (c instanceof GlobuleBlanc) {
                    GlobuleBlanc gb = (GlobuleBlanc) c;
                    drawGlobuleBlanc(g, gb);
                }
                if(c instanceof CelluleMutante){
                    CelluleMutante cm = (CelluleMutante) c;
                    drawCelluleMutante(g,cm);
                }
            }
            for(Rectangle partieVaissaux : vaisseauSimu){
                drawVesselPart(g,partieVaissaux);
            }
        }

        private void drawBacterie(Graphics g, Bacterie b) {
            Graphics2D g2d = (Graphics2D) g;

            Position pos = b.getPosition();

            // Dimensions souhaitées pour l'image
            int imageWidth = 40;
            int imageHeight = 40;

            g2d.drawImage(bacterieImage, pos.getX(), pos.getY(), imageWidth, imageHeight, null);
        }

        private void drawGlobuleBlanc(Graphics g, GlobuleBlanc gb) {
            Graphics2D g2d = (Graphics2D) g;

            Position pos = gb.getPosition();

            // Dimensions souhaitées pour l'image
            int imageWidth = 40;
            int imageHeight = 40;

            g2d.drawImage(gbImage, pos.getX(), pos.getY(), imageWidth, imageHeight, null);
        }

        private void drawCelluleMutante(Graphics g, CelluleMutante cm) {
            Graphics2D g2d = (Graphics2D) g;

            Position pos = cm.getPosition();

            // Dimensions souhaitées pour l'image
            int imageWidth = 40;
            int imageHeight = 40;

            g2d.drawImage(cmImage,pos.getX(), pos.getY(), imageWidth, imageHeight,null);

        }

        private void drawToxine(Graphics g, Toxine toxine) {
            Graphics2D g2d = (Graphics2D) g;

            Position pos = toxine.getPosition();

            // Dimensions souhaitées pour l'image
            int imageWidth = 40;
            int imageHeight = 40;

            g2d.drawImage(toxineImage,pos.getX(), pos.getY(), imageWidth, imageHeight,null);
        }

        private void drawNutriment(Graphics g, Nutriment nutri) {
            Graphics2D g2d = (Graphics2D) g;

            Position pos = nutri.getPosition();

            // Dimensions souhaitées pour l'image
            int imageWidth = 10;
            int imageHeight = 10;

            g2d.drawImage(nutriImage,pos.getX(), pos.getY(), imageWidth, imageHeight,null);
        }
        private void drawVesselPart(Graphics g, Rectangle partieVaissaux){
            g.setColor(Color.RED);
            Position pos = partieVaissaux.getPosition();
            g.fillRect(pos.getX(),pos.getY(),partieVaissaux.getWidth(),partieVaissaux.getHeight());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isRunning = !isRunning;
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            generation.getGrille().ajouterCellule(new Bacterie(Position.randPos(),Etat.RECHERCHE_NOURRITURE));
        }
        if (e.getKeyCode() == KeyEvent.VK_G) {
            generation.getGrille().ajouterCellule(new GlobuleBlanc(Position.randPos(new GlobuleBlanc()),Etat.PATROUILLE));
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER){

            if(isVaisseauRemoved){
                vaisseauSimu.add(paroiVaisseau);
                isVaisseauRemoved = false;
            }
            else {
                vaisseauSimu.remove(paroiVaisseau);
                isVaisseauRemoved = true;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {

        InitialWindow initialWindow = new InitialWindow();
        initialWindow.setVisible(true);
    }
}

