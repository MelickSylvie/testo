
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

public class GestionCompagnie extends JFrame {

    public static void main(String[] args) {
        int choisir;
        Compagnie flyWithMe = new Compagnie("AIRTRANSIT",50);
        try {
            flyWithMe.chargerVol();
        } catch (Exception e) {
            e.printStackTrace();
        }
        do {
        choisir = Integer.parseInt(flyWithMe.ouvrir());
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
            default:
                JOptionPane.showMessageDialog(new JFrame() ,"Mauvaise entrée, réessayer SVP.");
                break;
        }
    }
    while (choisir != 0);

    }
}
