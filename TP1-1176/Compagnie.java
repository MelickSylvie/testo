
/*
 * Décrivez votre classe Compagnie ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.text.*;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;

public class Compagnie {

    private static String nomDuFichier = "Cie_Air_Relax.txt";
    private static ArrayList<Vol> tabVols = new ArrayList<Vol>();
    public static boolean tabModifie = false;
    public static int nbVols = 0;
    public static final int MAX_PLACES = 340;

    public static void chargerVol() throws IOException {
        BufferedReader tLire = new BufferedReader(new FileReader("./Cie_Air_Relax.txt"));
        String tab[];
        String ligne;
        while ((ligne = tLire.readLine()) != null) {
            tab = ligne.split(":");
            Date date = new Date(Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]));
            tabVols.add(new Vol(tab[0], tab[1], date, Integer.parseInt(tab[5])));
        }
        tLire.close();

    }

    public static void ListeVols() {

        System.out.println("Vos réservations de vols:");

        System.out.println("Numéro     Déstination          Date de départ   Réservations"); // afficher la destination
                                                                                             // et le nombre de
                                                                                             // reservation
        for (Vol vol : tabVols) {
            System.out.println(String.format("%1$-10s %2$-20s %3$-16s %4$s", vol.getNumeroDeVol(), vol.getDestination(),
                    vol.getDateDepart().toString(), vol.getNbReservation()));
            System.out.println();
        }
    }

    public static void InsererVol() {

        String nouveauNumero;
        String nouvelleDestination;
        String choisir;
        int nouveauJour;
        int nouvelleAnnee;
        int nouveauMois;
        int nouvelleReservation;
        do {
            System.out.println("Le Numéro du Vol:");
            nouveauNumero = new Scanner(System.in).nextLine();
            boolean volExistant = RechercherVol(nouveauNumero);

            if (!volExistant) {
                System.out.println("Le Numéro du Vol est accepté:");
                System.out.println("SAISIE D'UN NOUVEAU VOL:");
                System.out.println("Veuillez entrer la déstination:");
                nouvelleDestination = new Scanner(System.in).nextLine();
                System.out.println("Veuillez entrez la date de depart au format jour/../....:");
                nouveauJour = Integer.parseInt(new Scanner(System.in).nextLine());
                System.out.println("Veuillez entrez le mois de depart ../mois/.... ");
                nouveauMois = Integer.parseInt(new Scanner(System.in).nextLine());
                System.out.println("Veuillez entrer en fin l'année de depart ../../Annee ");
                nouvelleAnnee = Integer.parseInt(new Scanner(System.in).nextLine());
                System.out.println("Veuillez entrer le nombre de reservation pour ce nouveau vol ");
                nouvelleReservation = Integer.parseInt(new Scanner(System.in).nextLine());
                tabVols.add(new Vol(nouveauNumero, nouvelleDestination,
                        new Date(nouveauJour, nouveauMois, nouvelleAnnee), nouvelleReservation));
                tabModifie = true;
            } else {
                System.out.println("Ce numero de vol existe déja");

            }
            System.out.print("voulez vous continuer (O/N)    ?");
            choisir = new Scanner(System.in).nextLine().toUpperCase();

        } while (choisir.equals("O"));
    }

    public static boolean RechercherVol(String nouveauNumero) {
        boolean volExistant = false;

        for (Vol unVol : tabVols) {
            if (nouveauNumero.equals(unVol.getNumeroDeVol())) {
                volExistant = true;
                break;
            }
        }
        return volExistant;
    }

    public static void RetirerVol() {
        String choisir;
        String numeroASupprimer;
        boolean volExistant;

        System.out.println("Veuillez entrer le numero de Vol à retirer");
        numeroASupprimer = new Scanner(System.in).nextLine();
        for (int i = 0; tabVols.size(); i++) {
            Vol vol = tabVols.get(i);
            if (vol.getNumeroDeVol() == numeroASupprimer) {
                tabVols.remove(vol);
                // remettre i si ca ne compile pas

                return; // on sort de la fonction ici
            }

        }

        System.out.println("ce vol n'existe pas");
        /**
         * volExistant = RechercherVol(numeroASupprimer);
         * 
         * if (!volExistant) { System.out.println("ce vol n'existe pas"); } else { Vol
         * vol = tabVols.Where(x = numeroASupprimer.equals( >
         * x.NumeroDeVol)).findFirst(); System.out.println("Destination Date de départ
         * Réservations"); //afficher la destination et le nombre de reservation
         * System.out.println(String.format("%1$-20s %2$-16s %3$s", vol.Destination,
         * vol.DateDepart.toString(), vol.NbReservation)); System.out.println("Voulez
         * vous supprimer ce vol ? O/N"); choisir = new Scanner(System.in).nextLine();
         * if (choisir.equals("O")) { tabVols.remove(vol); nbVols--; tabModifie = true;
         * System.out.println("Le vol a été supprimé"); choisir = new
         * Scanner(System.in).nextLine(); } }
         **/
    }

    public static void modificationDeDate() {
        String nouvelleDate;
        String numeroAChanger;

        System.out.println("Veuillez entrer le numero de Vol que vous voulez modifier la date");
        numeroAChanger = new Scanner(System.in).nextLine();

        for (int i = 0; tabVols.size(); i++) {
            Vol vol = tabVols.get(i);
            if (vol.getNumeroDeVol() == numeroAChanger) {
                System.out.println("Déstination          Date de départ   Réservations");
                System.out.println(String.format("%1$-20s %2$-16s %3$s", vol.getDestination(),
                        vol.getDateDepart().toString(), vol.getNbReservation()));

                System.out.println("MODIFICATION DE LA DATE DE DÉPART.");
                System.out.println("Entrer la nouvelle date au format JJ/MM/AAAA:");
                nouvelleDate = new Scanner(System.in).nextLine();
                String[] donnees = nouvelleDate.split(new String[] { "/" }, StringSplitOptions.RemoveEmptyEntries);

                Date date = new Date(Integer.parseInt(donnees[0]), Integer.parseInt(donnees[1]),
                        Integer.parseInt(donnees[2]));
                vol.setDateDepart(date);

                return; // on sort de la fonction ici
            }

        }

        /**
         * volExistant = RechercherVol(numeroASupprimer); if (!volExistant) {
         * System.out.println("ce vol n'existe pas"); } else { Vol vol = tabVols.Where(x
         * = numeroASupprimer.equals(> x.NumeroDeVol)). findFirst() . orElse ( null ) ;
         * System.out.println("Déstination Date de départ Réservations");
         * System.out.println(String.format("%1$-20s %2$-16s %3$s", vol.Destination,
         * vol.DateDepart.toString(), vol.NbReservation));
         * 
         * System.out.println("MODIFICATION DE LA DATE DE DÉPART.");
         * System.out.println("Entrer la nouvelle date au format JJ/MM/AAAA:");
         * nouvelleDate = new Scanner(System.in).nextLine(); String[] donnees =
         * nouvelleDate.split(new String[] {"/"},
         * StringSplitOptions.RemoveEmptyEntries);
         * 
         * Date date = new Date(Integer.parseInt(donnees[0]),
         * Integer.parseInt(donnees[1]), Integer.parseInt(donnees[2])); for (Vol unVol :
         * tabVols) { if (numeroASupprimer.equals(unVol.NumeroDeVol)) { unVol.DateDepart
         * = date; tabModifie = true; break; } }
         **/

    }

    public static void reservationDeVol() {
        String nouveauVolReserver;
        int nouvellePlace;

        System.out.println("veuillez inscrire le numero du vol que vous souhaitez reserver:");
        nouveauVolReserver = new Scanner(System.in).nextLine();
        for (int i = 0; tabVols.size(); i++) {
            Vol vol = tabVols.get(i);
            if (vol.getNumeroDeVol() == nouveauVolReserver) {
                int nbReservation = vol.getNbReservation();
                if (nbReservation < MAX_PLACES) {
                    vol.setNbReservation(nbReservation + 1);
                    return;
                }

                system.out.println("Il n'y a pas de place disponible dans ce vol");
                return;

            }

        }
        System.out.println("Ce vol n'existe pas");

        /**
         * if (!choixReponse) { System.out.println("Ce vol n'existe pas"); } else { Vol
         * vol = tabVols.Where(x = nouveauVolReserver.equals(>
         * x.NumeroDeVol)).FirstOrDefault();
         * 
         * if (vol.NbReservation > MAX_PLACES) { System.out.println("il n'y a pas de
         * place disponible dans ce vol"); } else { System.out.println("Destination Date
         * de départ le nombre de places restantes dans l'avion");
         * System.out.println(String.format("%1$-20s %2$-16s %3$s", vol.Destination,
         * vol.DateDepart.toString(), 340 - vol.NbReservation));
         * System.out.println("Combien de place désirez-vous reservez ?"); nouvellePlace
         * = Integer.parseInt(new Scanner(System.in).nextLine()); if ((vol.NbReservation
         * + nouvellePlace) > MAX_PLACES) { System.out.println("Il n'y a pas assez de
         * place dans ce vol"); } else if ((vol.NbReservation + nouvellePlace) <=
         * MAX_PLACES) { for (Vol unVol : tabVols) { if
         * (nouveauVolReserver.equals(unVol.NumeroDeVol)) { unVol.NbReservation =
         * unVol.NbReservation + nouvellePlace; tabModifie = true; break; } }
         * 
         * }
         * 
         * }
         **/

    }

    public static void ecritureFichier() {
        if (tabModifie && tabVols.Any()) {
            ArrayList<String> lignes = new ArrayList<String>();
            for (Vol vol : tabVols) {
                lignes.add(String.format("%1$-2s %2$-20s %3$-2s %4$-2s %5$-2s %6$s", vol.getNumeroDeVol(),
                        vol.getDestination(), vol.getDateDepart().getJour(), vol.getDateDepart().getMois(),
                        vol.getDateDepart().getAnnee(), vol.getNbReservation()));
            }
            File.WriteAllLines("../../../Cie_Air_Relax.txt", lignes, Encoding.UTF8);
        }
    }

}