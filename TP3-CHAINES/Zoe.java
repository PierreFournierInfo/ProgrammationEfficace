import java.util.ArrayList;


public class Zoe {
    
    private static String[] tabSousChaines;
	private static String chaine;
	private static int nbrSousChaines;
    private static ArrayList[] motif;


    public static boolean estPresent(String sousC, int i){
        for(int j=0; j<sousC.length(); j++){
            if(sousC.charAt(j)!=chaine.charAt(i+j)){
                return false;
            }
        }
        return true;
    }




}
