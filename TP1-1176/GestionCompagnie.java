
/**
 * Décrivez votre classe GestionCompagnie ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.text.*;
import java.awt.Font;

public class GestionCompagnie {
    public static int montrerOptions() {
        // int choisir;

        System.out.println("        GESTION DES VOLS");
        System.out.println("1. Liste des vols");
        System.out.println("2. Ajour d'un vol");
        System.out.println("3. Retrait d'un vol");
        System.out.println("4. Modification de la date de depart");
        System.out.println("5. Réservation d'un vol");
        System.out.println("0. Terminer");
        System.out.println("Faites Votre choixReponse:");

        return Integer.parseInt(new Scanner(System.in).nextLine());

    }

    public static void main(String[] args) {
        int choisir;
        Compagnie flyWithMe = new Compagnie();
        try {
            flyWithMe.chargerVol();
        }
        catch(Exception e) {
            System.out.println("file issue");
        }
        do {
            choisir = montrerOptions();
            switch (choisir) {
                case 1:
                    flyWithMe.ListeVols();
                    break;
                case 2:
                    flyWithMe.InsererVol();
                    break;
                case 3:
                    flyWithMe.RetirerVol();
                    break;
                case 4:
                    flyWithMe.modificationDeDate();
                    break;
                case 5:
                    flyWithMe.reservationDeVol();
                    break;
                case 0:
                    flyWithMe.ecritureFichier();
                    break;
            }

        } while (choisir != 0);

    }
}
