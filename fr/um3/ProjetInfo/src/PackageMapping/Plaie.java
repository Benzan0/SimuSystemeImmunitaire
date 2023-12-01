package fr.um3.ProjetInfo.src.PackageMapping;

import fr.um3.ProjetInfo.src.PackageConstructionSimu.Position;
import fr.um3.ProjetInfo.src.PackageMapping.Rectangle;

import java.util.ArrayList;

public class Plaie {
    public static ArrayList<Rectangle> creationVaissaux(){
        ArrayList<Rectangle>  vaissaux = new ArrayList<Rectangle>();

        Position pos1 = new Position(0,800);
        Rectangle rectangle01 = new Rectangle(pos1,1000,20);
        vaissaux.add(rectangle01);

        Position pos2 = new Position(0, 700);
        Rectangle rectangle02 = new Rectangle(pos2,100,20 );
        vaissaux.add(rectangle02);

        Position pos3 = new Position(150, 700);
        Rectangle rectangle03 = new Rectangle(pos3,100,20);
        vaissaux.add(rectangle03);

        Position pos4 = new Position(300, 700);
        Rectangle rectangle04 = new Rectangle(pos4,100,20);
        vaissaux.add(rectangle04);

        Position pos5 = new Position(450, 700);
        Rectangle rectangle05 = new Rectangle(pos5,100,20);
        vaissaux.add(rectangle05);

        Position pos6 = new Position(600, 700);
        Rectangle rectangle06 = new Rectangle(pos6,100,20);
        vaissaux.add(rectangle06);

        Position pos7 = new Position(750, 700);
        Rectangle rectangle07 = new Rectangle(pos7,100,20);
        vaissaux.add(rectangle07);

        Position pos8 = new Position(900, 700);
        Rectangle rectangle08 = new Rectangle(pos8,100,20);
        vaissaux.add(rectangle08);

        Position pos9 = new Position(300, 300);
        Rectangle rectangle09 = new Rectangle(pos9,10,20);
        vaissaux.add(rectangle09);

        Position pos10 = new Position(400, 350);
        Rectangle rectangle010 = new Rectangle(pos10,20,10);
        vaissaux.add(rectangle010);

        Position pos11 = new Position(200, 400);
        Rectangle rectangle011 = new Rectangle(pos11,20,10);
        vaissaux.add(rectangle011);

        Position pos12 = new Position(-20,-20);
        Rectangle rectangle012 = new Rectangle(pos12,20,1000);
        Rectangle rectangle013 = new Rectangle(pos12,1000,20);
        vaissaux.add(rectangle012);
        vaissaux.add(rectangle013);

        Position pos13 = new Position(1000,0);
        Rectangle rectangle014 = new Rectangle(pos13,20,1000);
        vaissaux.add(rectangle014);

        Position pos14 = new Position(0,1000);
        Rectangle rectangle015 = new Rectangle(pos14,1000,20);
        vaissaux.add(rectangle015);






        return vaissaux;
    }
}

