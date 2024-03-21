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

public class Robin {
    private static int nbrAzu;
    private static int prix1[];
    private static int hauteur1[];
    private static int prix2[];
    private static int hauteur2[];

    private static int resultat1[];
    private static int resultat2[];

    private static boolean impossible;

	@SuppressWarnings("unchecked")
    public static void parse(String file){
    	try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String ligne;
            String[] result;

            // Première ligne
           	ligne = bufferedReader.readLine();
            nbrAzu = Integer.parseInt(ligne);

            prix1 = new int[nbrAzu];
            hauteur1 = new int[nbrAzu];
            prix2 = new int[nbrAzu];
            hauteur2 = new int[nbrAzu];
            resultat1 = new int[nbrAzu];
            resultat2 = new int[nbrAzu];

            // Prix1
            ligne = bufferedReader.readLine();
            result = ligne.split(" ");
            for ( int i = 0; i < nbrAzu; i++){
                prix1[i] = Integer.parseInt(result[i]); 
            }

            // Hauteur1
            ligne = bufferedReader.readLine();
            result = ligne.split(" ");
            for ( int i = 0; i < nbrAzu; i++){
                hauteur1[i] = Integer.parseInt(result[i]); 
            }

            // Prix2
            ligne = bufferedReader.readLine();
            result = ligne.split(" ");
            for ( int i = 0; i < nbrAzu; i++){
                prix2[i] = Integer.parseInt(result[i]); 
            }

            // Hauteur2
            ligne = bufferedReader.readLine();
            result = ligne.split(" ");
            for ( int i = 0; i < nbrAzu; i++){
                hauteur2[i] = Integer.parseInt(result[i]); 
            }

            for(int i = 0; i < nbrAzu; i++){
                resultat1[i] = i+1;
                resultat2[i] = i+1;
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTab(int[] tab){
        for ( int i : tab ){
            System.out.print(" " + i);
        }
        System.out.println();
    }

    private static void permut(int i, int j, int[] tab){
        int tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }

    public static void trie(int[] tab, int res){
        //trie par rapport au prix
        for(int i = 0; i < tab.length - 1; i++){
            for(int j = tab.length - 1; j > 1; j--){
            
                if(tab[j] < tab[i]){
                    if(res == 1)permut(i, j, resultat1);
                    else permut(i, j, resultat2);
                }
            }
        }
    }

    public static void main(String[] args) {
        parse(args[0]);

        printTab(prix1);
        printTab(hauteur1);
        printTab(prix2);
        printTab(hauteur2);

        // Résultat
        if ( impossible ){
            System.out.println("impossible");
        }

        printTab(resultat1);
        printTab(resultat2);
        trie(prix1, 1);
        trie(prix2, 2);
        printTab(resultat1);   
        printTab(resultat2);     
	}  
}
