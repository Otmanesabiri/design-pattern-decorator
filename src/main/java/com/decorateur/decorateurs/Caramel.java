package com.decorateur.decorateurs;

import com.decorateur.composant.Boisson;

public class Caramel extends Decorateur {

    // Le prix du supplément caramel (exemple : 0.8)
    private static final double PRIX_CARAMEL = 0.8;

    public Caramel(Boisson boisson) {
        // Appel au constructeur du Decorateur Abstrait
        super(boisson);
    }

    @Override
    public String getDescription() {
        // Récupère la description de l'objet enveloppé et y ajoute sa propre
        // description
        return boisson.getDescription() + " au caramel";
    }

    @Override
    public double cout() {
        // Calcule le coût récursivement : son propre coût + le coût de l'objet
        // enveloppé
        return PRIX_CARAMEL + boisson.cout();
    }

}
