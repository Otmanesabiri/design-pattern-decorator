package com.decorateur;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;

import com.decorateur.composant.Boisson;
import com.decorateur.composant.Espresso;
import com.decorateur.composant.Deca;
import com.decorateur.composant.Sumatra;
import com.decorateur.decorateurs.Chocolat;
import com.decorateur.decorateurs.Caramel;

public class PrimaryController {

    @FXML
    private RadioButton espressoRadio;
    
    @FXML
    private RadioButton decaRadio;
    
    @FXML
    private RadioButton sumatraRadio;
    
    @FXML
    private Label chocolatQteLabel;
    
    @FXML
    private Label caramelQteLabel;
    
    @FXML
    private TextArea historiqueArea;
    
    @FXML
    private ToggleGroup boissonGroup;
    
    @FXML
    private Label prixLabel;
    
    // Compteurs pour les quantités
    private int chocolatQte = 0;
    private int caramelQte = 0;
    
    // Compteur de commandes
    private int numeroCommande = 1;
    
    // Format pour l'heure
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    // Méthode appelée automatiquement après le chargement du FXML
    @FXML
    public void initialize() {
        // Ajouter des listeners pour mettre à jour le prix en temps réel
        espressoRadio.setOnAction(e -> calculerPrixTempsReel());
        decaRadio.setOnAction(e -> calculerPrixTempsReel());
        sumatraRadio.setOnAction(e -> calculerPrixTempsReel());
        
        // Calcul initial du prix
        calculerPrixTempsReel();
    }
    
    @FXML
    private void augmenterChocolat() {
        chocolatQte++;
        chocolatQteLabel.setText(String.valueOf(chocolatQte));
        calculerPrixTempsReel();
    }
    
    @FXML
    private void diminuerChocolat() {
        if (chocolatQte > 0) {
            chocolatQte--;
            chocolatQteLabel.setText(String.valueOf(chocolatQte));
            calculerPrixTempsReel();
        }
    }
    
    @FXML
    private void augmenterCaramel() {
        caramelQte++;
        caramelQteLabel.setText(String.valueOf(caramelQte));
        calculerPrixTempsReel();
    }
    
    @FXML
    private void diminuerCaramel() {
        if (caramelQte > 0) {
            caramelQte--;
            caramelQteLabel.setText(String.valueOf(caramelQte));
            calculerPrixTempsReel();
        }
    }
    
    /**
     * Calcule et affiche le prix en temps réel selon les sélections
     */
    private void calculerPrixTempsReel() {
        Boisson boisson = creerBoisson();
        
        if (boisson != null) {
            double prix = boisson.cout();
            prixLabel.setText(String.format("%.2f €", prix));
        } else {
            prixLabel.setText("0.00 €");
        }
    }
    
    /**
     * Crée la boisson avec tous les décorateurs selon les sélections
     */
    private Boisson creerBoisson() {
        // 1. Créer la boisson de base selon le choix
        Boisson boisson = null;
        
        if (espressoRadio.isSelected()) {
            boisson = new Espresso();
        } else if (decaRadio.isSelected()) {
            boisson = new Deca();
        } else if (sumatraRadio.isSelected()) {
            boisson = new Sumatra();
        }
        
        if (boisson == null) {
            return null;
        }
        
        // 2. Appliquer les décorateurs selon les quantités
        // Ajouter le chocolat autant de fois que demandé
        for (int i = 0; i < chocolatQte; i++) {
            boisson = new Chocolat(boisson);
        }
        
        // Ajouter le caramel autant de fois que demandé
        for (int i = 0; i < caramelQte; i++) {
            boisson = new Caramel(boisson);
        }
        
        return boisson;
    }

    @FXML
    private void commander() {
        Boisson boisson = creerBoisson();
        
        // Vérifier qu'une boisson est sélectionnée
        if (boisson == null) {
            return;
        }
        
        // Obtenir l'heure actuelle
        String heure = LocalDateTime.now().format(timeFormatter);
        
        // Créer l'entrée pour l'historique
        StringBuilder historique = new StringBuilder();
        
        // Ajouter le séparateur si ce n'est pas la première commande
        if (!historiqueArea.getText().isEmpty()) {
            historique.append("\n");
        }
        
        historique.append("═══════════════════════════════════════\n");
        historique.append("Commande #").append(numeroCommande).append(" - ").append(heure).append("\n");
        historique.append("───────────────────────────────────────\n");
        historique.append("☕ ").append(boisson.getDescription()).append("\n");
        
        // Afficher le détail des suppléments
        if (chocolatQte > 0 || caramelQte > 0) {
            historique.append("\nSuppléments :\n");
            if (chocolatQte > 0) {
                historique.append("  🍫 Chocolat x").append(chocolatQte)
                       .append(" = ").append(String.format("%.2f", chocolatQte * 1.20)).append(" €\n");
            }
            if (caramelQte > 0) {
                historique.append("  🍯 Caramel x").append(caramelQte)
                       .append(" = ").append(String.format("%.2f", caramelQte * 0.80)).append(" €\n");
            }
        }
        
        historique.append("\nCoût total : ").append(String.format("%.2f", boisson.cout())).append(" €\n");
        historique.append("═══════════════════════════════════════\n");
        
        // Ajouter à l'historique (en haut)
        historiqueArea.appendText(historique.toString());
        
        // Scroller automatiquement vers le bas pour voir la nouvelle commande
        historiqueArea.setScrollTop(Double.MAX_VALUE);
        
        // Incrémenter le numéro de commande
        numeroCommande++;
        
        // Réinitialiser la commande actuelle
        reinitialiser();
    }
    
    @FXML
    private void reinitialiser() {
        // Réinitialiser les sélections
        espressoRadio.setSelected(true);
        chocolatQte = 0;
        caramelQte = 0;
        chocolatQteLabel.setText("0");
        caramelQteLabel.setText("0");
        calculerPrixTempsReel();
    }
    
    @FXML
    private void effacerHistorique() {
        // Effacer l'historique
        historiqueArea.clear();
        numeroCommande = 1;
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
