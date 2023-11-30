package fr.um3.ProjetInfo.src.PackageMapping;

import fr.um3.ProjetInfo.src.PackageCellule.Cellule;
import fr.um3.ProjetInfo.src.PackageConstructionSimu.Position;

public class Rectangle {
    Position position;
    int width;
    int height;

    public Rectangle(Position position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setRandPos(){
        this.position = Position.randPos();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean intersects(Cellule cell) {
        // Calculez les "bords" des deux cellules
        return this.getPosition().getX() < cell.getPosition().getX() + cell.getWidth() &&
                this.getPosition().getX() + this.getWidth() > cell.getPosition().getX() &&
                this.getPosition().getY() < cell.getPosition().getY() + cell.getHeight() &&
                this.getPosition().getY() + this.getHeight() > cell.getPosition().getY();
    }


}
