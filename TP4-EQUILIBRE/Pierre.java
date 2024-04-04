package TP4-EQUILIBRE;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
public class Pierre {
   
    private static int n;
    private static int k;
    private static int m;
    private static int resultat;
    private static ArrayList<Integer> mosaique;
    private static int[]proportion;//f
    private static int[]quantite;//s



    private static String FILENAME ;
    private static String [][] tabString;

   
    

    //*************Le main récupère le fichier.in indiqué en arg[0], le converti en tableau de tableau de int**********************
    //*************puis appelle  coutMinimal=solution(int[][] tab) qui va renvoyer le cout minimal (donc la réponse finale)********
    //*************Un fichier.out est créé contenant la phrase "le cout minimale est: "+coutMinimal  ******************************
    public static void main(String[] args) {

        try {
            //**********************On lit l'adresse l'adresse du fichier passé en argument*********************************
            FILENAME=args[0];
            //System.out.println("Le Graphe d'origine");
            //afficheTab(tabDistances);



            FileReader filereader = new FileReader(FILENAME);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            String strCurrentLine = bufferedreader.readLine();


      


            /* 

            //*************On lit ligne par ligne et les stocke dans un tableau de String**********************
            String [] tab;

            tab=new String[r];
            int z=0;
            while ((strCurrentLine = bufferedreader.readLine()) != null) {
                tab[z]=strCurrentLine;
                z++;
            }
            bufferedreader.close();

            */

            //******On sépare les mots de chaque ligne dans un tableau de tableau de String ****************/
            //tabString=parse(tab);
            
            //******On converti le tableau de tableau de String en Tableau de Tableau de Int ***************/
            
            //tabInt=convert(tabString);
            

            
            

            //*********On trouve la solution au problème et la renvoie dans un fichier de sortie *******************/
           

            String nomFichier =  FILENAME+".out.txt"; // Nom du fichier de sortie
            //System.out.println("\n Nombre de recettes possibles "+nombreRecettes);
            FileWriter fileWriter = new FileWriter(nomFichier, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("Nombre de carreaux encore posables : " + resultat ); // Écrit le paramètre dans le fichier
            printWriter.close(); // Ferme le PrintWriter pour libérer les ressources




            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    //*************La fonction parse() sépare les mots et converti donc un String[] en Strin[][]**********************

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
                if (ligneCourante.charAt(i)==' '|| i == ligneCourante.length() - 1){
                    res[h][motCourant]=ligneCourante.substring(dernierePosition, i == ligneCourante.length() - 1 ? i + 1 : i).trim();
                    while(i+1<ligneCourante.length() && ligneCourante.charAt(i+1)==' '){ i++;}
                    dernierePosition=i+1;
                    motCourant++;
                }
            }
            if (dernierePosition < ligneCourante.length()) {
                res[h][motCourant] = ligneCourante.substring(dernierePosition).trim();
            }
            
            
        }
        return res;
    }


    //*************La fonction selectWord sélectionne le ie mot d'une chaine de caractere (utili pour connaitre la taille du tableau indiquée ligne 0)**********************
    public static String selectWord(String s, int x) {
        // Divise la chaîne de caractères en mots, en utilisant l'espace comme séparateur.
        String[] words = s.split("\\s+"); // "\\s+" est une expression régulière qui correspond à un ou plusieurs espaces.
    
        // Vérifie si l'index x est dans la plage du tableau de mots.
        if (x >= 0 && x < words.length) {
            return words[x]; // Retourne le mot à l'index spécifié.
        } else {
            return ""; // Retourne une chaîne vide si l'index est hors de la plage.
        }
    }

    //*************la fonction convert converti un String[][] en int[][]**********************
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


    //si le nombre de carreaux du modèle i
    //utilisés à un moment donné, et n = s1 + ... + sm. Alors la frise
    //est équilibrée si, à tout moment et pour chaque i, si est
    //strictement compris entre n·fi − 1 et n·fi + 1.

    public static boolean estEquilibre(int[]tab){
        for(int i=0;i<tab.length;i++){
            if(quantite[i]<=n*proportion[i]-1 || quantite[i]>=n*proportion[i]+1){
                return false;
            }
        }
        return true;
    }

    public static int[] copie(int[]tab){
        int taille =tab.length;
        int []copie=new int[taille];
        for(int i=0;i<taille;i++){
            copie[i]=tab[i];
        }
        return copie;
    }





}