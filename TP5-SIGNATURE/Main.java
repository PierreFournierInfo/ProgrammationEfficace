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
    private static String ligne;
    private static char[] alpha; /*{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; */
    private static ArrayList<String> sc = new ArrayList<>();
    private static String sol = "";

	@SuppressWarnings("unchecked")
    public static void parse(String file){
    	try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

           	ligne = bufferedReader.readLine();

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int nbOcc(String mot){
        int res = 0;
        for(String s : sc){
            if(mot.equals(s))res++;
        }
        return res;
    }

    private static String min(){
        String res = sc.get(0);
        for(String s : sc){
            if(!res.equals(s)){
                if(nbOcc(s) < nbOcc(res))res = s;
            }
        }
        return res;
    }

    private static String sousChaine(int deb, int fin){
        String res = "";
        for(int i = deb; i < fin; i++){
            if(i < ligne.length())res = res + ligne.charAt(i);
        }
        return res;
    }

    public static void main(String[] args) {
        alpha = new char[26];
        for(int i = 0; i < 26; i++){
            alpha[i] = (char)(65 + i);
        }
		parse(args[0]);

        boolean fini = false;
        int pas = 1;
        String scmin = "";
        while(!fini){
            for(int i = 0; i < ligne.length(); i++){
                String s = sousChaine(i, i+pas);
                //System.out.println(s);
                sc.add(s);
            }
            scmin = min();
            //System.out.println(scmin);
            if(nbOcc(scmin) == 1)fini = true;
            pas++;
        }
        System.out.println(scmin);
	}  
}
