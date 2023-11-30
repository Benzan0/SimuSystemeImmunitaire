package fr.um3.ProjetInfo.src.PackageInterfaceGraphique;

import fr.um3.ProjetInfo.src.PackageCellule.Nutriment;
import fr.um3.ProjetInfo.src.PackageConstructionSimu.FilesReader;
import fr.um3.ProjetInfo.src.PackageConstructionSimu.Generation;
import fr.um3.ProjetInfo.src.PackageException.ChanceException;
import fr.um3.ProjetInfo.src.PackageException.FilesException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class InitialWindow extends JFrame {

    public InitialWindow() {
        setTitle("Ecran de départ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        String[] listePara = {"Chance Division","Chance Mutation","Taux d'apparition Nutriment","Duree de vie Bactérie"};


        JPanel rowPanel1 = new JPanel();
        rowPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 1)); // Espacement de 5 pixels
        JPanel rowPanel2 = new JPanel();
        rowPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 1)); // Espacement de 5 pixels
        JPanel rowPanel3 = new JPanel();
        rowPanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 1)); // Espacement de 5 pixels
        JPanel rowPanel4 = new JPanel();
        rowPanel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 1)); // Espacement de 5 pixels
        JPanel rowPanel5 = new JPanel();
        rowPanel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Espacement de 5 pixels
        JPanel rowPanel6 = new JPanel();
        rowPanel6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Espacement de 5 pixels

        JTextField textFieldCD = new JTextField(10); // Taille du champ de texte
        JButton buttonCD = new JButton("<- Chance Division");
        JTextField textFieldCM = new JTextField(10); // Taille du champ de texte
        JButton buttonCM = new JButton("<- Chance Mutation");
        JTextField textFieldTAN = new JTextField(10); // Taille du champ de texte
        JButton buttonTAN = new JButton("<- Taux d'apparition Nutriment");
        JTextField textFieldDVB = new JTextField(10); // Taille du champ de texte
        JButton buttonDVB = new JButton("<- Duree de vie Bactérie");

        JButton buttonSet1 = new JButton("Préconfiguration 1");
        JButton buttonSet2 = new JButton("Préconfiguration 2");
        JButton buttonSet3 = new JButton("Préconfiguration 3");
        JButton buttonSet4 = new JButton("Préconfiguration 4");

        JButton buttonNewConfig = new JButton("Configuration Perso");



        rowPanel1.add(textFieldCD);
        rowPanel1.add(buttonCD);
        rowPanel2.add(textFieldCM);
        rowPanel2.add(buttonCM);
        rowPanel3.add(textFieldDVB);
        rowPanel3.add(buttonDVB);
        rowPanel4.add(textFieldTAN);
        rowPanel4.add(buttonTAN);
        rowPanel5.add(buttonSet1);
        rowPanel5.add(buttonSet2);
        rowPanel5.add(buttonSet3);
        rowPanel5.add(buttonSet4);
        rowPanel6.add(buttonNewConfig);


        add(rowPanel1);
        add(rowPanel2);
        add(rowPanel3);
        add(rowPanel4);
        add(rowPanel5);
        add(rowPanel6);
        add(Box.createVerticalGlue());



        add(Box.createVerticalGlue());

        JButton startButton = new JButton("Démarrer");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Alignement au centre
        add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchSimulation();
            }
        });

        buttonCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double value = Double.parseDouble(textFieldCD.getText());
                    System.out.println("Chance division value: " + value);
                    Generation.setChanceDivision(value);
                    System.out.println(Generation.chanceDivision);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.");
                } catch (ChanceException ce){
                    JOptionPane.showMessageDialog(null, ce.getMessage());
                }
            }
        });

        buttonCM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double value = Double.parseDouble(textFieldCM.getText());
                    System.out.println("Chance Mutation value: " + value);
                    Generation.setChanceDivision(value);
                    System.out.println(Generation.chanceMutation);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.");
                } catch (ChanceException ce){
                    JOptionPane.showMessageDialog(null, ce.getMessage());
                }
            }
        });

        buttonDVB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(textFieldDVB.getText());
                    System.out.println("Duree Vie Bactérie  value: " + value);
                    Generation.setDureeDeVieBact(value);
                    System.out.println(Generation.dureeDeVieBact);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.");
                } catch (ChanceException ce){
                    JOptionPane.showMessageDialog(null, ce.getMessage());
                }
            }
        });

        buttonTAN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(textFieldTAN.getText());
                    System.out.println("Chance Spawn Nutriment value: " + value);
                   Nutriment.setChanceSpawn(value);
                    System.out.println(Nutriment.chanceSpawn);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.");
                } catch (ChanceException ce){
                    JOptionPane.showMessageDialog(null, ce.getMessage());
                }
            }
        });

        buttonSet1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Double> listPara = FilesReader.readFiles("Profiles/ProfileBasique.txt");

                    Generation.setChanceDivision(listPara.get(0));
                    System.out.println(Generation.chanceDivision);
                    Generation.setChanceMutation(listPara.get(1));
                    System.out.println(Generation.chanceMutation);
                    Nutriment.setChanceSpawn(listPara.get(2));
                    System.out.println(Nutriment.chanceSpawn);
                    Generation.setDureeDeVieBact(listPara.get(3).intValue());
                    System.out.println(Generation.dureeDeVieBact);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.");
                } catch (ChanceException ce){
                    JOptionPane.showMessageDialog(null, ce.getMessage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonNewConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = JOptionPane.showInputDialog("Entez le chemin vers le fichier .txt contenant la configuration \n" +
                            "le fichier doit contenir dans l'ordre : chance division, chance mutation, chance apparition nutriment " +
                            ", durée de vie bactérie");

                    setConf(text);
                    System.out.println(text);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.");
                } catch (ChanceException | FilesException ce){
                    JOptionPane.showMessageDialog(null, ce.getMessage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonSet2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setConf("Profiles/ProfileSuperBacterie.txt");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.");
                } catch (ChanceException | IOException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (FilesException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonSet3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setConf("Profiles/ProfilMutationFort.txt");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.");
                } catch (ChanceException ce){
                    JOptionPane.showMessageDialog(null, ce.getMessage());
                } catch (IOException | FilesException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonSet4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setConf("Profiles/ProfilNutPlus.txt");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.");
                } catch (ChanceException ce){
                    JOptionPane.showMessageDialog(null, ce.getMessage());
                } catch (IOException | FilesException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void setConf(String path) throws IOException, ChanceException, FilesException {
        List<Double> listPara = FilesReader.readFiles(path);

        if(listPara.size() == 4){
            Generation.setChanceDivision(listPara.get(0));
            System.out.println(Generation.chanceDivision);
            Generation.setChanceMutation(listPara.get(1));
            System.out.println(Generation.chanceMutation);
            Nutriment.setChanceSpawn(listPara.get(2));
            System.out.println(Nutriment.chanceSpawn);
            Generation.setDureeDeVieBact(listPara.get(3).intValue());
            System.out.println(Generation.dureeDeVieBact);
        }
        else throw new FilesException("Erreur : Le fichier n'as pas été correctement configuré");
    }

    private void launchSimulation() {
        Generation generation = new Generation();
        SimulationFrame simulationFrame = new SimulationFrame(generation);
        simulationFrame.setVisible(true);

        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InitialWindow initialWindow = new InitialWindow();
            initialWindow.setVisible(true);
        });
    }
}
