import java.util.*;
import java.time.*;

public class Vol
{
    private String numeroDeVol;
    private String destination;
    private LocalDateTime dateDepart = LocalDateTime.MIN;
    private int nbReservation;
    
    private static int nbVols = 0;

    public Vol(String numeroDeVol, String destination, Date dateDepart, int nbReservation)
    {
        nbVols++;
        NumeroDeVol = numeroDeVol;
        Destination = destination;
        DateDepart = dateDepart;
        NbReservation = nbReservation;
    }

    private String NumeroDeVol;
    public String getNumeroDeVol()
    {
    return numeroDeVol;
    }
    
    private String Destination;
    public String getDestination()
    {
        return destination;
    }
    
    private Date DateDepart;
    public Date getDateDepart()
    {
        return DateDepart;
    }
      public void setDateDepart(Date value)
    {
    DateDepart = value;
    }
    
    private int NbReservation ;
    public int getNbReservation()
    {
        return NbReservation;
    }
    public final void setNbReservation(int value)
    {
        NbReservation = value;
    }
    
    
    
}



