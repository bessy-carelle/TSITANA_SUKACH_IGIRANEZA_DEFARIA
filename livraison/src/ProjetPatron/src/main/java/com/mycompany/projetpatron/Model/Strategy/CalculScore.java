package com.mycompany.projetpatron.Model.Strategy;

import com.mycompany.projetpatron.Model.GroupeForme;

/**
 * Interface représentant une stratégie de calcul de score dans le cadre du
 * patron de conception <b>Stratégie</b> (Strategy Pattern).
 * <p>
 * Chaque implémentation définit un algorithme de calcul du score basé sur
 * les formes placées par les deux joueurs. Cela permet de varier la logique
 * de scoring sans modifier les classes qui l'utilisent.
 * </p>
 *
 * @author defaria241@CAMPUS
 * @version 1.0
 * @see GroupeForme
 */
public interface CalculScore {

    /**
     * Calcule le score en fonction des formes placées par les deux joueurs.
     *
     * @param formesBleues  le {@link GroupeForme} contenant les formes du joueur bleu
     * @param formesRouges  le {@link GroupeForme} contenant les formes du joueur rouge
     * @return le score calculé sous forme de {@code double}
     */
    double calculer(GroupeForme formesBleues, GroupeForme formesRouges);
}
