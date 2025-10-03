package com.decorateur.decorateurs;

import com.decorateur.composant.Boisson;
// Le Décorateur doit être une sous-classe du Composant (Boisson) [1]
public abstract class Decorateur extends Boisson { 
    
    // Agrégation : L'objet à décorer ou le décorateur suivant dans la chaîne [2, 10]
    protected Boisson boisson; 

    // Constructeur qui reçoit l'objet à décorer [2, 11]
    public Decorateur(Boisson boisson) { 
        this.boisson = boisson;
    }

    // Ces méthodes sont souvent laissées abstraites ici, mais doivent être redéfinies
    // par les décorateurs concrets pour y ajouter leur propre responsabilité [11].
    @Override
    public abstract String getDescription();
    
    @Override
    public abstract double cout();
}
