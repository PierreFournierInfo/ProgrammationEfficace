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

    private static Azulejos resultat1[];
    private static Azulejos resultat2[];

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
            resultat1 = new Azulejos[nbrAzu];
            resultat2 = new Azulejos[nbrAzu];

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

            //resultat1 et resultat2
            for(int i = 0; i < nbrAzu; i++){
                resultat1[i] = new Azulejos(i+1, prix1[i], hauteur1[i]);
                resultat2[i] = new Azulejos(i+1, prix2[i], hauteur2[i]);
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

    public static void printAz(Azulejos[] tab){
        for(int i = 0; i < tab.length; i++){
            System.out.print(tab[i].indice + " ");
        }
        System.out.println();
    }

    private static void permut(int i, int j, Azulejos[] tab){
        Azulejos tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }

    public static void trie(Azulejos[] tab){
        //trie par rapport au prix
        for(int i = 0; i < tab.length - 1; i++){
            for(int j = tab.length - 1; j > i; j--){
                //Si ils ont le même prix et ont une hauteur différente
                if(tab[j].prix == tab[i].prix){
                    if(tab[j].hauteur < tab[i].hauteur){
                        permut(i, j, tab);
                    }
                    continue;
                }
                //Si l'un est plus petit que l'autre
                if(tab[j].prix < tab[i].prix){
                    permut(i, j, tab);
                }
            }
        }
    }

    public static boolean verif(){
        for(int i = 0; i < nbrAzu; i++){
            if(i < nbrAzu-1){
                if(resultat1[i].prix > resultat1[i+1].prix){
                    System.out.println(resultat1[i]);
                    System.out.println(resultat1[i+1]);
                    return false;
                }
                if(resultat1[i].hauteur <= resultat2[i].hauteur){
                    System.out.println("res1 " + resultat1[i]);
                    System.out.println("res2 " + resultat2[i]);
                    return false;
                }
            }
            else{
                if(resultat1[i].hauteur <= resultat2[i].hauteur){
                    System.out.println("res1 " + resultat1[i]);
                    System.out.println("res2 " + resultat2[i]);
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        parse(args[0]);

        printTab(prix1);
        printTab(hauteur1);
        printTab(prix2);
        printTab(hauteur2);
        System.out.println();

        // Résultat
        if ( impossible ){
            System.out.println("impossible");
        }
        trie(resultat1);
        trie(resultat2);
        printAz(resultat1);
        printAz(resultat2);
        System.out.println(verif());
	}  
}

