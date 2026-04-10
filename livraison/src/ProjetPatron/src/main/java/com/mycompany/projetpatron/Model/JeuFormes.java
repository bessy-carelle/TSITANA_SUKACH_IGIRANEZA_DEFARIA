package com.mycompany.projetpatron.Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import com.mycompany.projetpatron.Model.Strategy.CalculScore;
import com.mycompany.projetpatron.Model.Strategy.GenerationStrategy;
import com.mycompany.projetpatron.Model.Strategy.ScoreParSurface;
import com.mycompany.projetpatron.Model.Strategy.FormeJoueurStrategy;

/**
 * Classe principale du jeu de formes.
 * <p>
 * Elle gère le déroulement des parties, la génération des formes,
 * le calcul des scores, ainsi que les différents modes de jeu
 * (solo et deux joueurs).
 * </p>
 */
public class JeuFormes extends AbstractModeleEcoutable {

    private GenerationStrategy generationStrategy;
    private GroupeForme formesJoueur;   // formes bleues
    private GroupeForme obstacles;      // formes rouges
    private Timer timerObstacles;
    private CalculScore calculScore = new ScoreParSurface();

    private int numPartie = 0;
    private static final int TOTAL_PARTIES = 4;
    private List<Integer> scores = new ArrayList<>();
    private List<Double> pourcentages = new ArrayList<>();

    public enum Phase {
        JOUEUR1_PLACE_ROUGE,
        JOUEUR2_PLACE_BLEU
    }

    private Phase phaseActuelle = Phase.JOUEUR1_PLACE_ROUGE;
    private boolean modeDeuxJoueurs = false;

    /**
     * Constructeur du jeu.
     * Initialise les groupes de formes du joueur et des obstacles.
     */
    public JeuFormes() {
        this.formesJoueur = new GroupeForme();
        this.obstacles = new GroupeForme();
    }

    /**
     * Définit la stratégie de génération des formes.
     *
     * @param s stratégie de génération
     */
    public void setGenerationStrategy(GenerationStrategy s) {
        this.generationStrategy = s;
    }

    /**
     * Démarre une nouvelle partie.
     * Génère les obstacles et initialise le joueur.
     * Les obstacles disparaissent après 30 secondes.
     *
     * @param borneMaxX borne maximale en X
     * @param borneMaxY borne maximale en Y
     */
    public void demarrerPartie(int borneMaxX, int borneMaxY) {
        numPartie++;
        this.obstacles = generationStrategy.generer(borneMaxX, borneMaxY);
        this.formesJoueur = new GroupeForme();
        fireChangement();

        timerObstacles = new Timer(30000, e -> {
            for (Forme f : obstacles.getFormes()) {
                f.active = false;
            }
            fireChangement();
            timerObstacles.stop();
        });

        timerObstacles.setRepeats(false);
        timerObstacles.start();
    }

    /**
     * Retourne les obstacles (formes rouges).
     *
     * @return groupe des obstacles
     */
    public GroupeForme getObstacles() {
        return obstacles;
    }

    /**
     * Retourne les formes du joueur (formes bleues).
     *
     * @return groupe des formes du joueur
     */
    public GroupeForme getFormesDuJoueur() {
        return formesJoueur;
    }

    /**
     * Valide la partie en cours, calcule le score et enregistre les résultats.
     *
     * @param borneMaxX borne maximale en X
     * @param borneMaxY borne maximale en Y
     * @return true si la partie peut continuer, false si elle est terminée
     */
    public boolean validerPartie(int borneMaxX, int borneMaxY) {
        int score = (int) calculerScore();
        double pourcentage = ((ScoreParSurface) calculScore)
                .calculerPourcentage(formesJoueur, obstacles);

        scores.add(score);
        pourcentages.add(pourcentage);
        fireChangement();

        if (modeDeuxJoueurs) return true;
        return numPartie >= TOTAL_PARTIES;
    }

    /**
     * Retourne la liste des pourcentages obtenus.
     *
     * @return liste des pourcentages
     */
    public List<Double> getPourcentages() {
        return pourcentages;
    }

    /**
     * Calcule le score actuel de la partie.
     *
     * @return score calculé
     */
    public double calculerScore() {
        return calculScore.calculer(formesJoueur, obstacles);
    }

    /**
     * Retourne le résultat sous forme textuelle.
     *
     * @return résultat du calcul
     */
    public String calculerResultat() {
        return ((ScoreParSurface) calculScore)
                .calculerResultat(formesJoueur, obstacles);
    }

    /**
     * Calcule la moyenne des scores.
     *
     * @return moyenne des scores
     */
    public double getMoyenne() {
        if (scores.isEmpty()) return 0;
        return scores.stream().mapToInt(i -> i).average().orElse(0);
    }

    /**
     * Calcule la moyenne en pourcentage des scores.
     *
     * @return pourcentage moyen
     */
    public int getMoyennePourcentage() {
        if (scores.isEmpty()) return 0;

        int scoreTotal = scores.stream().mapToInt(i -> i).sum();
        int scoreMax = TOTAL_PARTIES * 4;

        return (int) Math.floor((scoreTotal / (double) scoreMax) * 100);
    }

    /**
     * Retourne le numéro de la partie actuelle.
     *
     * @return numéro de partie
     */
    public int getNumeroPartie() {
        return numPartie;
    }

    /**
     * Retourne le nombre total de parties.
     *
     * @return total des parties
     */
    public int getTotalParties() {
        return TOTAL_PARTIES;
    }

    /**
     * Retourne la liste des scores.
     *
     * @return liste des scores
     */
    public List<Integer> getScores() {
        return scores;
    }

    /**
     * Notifie la vue d'un changement dans le modèle.
     */
    public void notifierVue() {
        fireChangement();
    }

    /**
     * Active le mode deux joueurs.
     *
     * @param strategie stratégie utilisée pour le joueur
     */
    public void activerModeDeuxJoueurs(FormeJoueurStrategy strategie) {
        this.modeDeuxJoueurs = true;
        this.generationStrategy = strategie;
        this.phaseActuelle = Phase.JOUEUR1_PLACE_ROUGE;
        this.formesJoueur = new GroupeForme();
        this.obstacles = strategie.getFormesDessinee();
        fireChangement();
    }

    /**
     * Retourne la phase actuelle du jeu.
     *
     * @return phase actuelle
     */
    public Phase getPhase() {
        return phaseActuelle;
    }

    /**
     * Indique si le mode deux joueurs est activé.
     *
     * @return true si mode deux joueurs actif
     */
    public boolean isModeDeuxJoueurs() {
        return modeDeuxJoueurs;
    }

    /**
     * Passe au joueur 2 et régénère les formes.
     *
     * @param borneMaxX borne maximale X
     * @param borneMaxY borne maximale Y
     */
    public void joueur1Valide(int borneMaxX, int borneMaxY) {
        phaseActuelle = Phase.JOUEUR2_PLACE_BLEU;
        this.obstacles = generationStrategy.generer(borneMaxX, borneMaxY);
        this.formesJoueur = new GroupeForme();
        fireChangement();
    }
}