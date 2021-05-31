public class Date
{
    private int annee;
    private int mois;
    private int jour;
    //constructeurs sans parametres
    public Date()
    {
        annee = 0;
        mois = 0;
        jour = 0;
    }
     // constructeurs avec 3 parametres
    public Date (int jour, int mois, int annee)
    {
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
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


