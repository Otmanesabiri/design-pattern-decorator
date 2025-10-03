package com.decorateur.decorateurs;

import com.decorateur.composant.Boisson;

public class Chocolat extends Decorateur {

    // Le prix du supplément chocolat (exemple : 1.2)
    private static final double PRIX_CHOCOLAT = 1.2;

    public Chocolat(Boisson boisson) {
        // Appel au constructeur du Decorateur Abstrait
        super(boisson);
    }

    @Override
    public String getDescription() {
        // Récupère la description de l'objet enveloppé et y ajoute sa propre description [12, 13]
        return boisson.getDescription() + " au chocolat";
    }

    @Override
    public double cout() {
        // Calcule le coût récursivement : son propre coût + le coût de l'objet enveloppé [12]
        return PRIX_CHOCOLAT + boisson.cout(); 
    }
}
