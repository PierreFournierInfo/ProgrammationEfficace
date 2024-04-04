import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class Pierre {
    private static String[] tabSousChaines;
	private static String chaine;
	private static int nbrSousChaines;
	private static String FILENAME;
	private static ArrayList<Integer>[] motif; 
	private static int [] result;
	private static String resultat;
	private static int debut;

	private static int taille;
	private static String nul="⊥";
    public static void solution1(){
        int[] liste=new int[motif.length];
        int k=0;

        //On parcour la premiere ligne de taille n 
        for(int i=0;i<motif[0].size();i++){
                
            int min=motif[0].get(i);
            int max=motif[0].get(i);

            //Chaque element de la premiere ligne va etre comparé à l'élément le plus proche des autres lignes
            for(int j=1;j<motif.length;j++){
                int indice=0;
                //On trouve l element le plus proche de chaque ligne
                while(indice<motif[j].size()){

                }
            }


            liste[k]=max-min;
            k++;
            


        }
	}

	public static void reponse(){
		debut=result[0];
		resultat=chaine.substring(debut,debut+taille);
	}
}
