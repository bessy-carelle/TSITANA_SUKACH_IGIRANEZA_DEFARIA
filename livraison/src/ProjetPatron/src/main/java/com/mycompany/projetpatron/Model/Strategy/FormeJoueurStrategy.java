package com.mycompany.projetpatron.Model.Strategy;

import com.mycompany.projetpatron.Model.Forme;
import com.mycompany.projetpatron.Model.GroupeForme;

/**
 * Stratégie de génération de formes basée sur les dessins du joueur 1,
 * dans le cadre du patron de conception <b>Stratégie</b> (Strategy Pattern).
 * <p>
 * Cette implémentation de {@link GenerationStrategy} permet au joueur 1 de
 * placer manuellement des formes rouges sur le plateau. Les formes sont
 * accumulées jusqu'à la validation, après quoi elles sont transmises au jeu.
 * </p>
 *
 * @author defaria241@CAMPUS
 * @version 1.0
 * @see GenerationStrategy
 * @see GroupeForme
 * @see Forme
 */
public class FormeJoueurStrategy implements GenerationStrategy {

    /**
     * Groupe contenant les formes dessinées par le joueur 1.
     */
    private GroupeForme formesDessinées = new GroupeForme();

    /**
     * Indique si le joueur 1 a validé son placement de formes.
     */
    private boolean prêt = false;

    /**
     * Retourne le groupe de formes dessinées par le joueur 1.
     *
     * @return le {@link GroupeForme} contenant les formes placées
     */
    public GroupeForme getFormesDessinee() {
        return formesDessinées;
    }

    /**
     * Ajoute une forme rouge au groupe de formes du joueur 1.
     * <p>
     * La couleur de la forme est automatiquement définie à {@code "ROUGE"}
     * avant l'ajout dans le groupe.
     * </p>
     *
     * @param f la {@link Forme} à ajouter
     */
    public void ajouterForme(Forme f) {
        f.couleur = "ROUGE";
        formesDessinées.ajoutForme(f);
    }

    /**
     * Valide le placement des formes du joueur 1.
     * <p>
     * Après appel de cette méthode, {@link #isPrêt()} retourne {@code true}.
     * </p>
     */
    public void valider() {
        prêt = true;
    }

    /**
     * Indique si le joueur 1 a terminé et validé son placement de formes.
     *
     * @return {@code true} si le joueur a validé, {@code false} sinon
     */
    public boolean isPrêt() {
        return prêt;
    }

    /**
     * Réinitialise la stratégie en vidant le groupe de formes et en
     * remettant l'état de validation à {@code false}.
     * <p>
     * Permet de recommencer un nouveau placement pour le joueur 1.
     * </p>
     */
    public void reset() {
        formesDessinées = new GroupeForme();
        prêt = false;
    }

    /**
     * Retourne le groupe de formes dessinées par le joueur 1.
     * <p>
     * Les paramètres de bornes ne sont pas utilisés dans cette implémentation
     * car les formes sont placées manuellement par le joueur et non générées
     * aléatoirement.
     * </p>
     *
     * @param borneMaxX la borne maximale en X (non utilisée dans cette implémentation)
     * @param borneMaxY la borne maximale en Y (non utilisée dans cette implémentation)
     * @return le {@link GroupeForme} contenant les formes placées par le joueur 1
     */
    @Override
    public GroupeForme generer(int borneMaxX, int borneMaxY) {
        return formesDessinées;
    }
}
