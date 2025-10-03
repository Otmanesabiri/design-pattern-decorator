package com.decorateur.composant;



public class Espresso extends Boisson {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cout() {
        return 4.0;
    }

}
