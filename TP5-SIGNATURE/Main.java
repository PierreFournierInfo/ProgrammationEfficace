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


public class Main{

	private static String ligne;
    private static char[] alpha; /*{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; */
    private static String[] tab;
    private static int taille;


	@SuppressWarnings("unchecked")
    public static void parse(String file){
    	try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            alpha = new char[26];
            for(int i = 0; i < 26; i++){
                alpha[i] = (char)(65 + i);
            }
		

           	ligne = bufferedReader.readLine();

            taille=ligne.length();

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
		parse(args[0]);
        taille=ligne.length();

		System.out.println(ligne);
        taille=ligne.length();
        tab=new String[taille];
        //*********On trouve la solution au problème et la renvoie dans un fichier de sortie *******************/
        String resultat=recherche();
           

        //System.out.println("\n Nombre de recettes possibles "+nombreRecettes);
        try{
            String nomFichier =  args[0]+".out";
            FileWriter fileWriter = new FileWriter(nomFichier, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("Signature : " + resultat ); // Écrit le paramètre dans le fichier
            printWriter.close(); // Ferme le PrintWriter pour libérer les ressources
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}

    public static String recherche(){
        for(int a=0;a<taille;a++){
            tab[a]=String.valueOf(ligne.charAt(a));
            
        }
        if (Compare(taille)!="Echec"){
            return Compare(taille);
        }
        for(int i=0; i<taille-1;i++){
            
            for(int j=0;j<taille-1;j++){
                if(i+tab[i].length()<taille){ //REVOIR POTENTIELLEMENT ICI
                    tab[i]+=ligne.charAt(j);
                    
                }
            }
            if (Compare(taille)!="Echec"){
                return Compare(taille);
            }
        }
        return ligne;
    }

    public static String Compare( int n){
        boolean b=false;
        for(int i=0; i<n; i++){
            b=false;
            System.out.println("c");
            for(int j=0; j<n; j++){
                if(i!=j && tab[i].equals(tab[j])){
                    b=true;
                }
            }
            if(b==false){
                return tab[i];
            }
            b=false;
        }
        return "ECHEC";
    }

	
}