package fr.um3.ProjetInfo.src;

import javax.swing.text.Position;
import javafx.scene.shape.Circle;


public class GB {
    private Position position;
    private String etat;
    private Circle representationGraphique;

    public GB(Position position) {
        this.position = position;
        this.etat = "patrouille";

        this.representationGraphique = new Circle(position.getX(), position.getY(), 5);  // un cercle de rayon 5
    }

    public void miseAJourGraphique() {
        this.representationGraphique.setCenterX(position.getX());
        this.representationGraphique.setCenterY(position.getY());
    }
}
