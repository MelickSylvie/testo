public class Date
{
    private int annee;
    private int mois;
    private int jour;
    //constructeurs sans parametres
    public Date()
    {
        Annee = 0;
        Mois = 0;
        Jour = 0;
    }
     // constructeurs avec 3 parametres
    public Date (int jour, int mois, int annee)
    {
        Annee = annee;
        Mois = mois;
        Jour = jour;
    }
    
     // get
    
    public int getAnnee()
    {
        return annee;
    }
    
        public int getMois ()
    {
        return mois;
    } 
    
        public int getJour()
    {
        return jour;
    }
    
    
    public String toString()
    {
        return String.format("%1$s/%2$s/%3$s", getJour(), getMois(), getAnnee());
    }
}


