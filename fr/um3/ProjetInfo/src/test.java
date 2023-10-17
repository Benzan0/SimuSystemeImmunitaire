package fr.um3.ProjetInfo.src;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {

        ArrayList<GlobuleBlanc> list = new ArrayList<>();

        Generation listeCellule = new Generation();

        listeCellule.setListGlobblanc(list);

        Position pos = new Position(1, 1);
        System.out.println(listeCellule.getListGlobblanc().size());
        listeCellule.getListGlobblanc().add(new GlobuleBlanc(pos, Etat.PATROUILLE));
        System.out.println(listeCellule.getListGlobblanc().size());

        GlobuleBlanc gb = new GlobuleBlanc(pos, Etat.PATROUILLE);
        Position newpos = gb.getPositionGlobBlanc();
        GlobuleBlanc gbMod = new GlobuleBlanc(gb.getPositionGlobBlanc(),gb.getEtatGlobBlanc());

        pos.setY(23);

        System.out.println(gbMod.getPositionGlobBlanc().getY());
        System.out.println(gb.getPositionGlobBlanc().getY());


    }

}

