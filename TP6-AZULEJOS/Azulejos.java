public class Azulejos {
    public int indice;
    public int prix;
    public int hauteur;

    public Azulejos(int i, int p, int h){
        indice = i;
        prix = p;
        hauteur = h;
    }

    public String toString(){
        return "indice : " + indice + ", prix : " + prix + ", hauteur : " + hauteur;
    }
    
}
