package com.decorateur.composant;

public abstract class Boisson {
    // Attribut protégé pour permettre l'accès aux sous-classes [5, 6]
    protected String description; 

    // Méthode concrète pour obtenir la description 
    public String getDescription() { 
        return description;
    }
    
    // Méthode abstraite, implémentée par toutes les sous-classes pour définir le coût de base [7, 8]
    public abstract double cout(); 
}