package fr.um3.ProjetInfo.src;

public class CelluleMutante {
    private Position position;
    private String etat; // Vous devriez définir les états possibles pour la cellule mutante

    public void CelluleMutante(Position position) {
        this.position = position;
        // Initialisation d'un état par défaut
    }

    public void seDeplacer() {
        // implémentation du déplacement
    }

    // Autres méthodes pour la cellule mutante
    public class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
