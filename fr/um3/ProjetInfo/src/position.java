package fr.um3.ProjetInfo.src;

import java.util.Random;

public class position {
    private int x;
    private int y;
    private static Random random = new Random();

    private position(int x, int y) {
        setX(x);
        setY(y);
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void deplacerAleatoirement() {
        int direction = random.nextInt(4);  // Génère un nombre entre 0 (inclus) et 4 (exclus)
        switch (direction) {
            this.position();
        }
    }


    // ... (autres méthodes, getters, setters, etc.)
}
