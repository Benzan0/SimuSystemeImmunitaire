package fr.um3.ProjetInfo.src;

import javax.swing.text.Position;

public class Bacterie {
    private Position position;
    private String etat; // "chercherNourriture" ou "fuir"

    public Bacterie(Position position) {
        this.position = position;
        this.etat = "chercherNourriture"; // par défaut
    }
}