package fr.um3.ProjetInfo.src;
import java.util.Random;


public class Position {
    public static final int width = 1000;
    public static final int height = 1000;
    public static final int deplacement = 20;
    public static final int deplacementGB = 40;
    private int x = 0;
    private int y = 0;

    public Position(int x, int y) {
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
        int x = getX();

        // Il est généralement préférable de réutiliser un seul objet Random pour éviter des coûts d'initialisation inutiles
        // et pour éviter que les nombres générés ne soient pas vraiment aléatoires si les objets Random sont créés trop rapprochés dans le temps.
        Random random = new Random();

        // Tirage d'un nombre aléatoire pour décider du déplacement en x
        int randomX = random.nextInt(3); // 0, 1 ou 2

        if (getX() > 0 && getX() <= width) {
            // + deplacement
            if (randomX == 1) {
                setX(x + deplacement);
            }
            // - deplacement
            else if (randomX == 2) {
                setX(x - deplacement);
            }
            // si randomX == 0, pas de mouvement en x
        } else if (getX() < width-100)
            setX(x + 2*deplacement);
        else
            setX(x - 2*deplacement);

        int y = getY();
        // Tirage d'un nombre aléatoire pour décider du déplacement en y
        int randomY = random.nextInt(3); // 0, 1 ou 2
        if (getY() > 0 && getY() <= height)

            // + deplacement
            if (randomY == 0) {
                setY(y + deplacement);
            }
            // - deplacement
            else if (randomY == 2) {
                setY(y - deplacement);

            }
        // si randomY == 1, pas de mouvement en y
        else if (getY() > height-100)
            setY(y-(100));
        else
            setY(y+(100));


    }

}

