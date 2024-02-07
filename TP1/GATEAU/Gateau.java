import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Gateau {
   
    private static final String FILENAME = "1.in";
    private static String [][] tableau;
    public static void main(String[] args) {
        BufferedReader bufferedreader = null;
        FileReader filereader = null;
        String [] tab;

        //System.out.println("Hello worlds");
        try {
            
            filereader = new FileReader(FILENAME);
            bufferedreader = new BufferedReader(filereader);
            String strCurrentLine = bufferedreader.readLine();
            tab=new String[Integer.parseInt(strCurrentLine)];
            int z=0;
            while ((strCurrentLine = bufferedreader.readLine()) != null) {
                tab[z]=strCurrentLine;
                System.out.println(strCurrentLine);
                z++;
            }
            System.out.println();
            System.out.println("tableau finale: \n");

            tableau=parse(tab);
            afficheTab(tableau);
            System.out.println("nombre de lignes " +tableau.length);
            System.out.println("nombre de mots a la ligne 5:  " +tableau[5].length);

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

    public static void afficheTab(String [][]tab){
        for(int i=0;i<tab.length;i++){
            
            for(int j=0;j<tab[i].length;j++){
                System.out.print(tab [i][j]+" ");
            }
            System.out.println();
        }
    }



    public class Paire{
        private int quantite;
        private int stock;
        public Paire( int q, int s){
            quantite=q;
            stock=s;
        }
    }

    public class Recette{
        
    }

}