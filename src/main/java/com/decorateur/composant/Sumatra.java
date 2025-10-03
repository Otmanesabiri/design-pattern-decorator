package com.decorateur.composant;

public class Sumatra extends Boisson {

    public Sumatra() {
        // Initialisation de la description de base [5]
        description = "Sumatra"; 
    }

    @Override
    public double cout() {
        // Retourne le coût de base (par exemple 6.0) [5, 9]
        return 6.0; 
    }
}