package fr.um3.ProjetInfo.src;

public class CelluleMutante {
    private Position positionCelMut;
    private final Etat etatCelMut;// Etat unique Errance

    public CelluleMutante(Position position) {
        setPosition(position);
        etatCelMut = Etat.ERRANCE;

    }

    public Position getPosition() {
        return positionCelMut;
    }

    public void setPosition(Position position) {
        this.positionCelMut = position;
    }

    public Etat getEtatCelMut() {
        return etatCelMut;
    }

    // Placement des toxines
    public void placeToxine() {
        //....


    }
}
