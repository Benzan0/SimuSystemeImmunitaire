package fr.um3.ProjetInfo.src;

import java.util.Random;

public class GB {
    private int x;
    private int y;
    private static final int MOVE_DISTANCE = 10;
    private static Random random = new Random();

    public GB(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void deplacerAleatoirement() {
        int direction = random.nextInt(4);
        switch (direction) {
            case 0:
                this.x += MOVE_DISTANCE;  // déplacer à droite
                break;
            case 1:
                this.x -= MOVE_DISTANCE;  // déplacer à gauche
                break;
            case 2:
                this.y += MOVE_DISTANCE;  // déplacer vers le bas
                break;
            case 3:
                this.y -= MOVE_DISTANCE;  // déplacer vers le haut
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
