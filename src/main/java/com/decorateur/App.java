package com.decorateur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App - Café Décorateur
 * Démonstration du pattern Décorateur avec une interface utilisateur
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 700, 900);
        stage.setTitle("☕ Café Décorateur - Pattern Design");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMinWidth(700);
        stage.setMinHeight(700);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        // Lancement de l'application JavaFX
        launch(args);
        
        // Exemple d'utilisation du pattern Décorateur en console
        /*
        // 1. Création de la boisson de base 
        Boisson b1 = new Sumatra(); 

        // 2. Première décoration : on enveloppe Sumatra dans Chocolat
        b1 = new Chocolat(b1); 
        
        // 3. Deuxième décoration : on enveloppe Chocolat dans un autre Chocolat (double dose)
        b1 = new Chocolat(b1);

        // 4. Affichage du résultat final
        System.out.println("Description : " + b1.getDescription());
        System.out.println("Coût total : " + b1.cout() + " €"); 
        */
    }


}