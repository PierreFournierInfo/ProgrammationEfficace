import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Guives {
    private static int nbrAzu;
    private static int prix1[];
    private static int hauteur1[];
    private static int prix2[];
    private static int hauteur2[];

    private static Azulejos resultat1[];
    private static Azulejos resultat2[];

    private static boolean impossible = false;

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
                resultat1[i] = new Azulejos(i, prix1[i], hauteur1[i]);
                resultat2[i] = new Azulejos(i, prix2[i], hauteur2[i]);
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
            System.out.print(tab[i].indice+1 + " ");
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

    public static boolean solution(){
        int b1, b2;
        int m1, m2;

        for ( int x = 0; x < nbrAzu; x++ ){
            b1 = getBloc(resultat1, x);
            b2 = getBloc(resultat2, x);
            m1 = findMax(resultat1, x, b1);
            m2 = findClosestToMax(resultat2, x, b2, m1);
            if ( m2 == -1 ){
                return false;
            }
            permut(x, m1, resultat1);
            permut(x, m2, resultat2);
        }
        return true;
    }

    public static int getBloc(Azulejos[] tab, int indice){
        int cpt = 0;
        for ( int i = indice; i < tab.length - 1 ; i++ ){
            if (tab[i+1].prix == tab[indice].prix) {
                cpt++;
            }
        }
        return cpt;
    }

    public static int findMax(Azulejos[] tab, int x, int y){
        int indice = x;
        for ( int i = x; i <= x+y; i++ ){
            if (tab[i].hauteur > tab[indice].hauteur) {
                indice = i;
            }
        }
        return indice;
    }

    public static int findClosestToMax(Azulejos[] tab, int x, int y, int m1){
        Azulejos m2 = new Azulejos(0,0,-1);
        for ( int i = x; i <= x+y; i++ ){
            if (tab[i].hauteur > m2.hauteur && tab[i].hauteur < resultat1[m1].hauteur) {
                m2.indice = i;
                m2.hauteur = tab[i].hauteur;
            }
        }
        if ( m2.hauteur < 0 ){
            return -1;
        } else {
            return m2.indice;
        }
    }

    public static void main(String[] args) {
        parse(args[0]);

        // Résultat
        trie(resultat1);
        trie(resultat2);
        if ( !solution() ){
            System.out.println("impossible");
        } else {
            printAz(resultat1);
            printAz(resultat2);   
        }
	}  
}

