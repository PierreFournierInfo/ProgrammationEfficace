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

public class Main {
    private static int nbrAzu;
    private static int prix1[];
    private static int hauteur1[];
    private static int prix2[];
    private static int hauteur2[];

    private static int resultat1[];
    private static int resultat2[];

    private static boolean impossible;

    public static boolean verif(){
        for(int i=0;i<nbrAzu ; i++){//()On fait un parcours de taille n
            if(resultat1[i]>=0){ //---à chaque indice i de result1 on verifié si il est déplaçable ou non
                if(resultat2[i]>=0){ //--si c'est le cas on vérifie si l'indice i de result2 est déplacable ou non 
                    if(hauteur1[resultat1[i]]<=hauteur2[resultat2[i]]){//-si ce n'est pas le cas on vérifie juste si les 2 blocs vérifient juste la condition.
                        return false;
                    }else{//-si c'est le cas on place a l indice i de resultat2 la tuile qui a la plus grande hauteur parmis celles déplacables.
                        int x=0;
                        while(i+x<=nbrAzu){
                            if( hauteur2[resultat2[i+x]]<hauteur1[resultat1[i]]){
                                resultat2[i].
                            }
                    }
                }
                
            }
            else{
                if(resultat2[i]>=0){

                }else{

                }
            }
        }
        return true;
    }

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

    public static void main(String[] args) {
        parse(args[0]);

        printTab(prix1);
        printTab(hauteur1);
        printTab(prix2);
        printTab(hauteur2);

        // Résultat
        if ( impossible ){
            System.out.println("impossible");
        } else {
            printTab(resultat1);
            printTab(resultat2);
        }
	}  
}
