import java.util.Random;

public class Position {
    private int x;
    private int y;
    private static Random random = new Random();

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void deplacerAleatoirement() {
        int direction = random.nextInt(4);  // Génère un nombre entre 0 (inclus) et 4 (exclus)
        switch (direction) {
            case 0:
                this.x += 1;  // déplacer à droite
                break;
            case 1:
                this.x -= 1;  // déplacer à gauche
                break;
            case 2:
                this.y += 1;  // déplacer vers le haut
                break;
            case 3:
                this.y -= 1;  // déplacer vers le bas
                break;
        }
    }

    // ... (autres méthodes, getters, setters, etc.)
}
