

package fr.um3.ProjetInfo.src.PackageConstructionSimu;

        import fr.um3.ProjetInfo.src.PackageException.ChanceException;
        import fr.um3.ProjetInfo.src.PackageCellule.*;

        import java.util.*;


public class Generation extends Thread {


    public static final int distanceDetection = 500;
    public static final int distanceDetectionGB = 500;
    public static double chanceMutation = 0.005;
    public static double chanceDivision = 0.1;
    public static int dureeDeVieBact = 50;
    private Grille grille = new Grille();

    private List<Cellule> aSupprimer = new ArrayList<>();
    private List<Cellule> aAjouter = new ArrayList<>();
    private List<Nutriment> nutASup = new ArrayList<>();
    private List<Toxine> toxinesASup = new ArrayList<>();

    public List<Cellule> getaSupprimer() {
        return aSupprimer;
    }

    public void setaSupprimer(List<Cellule> aSupprimer) {
        this.aSupprimer = aSupprimer;
    }

    public List<Cellule> getaAjouter() {
        return aAjouter;
    }

    public void setaAjouter(List<Cellule> aAjouter) {
        this.aAjouter = aAjouter;
    }

    public List<Nutriment> getNutASup() {
        return nutASup;
    }

    public void setNutASup(List<Nutriment> nutASup) {
        this.nutASup = nutASup;
    }

    public List<Toxine> getToxinesASup() {
        return toxinesASup;
    }

    public void setToxinesASup(List<Toxine> toxinesASup) {
        this.toxinesASup = toxinesASup;
    }

    public Grille getGrille() {
        return grille;
    }

    public static void setDureeDeVieBact(int dureeDeVieBact) throws ChanceException {
        if(dureeDeVieBact < 0){
            throw new ChanceException("Erreur : La durée de vie d'une bactérie doit-etre superieur à 0");
        }
        Generation.dureeDeVieBact = dureeDeVieBact;
    }

    public static void setChanceMutation(double chanceMutation) throws ChanceException {
        if(chanceMutation < 0 || chanceMutation > 1){
            throw new ChanceException("Erreur : La chance de mutation doit-être comprise entre 0 et 1");
        }
        Generation.chanceMutation = chanceMutation;
    }

    public static void setChanceDivision(double chanceDivision) throws ChanceException {
        if(chanceDivision < 0 || chanceDivision > 1){
            throw new ChanceException("Erreur : La chance de division doit-être comprise entre 0 et 1");
        }
        Generation.chanceDivision = chanceDivision;
    }

    private void isEating( GlobuleBlanc cellChass){
        for(Cellule cell : getGrille().getListCellules()) {
            if(cell instanceof Bacterie cellCible){
                if (cellChass.getPosition().getX() < cellCible.getPosition().getX() + cellCible.getWidth() &&
                        cellChass.getPosition().getX() + cellChass.getWidth() > cellCible.getPosition().getX() &&
                        cellChass.getPosition().getY() < cellCible.getPosition().getY() + cellCible.getHeight() &&
                        cellChass.getPosition().getY() + cellChass.getHeight() > cellCible.getPosition().getY()) {
                    getaSupprimer().add(cellCible);
                }
            }
        }

    }

    private void isEating( Bacterie cellChass){
        for(Nutriment nutCible : getGrille().getListNutriments()){
            if(cellChass.getPosition().getX() < nutCible.getPosition().getX() + nutCible.getPosition().getX() +10 &&
                    cellChass.getPosition().getX() + cellChass.getWidth() > nutCible.getPosition().getX()+10 &&
                    cellChass.getPosition().getY() < nutCible.getPosition().getY()+ 10 &&
                    cellChass.getPosition().getY() + cellChass.getHeight() > nutCible.getPosition().getY()+10){
                getNutASup().add(nutCible);
                cellChass.setDureeVie(cellChass.getDureeVie()+20);
            }
        }

    }

    private void isEating(Toxine toxine){
        for(Cellule cellCible : getGrille().getListCellules()) {
            if(!(cellCible instanceof CelluleMutante)) {
                if (toxine.getPosition().getX() < cellCible.getPosition().getX() + cellCible.getWidth() &&
                        toxine.getPosition().getX() + 40 > cellCible.getPosition().getX() &&
                        toxine.getPosition().getY() < cellCible.getPosition().getY() + cellCible.getHeight() &&
                        toxine.getPosition().getY() + 40 > cellCible.getPosition().getY()) {
                    System.out.println("PASSE SUR UNE TOXINE");
                    getaSupprimer().add(cellCible);
                }
            }
        }
    }

    private void division(Bacterie b){
        System.out.println("Division Cellule");
        Random randomDiv = new Random();
        Random randomMut = new Random();

        if(randomDiv.nextDouble() <= chanceDivision){
            System.out.println("Division");
            if(randomMut.nextDouble() <= chanceMutation){
                getaAjouter().add(new CelluleMutante(b.getPosition()));
            }
            else{
                getaAjouter().add(new Bacterie(b.getPosition(), Etat.RECHERCHE_NOURRITURE));
            }
        }
    }

    private void addNutriment(){
        Random randomNut = new Random();
        System.out.println("NUT");
        if(randomNut.nextDouble() <= Nutriment.chanceSpawn){
            System.out.println("New Nut");
            getGrille().getListNutriments().add(new Nutriment());
        }
    }

    private void deplacerCellules(){
        for(Cellule c : getGrille().getListCellules()){
            grille.deplacerCellule(c);
        }
    }

    private void traiterCellules(){
        for(Cellule c : getGrille().getListCellules()){
            if(c instanceof Bacterie b){
                division(b);
                isEating(b);
            }
            if(c instanceof GlobuleBlanc gb){
                isEating(gb);
            }
        }
    }

    private void traiterToxines(){
        for(Toxine t : getGrille().getListToxines()){
            t.setDureeVie(t.getDureeVie()-1);
            isEating(t);
            if(t.getDureeVie() <= 0){
                getToxinesASup().add(t);
            }
        }
    }

    private void nettoyerGrille(){
        for(Cellule c : getGrille().getListCellules()){
            if(c instanceof CelluleADureeVie cdv){
                cdv.setDureeVie(cdv.getDureeVie()-1);
                if(cdv.getDureeVie() <= 0){
                    System.out.println("SUPPRESSION"+c.getEtat().toString());
                    getaSupprimer().add(cdv);
                }
            }
        }

        addNutriment();


    }
    public void generation(){
        System.out.println("//////////NEW GENERATION///////////");

        setaAjouter(new ArrayList<>());
        setaSupprimer(new ArrayList<>());
        setNutASup(new ArrayList<>());
        setToxinesASup(new ArrayList<>());

        System.out.println("Nouvelle grille : " + aAjouter);

        Thread threadDeplacement = new Thread(this::deplacerCellules);
        Thread threadTraitementCellules = new Thread(this::traiterCellules);
        Thread threadTraitementToxines = new Thread(this::traiterToxines);

        threadDeplacement.start();
        threadTraitementCellules.start();
        threadTraitementToxines.start();

        try {
            System.out.println(threadDeplacement.getName());
            threadDeplacement.join();
            threadTraitementCellules.join();
            threadTraitementToxines.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        nettoyerGrille();

        getGrille().getListToxines().removeAll(getToxinesASup());
        getGrille().getListCellules().removeAll(getaSupprimer());
        getGrille().getListNutriments().removeAll(getNutASup());
        getGrille().getListCellules().addAll(getaAjouter());
    }

}



