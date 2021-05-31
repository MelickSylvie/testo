import java.util.*;
import java.time.*;

public class Vol {
    private String numeroDeVol;
    private String destination;
    private Date DateDepart;
    private int NbReservation;

    public Vol(String numeroDeVol, String destination, Date dateDepart, int nbReservation) {
        this.numeroDeVol = numeroDeVol;
        this.destination = destination;
        this.DateDepart = dateDepart;
        this.NbReservation = nbReservation;
    }

    public String getNumeroDeVol() {
        return numeroDeVol;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDateDepart() {
        return DateDepart;
    }
    public int getNbReservation() {
        return NbReservation;
    }

    public void setDateDepart(Date value) {
        DateDepart = value;
    }


    public final void setNbReservation(int value) {
        NbReservation = value;
    }

}
