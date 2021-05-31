
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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class Compagnie {

    private static ArrayList<Vol> tabVols;
    private int nbVols;
    private int maxVol;
    public static final int MAX_PLACES = 340;
    private String name;
    private JFrame dialog; // frame pour les dialog
    private JFrame frame; // frame principale pour les informations

    public Compagnie(String name, int maxVol) {
        // initialisation des attributs par paramtres
        this.name = name;
        this.maxVol = maxVol;
        // initialisation des attribut non parametrable
        nbVols = 0;
        tabVols = new ArrayList<Vol>();
        nbVols = 0;
        dialog = new JFrame("Option de gestion pour" + name);
        frame = new JFrame(name);
        frame.setLayout(new GridLayout(1, 1));
        dialog.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public String ouvrir() {
        return JOptionPane.showInputDialog(dialog, "1. Listes des vols \n" + "2. Ajout d'un vol  \n"
                + "3. Retrait d'un vol\n" + "4. Modif. date depart\n" + "5. Reserver un vol \n" + "0. Terminer");
    }

    public void chargerVol() throws IOException {
        BufferedReader tLire = new BufferedReader(new FileReader("Cie_Air_relax.txt"));
        String tab[];
        String ligne;
        while ((ligne = tLire.readLine()) != null) {
            tab = ligne.split(";");
            Date date = new Date(Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]));
            tabVols.add(new Vol(tab[0], tab[1], date, Integer.parseInt(tab[5])));
        }
        tLire.close();
    }

    public void ListeVols() {

        String message = "Vos réservations de vols: \n"
                + "Numéro     Déstination          Date de départ   Réservations\n"; // afficher la destination
        for (Vol vol : tabVols) {
            message += String.format("%1$-10s %2$-20s %3$-16s %4$s", vol.getNumeroDeVol(), vol.getDestination(),
                    vol.getDateDepart().toString(), vol.getNbReservation()) + "\n";
        }
        JTextArea area = new JTextArea(message);
        area.setFont(new Font("Courier", Font.PLAIN, 12));
        area.setBounds(10, 30, 200, 200);
        frame.add(area);
        frame.setBounds(50, 50, 300, 300);
        frame.setVisible(true);
        frame.repaint();
    }

    public void InsererVol() {
        if (nbVols == maxVol) {
            JOptionPane.showMessageDialog(dialog, "Impossible d'inserer de nouveaux vols, liste est pleinne \n");
            return;
        }
        String numVol = JOptionPane.showInputDialog(dialog, "numero du vol:\n");
        if (RechercherVol(numVol)) {
            JOptionPane.showMessageDialog(dialog, "Ce numero Existe deja");
            return;
        }
        String dateStr = JOptionPane.showInputDialog(dialog, "Date du vol (jj/mm/aaaa):\n");
        String date[] = dateStr.split("/");
        String dest = JOptionPane.showInputDialog(dialog, "Destination:\n");
        tabVols.add(new Vol(numVol, dest,
                new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])), 0));
        ecritureFichier();
    }

    private boolean RechercherVol(String nouveauNumero) {
        boolean volExistant = false;

        for (Vol unVol : tabVols) {
            if (nouveauNumero.equals(unVol.getNumeroDeVol())) {
                volExistant = true;
                break;
            }
        }
        return volExistant;
    }

    public void RetirerVol() {
        // utiliser la methode "RechercherVol()" ne fera que complexifié la solution
        String numeroASupprimer = JOptionPane.showInputDialog(dialog, "numero du vol:\n");
        for (int i = 0; i < tabVols.size(); i++) {
            Vol vol = tabVols.get(i);
            String nVol = vol.getNumeroDeVol();
            if (nVol.equals(numeroASupprimer)) {
                int confirmation = JOptionPane.showConfirmDialog(dialog,
                        "Désirez-vous vraiment retirer ce vol ? \n" + String.format("%1$-20s %2$-16s %3$s",
                                vol.getDestination(), vol.getDateDepart().toString(), vol.getNbReservation()));
                System.out.print("found you");
                if (confirmation == JOptionPane.YES_OPTION) {
                    tabVols.remove(vol);
                    ecritureFichier();
                }
                return; // on sort de la fonction ici
            }
        }
        JOptionPane.showMessageDialog(dialog, "Ce vol n'existe pas");
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

    public void modificationDeDate() {
        String numVol = JOptionPane.showInputDialog(dialog,
                "Veuillez entrer le numero de Vol que vous voulez modifier la date\n");

        for (int i = 0; i < tabVols.size(); i++) {
            Vol vol = tabVols.get(i);
            if (vol.getNumeroDeVol().equals(numVol)) {
                String newDate = JOptionPane
                        .showInputDialog(dialog,
                                String.format("%1$-20s %2$-16s %3$s", vol.getDestination(),
                                        vol.getDateDepart().toString(), vol.getNbReservation())
                                        + "\nEntrer la nouvelle date au format JJ/MM/AAAA:");
                String date[] = newDate.split("/");
                vol.setDateDepart(
                        new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])));
                ecritureFichier();
                return; // on sort de la fonction ici
            }
            JOptionPane.showMessageDialog(dialog, "Ce vol n'existe");
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

    public void reservationDeVol() {
        String nouveauVolReserver = JOptionPane.showInputDialog(dialog,
                "veuillez inscrire le numero du vol que vous souhaitez reserver:");
        for (int i = 0; i < tabVols.size(); i++) {
            Vol vol = tabVols.get(i);
            if (vol.getNumeroDeVol().equals(nouveauVolReserver)) {
                int nbReservation = vol.getNbReservation();
                if (nbReservation < MAX_PLACES) {
                    vol.setNbReservation(nbReservation + 1);
                    ecritureFichier();
                    return;
                }

                JOptionPane.showMessageDialog(dialog, "Il n'y a plus de place disponible dans ce vol");
                return;

            }

        }
        JOptionPane.showMessageDialog(dialog, "Ce vol n'existe pas");

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

    public void ecritureFichier() {
        String str = "";
        for (int i = 0; i < tabVols.size(); ++i) {
            Vol vol = tabVols.get(i);
            str += vol.getNumeroDeVol() + ";" + vol.getDestination() + ";" + vol.getDateDepart().getJour() + ";"
                    + vol.getDateDepart().getMois() + ";" + vol.getDateDepart().getAnnee() + ";"
                    + vol.getNbReservation() + "\n";

        }
        try {
            FileWriter fw = new FileWriter("Cie_Air_relax.txt");
            fw.write(str);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}