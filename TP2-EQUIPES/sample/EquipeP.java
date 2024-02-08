
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EquipeP {
   
    private static String FILENAME ;
    private static String [][] tabString;

    private static int [][] tabInt;
    private static int Arbitre;
    private static int m;
    private static int n;
    private static int p;
    private static int r;
    private static int[] distanceALarbitre;
    private static int[] distanceDepuisArbitre;

    public static void main(String[] args) {

        try {
            //**********************On lit l'adresse l'adresse du fichier passé en argument*********************************
            FILENAME=args[0];
            FileReader filereader = new FileReader(FILENAME);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            String strCurrentLine = bufferedreader.readLine();

            //*************On lit ligne par ligne et les stocke dans un tableau de String**********************
            String [] tab;
            tab=new String[Integer.parseInt(strCurrentLine)];
            int z=0;
            while ((strCurrentLine = bufferedreader.readLine()) != null) {
                tab[z]=strCurrentLine;
                z++;
            }
            bufferedreader.close();

            //******On sépare les mots de chaque ligne dans un tableau de tableau de String ****************/
            tabString=parse(tab);
            
            //******On converti le tableau de tableau de String en Tableau de Tableau de Int ***************/
            tabInt=convert(tabString);
            //afficheTab(tabInt);

            //*********On trouve la solution au problème et la renvoie dans un fichier de sortie *******************/

            //int nombreRecettes=solution();

            String nomFichier = "sortieGateau"+ FILENAME+".txt"; // Nom du fichier de sortie
            //System.out.println("\n Nombre de recettes possibles "+nombreRecettes);
            FileWriter fileWriter = new FileWriter(nomFichier, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            //printWriter.println("Nombre de recettes possibles : " + nombreRecettes); // Écrit le paramètre dans le fichier
            printWriter.close(); // Ferme le PrintWriter pour libérer les ressources
        
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static String [][] parse(String [] tab){
        String [][] res=new String [tab.length][];
        for(int h=0;h<tab.length;h++){
            
            String ligneCourante=tab[h];
            int nombreDeMots=0;
            int motCourant=0;
            int dernierePosition=0;
            //On compte
            for(int i=0;i<ligneCourante.length();i++){
                if (ligneCourante.charAt(i)==' '){
                    nombreDeMots++;
                    while(i+1<ligneCourante.length() && ligneCourante.charAt(i+1)==' '){ i++;}
                }
            }

            res [h]= new String [nombreDeMots+1];
            //On parse
            for(int i=0;i<ligneCourante.length();i++){
                if (ligneCourante.charAt(i)==' '){
                    res[h][motCourant]=ligneCourante.substring(dernierePosition,i);
                    while(i+1<ligneCourante.length() && ligneCourante.charAt(i+1)==' '){ i++;}
                    dernierePosition=i;
                    motCourant++;
                }
            }
            res[h][motCourant] = ligneCourante.substring(dernierePosition).trim();
            
        }
        return res;
    }

    public static int[][] convert(String [][] tableau){
        int [][] tab=new int[tableau.length][];
        for(int i=0;i<tableau.length;i++){
            tab[i]=new int [tableau[i].length];
            for(int j=0;j<tableau[i].length;j++){
                tab[i][j]=Integer.parseInt(tableau[i][j]);
            }
        }
        return tab;
    }

    public static void afficheTab(String [][]tab){
        for(int i=0;i<tab.length;i++){
            
            for(int j=0;j<tab[i].length;j++){
                System.out.print(tab [i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void afficheTab(int [][]tab){
        for(int i=0;i<tab.length;i++){
            
            for(int j=0;j<tab[i].length;j++){
                System.out.print(tab [i][j]+" ");
            }
            System.out.println();
        }
    }

    public static int[] Dijkstra(int a,int[][]tab){
        int[]tabFin=new int[3];
        return tabFin;
    }
    public static int[] DijkstraInv(int a,int[][]tab){
        int[]tabFin=new int[3];
        return tabFin;
    }

    public static void chemins(int [][] tabInt){
        int[][] couts= new int[m][];
        distanceDepuisArbitre=Dijkstra(m+1, tabInt);
        distanceALarbitre=DijkstraInv(m+1, tabInt);
        
    }

    



}