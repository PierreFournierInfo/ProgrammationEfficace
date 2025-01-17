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
public class EquipeP {
   
    private static String FILENAME ;
    private static String [][] tabString;

    private static TasBin tasBin;

    private static int [][] tabInt;
    private static int [][] tabDistances;
    private static int Arbitre;
    private static int m;
    private static int n;
    private static int p;
    private static int r;
    private static int[] distanceALarbitre;
    private static int[] distanceDepuisArbitre;
    private static int[] tabDistAllerRetour;
    private static int[] tabTri;
    public static ArrayList<Integer>[] equipes;
    public static int[]coutEquipes;
    private static int coutMinimal;
    

    //*************Le main récupère le fichier.in indiqué en arg[0], le converti en tableau de tableau de int**********************
    //*************puis appelle  coutMinimal=solution(int[][] tab) qui va renvoyer le cout minimal (donc la réponse finale)********
    //*************Un fichier.out est créé contenant la phrase "le cout minimale est: "+coutMinimal  ******************************
    public static void main(String[] args) {

        try {
            //**********************On lit l'adresse l'adresse du fichier passé en argument*********************************
            FILENAME=args[0];
            tabDistances=initTabDistances(FILENAME);
            //System.out.println("Le Graphe d'origine");
            //afficheTab(tabDistances);



            FileReader filereader = new FileReader(FILENAME);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            String strCurrentLine = bufferedreader.readLine();

            //****************Apreès avoir lu la premiere ligne, on initialise les valeurs de n,p,l et m ***************************
            n=Integer.parseInt(selectWord(strCurrentLine,0));
            m=Integer.parseInt(selectWord(strCurrentLine,1));
            p=Integer.parseInt(selectWord(strCurrentLine,2));
            r=Integer.parseInt(selectWord(strCurrentLine,3));

            Arbitre=m+1;


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
            

            
            //Initialisation des distance depuis l'arbitre et jusqu a l arbitre

            initDistancesArbitre();

            /* 
            System.out.println("\nDijkstra de distanceALarbitre :");
            afficheDijkstra(distanceALarbitre);
            System.out.println("\nDijkstra de distanceDepuisArbitre :");
            afficheDijkstra(distanceDepuisArbitre);

            System.out.println("\nDijkstra Aller Retour :");
            afficheDijkstra(tabDistAllerRetour);
            */
            
            InitTri();
            //System.out.println("\nDijkstra tabTri :");
            //afficheDijkstra(tabTri);
           

            //*********On trouve la solution au problème et la renvoie dans un fichier de sortie *******************/
            InitEquipes();
            repartition();
            coutMinimal=solution();

            afficheEquipes();

            String nomFichier =  FILENAME+".out.txt"; // Nom du fichier de sortie
            //System.out.println("\n Nombre de recettes possibles "+nombreRecettes);
            FileWriter fileWriter = new FileWriter(nomFichier, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("Cout minimal : " + coutMinimal); // Écrit le paramètre dans le fichier
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





    //************nouvTableau() doit convertir tabInt en un tableau sous forme de matrice d'adjacence    **********************
    public static int[][] initTabDistances(String file){
        int n;
        int m;
        int p;
        int r;

        int x;
        int y;
        int l;

        int[][] tab;

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            String[] result = line.split(" ");
            n = Integer.parseInt(result[0]);
            m = Integer.parseInt(result[1]);
            p = Integer.parseInt(result[2]);
            r = Integer.parseInt(result[3]);

            //System.out.println(n + " " + m + " " + p + " " + r + " ");

            tab = new int[n][n];

            for (int i = 0; i < n ; i ++ ) {
                for ( int j = 0; j < n ; j ++ ){
                    tab[i][j] = -1;
                }
            }

            line = bufferedReader.readLine();
            while ( line != null ){
                result = line.split(" ");
                x = Integer.parseInt(result[0]);
                y = Integer.parseInt(result[1]);
                l = Integer.parseInt(result[2]);

                //System.out.println(x + " " + y + " " + l );

                tab[x-1][y-1] = l;

                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            tab = new int[1][1];
            e.printStackTrace();
        }

        return tab;
    
    }

    public static void initDistancesArbitre(){
        
       distanceDepuisArbitre=Dijkstra(Arbitre,tabDistances);
       distanceALarbitre=DijkstraInv(Arbitre,tabDistances);

       tabDistAllerRetour = new int[distanceALarbitre.length];

       for (int i = 0; i<tabDistAllerRetour.length; i++) {
          tabDistAllerRetour[i] = distanceALarbitre[i] + distanceDepuisArbitre[i];
       }
    }

    //*************DIJKSTRA doit renvoyer un tableau de int comprenant toutes les distances du sommet numéro a au sommet d'indice i+1  **********************
    public static int[] Dijkstra(int a,int[][]tab){
        int l = tab.length;

        //initialisation de tabFin
        int[]tabFin=new int[l];
        for(int i = 0; i < l; i++)tabFin[i] = 10001;
        tabFin[a-1] = 0;

        //initialisation du tas binaire
        TasBin tas = new TasBin(l, tabFin);  
        tas.insert(l-1);    
        for(int i = 0; i < l-1; i++){
            tas.insert(i);
        }

        while(!tas.empty()){
            int u = tas.remove();
            for(int i = 0; i < l; i++){
                // Mise a jour des distances des voisins de u
                if(tab[u][i] > -1 && tabFin[i] > tab[u][i] + tabFin[u]){
                    tabFin[i] = tab[u][i] + tabFin[u];
                    tas.decreasekey(i, tabFin[i]);
                }
            }
        }
        return tabFin;
    }

    public static void afficheDijkstra(int[] d){
        System.out.print("Sommets  : ");
        for(int i = 0; i < d.length; i++){
            System.out.print(i+1 + " ");
        }
        System.out.println();
        System.out.print("Distance : ");
        for(int i = 0; i < d.length; i++){
            System.out.print(d[i] + " ");
        }
        System.out.println();
    }

    public static int[] DijkstraInv(int a,int[][]tab){
        int l = tab.length;

        //initialisation de tabFin
        int[]tabFin=new int[l];
        for(int i = 0; i < l; i++)tabFin[i] = 10001;
        tabFin[a-1] = 0;

        //initialisation du tas binaire
        TasBin tas = new TasBin(l, tabFin);  
        tas.insert(l-1);    
        for(int i = 0; i < l-1; i++){
            tas.insert(i);
        }

        while(!tas.empty()){
            int u = tas.remove();
            for(int i = 0; i < l; i++){
                // Mise a jour des distances des voisins de u
                if(tab[i][u] > -1 && tabFin[i] > tab[i][u] + tabFin[u]){
                    tabFin[i] = tab[i][u] + tabFin[u];
                    tas.decreasekey(i, tabFin[i]);
                }
            }
        }
        return tabFin;
    }






    public static void chemins(int [][] tabInt){
        int[][] couts= new int[m][];
        distanceDepuisArbitre=Dijkstra(m+1, tabInt);
        distanceALarbitre=DijkstraInv(m+1, tabInt);
        
    }
    public static int coutAjout(int numSommet, int indiceEquipe){
        int cout=0;
        int taille=equipes[indiceEquipe].size();
        for(int i=0;i<taille;i++){
            int poids=tabDistAllerRetour[i];
            cout+=poids*taille;
        }
        cout+=tabDistAllerRetour[numSommet]*taille;
        return cout;
    }
    public static void trier(int[] tableau) {
        for (int i = 0; i < tableau.length - 1; i++) {
            // Trouver l'index du minimum dans le reste du tableau
            int indexMin = i;
            for (int j = i + 1; j < tableau.length; j++) {
                if (tableau[j] < tableau[indexMin]) {
                    indexMin = j;
                }
            }
            
            // Échanger l'élément actuel avec l'élément le plus petit trouvé
            int temp = tableau[i];
            tableau[i] = tableau[indexMin];
            tableau[indexMin] = temp;
        }
    }

    public static int[][] copie(int[][]tab){
        int taille =tab.length;
        int [][]copie=new int[taille][];
        for(int i=0;i<taille;i++){
            copie[i]=new int[tab[i].length];
            for(int j=0;j<tab[j].length;j++){
                copie[i][j]=tab[i][j];
            }
        }
        return copie;
    }
    public static int[] copie(int[]tab){
        int taille =tab.length;
        int []copie=new int[taille];
        for(int i=0;i<taille;i++){
            copie[i]=tab[i];
        }
        return copie;
    }
    public static void InitTri(){
        int taille=tabDistAllerRetour.length;
        tabTri=copie(tabDistAllerRetour);
        trier(tabTri);
    }

    @SuppressWarnings("unchecked")
    public static void InitEquipes(){
        equipes=new ArrayList[p];
        for(int i=0;i<p ;i++){
            equipes[i]=new ArrayList<Integer>();
        }
    }
   

    public static void repartition1(){
        int repere=0;
        for(int i=0;i<m ;i++){
            equipes[repere].add(i+1);
            repere=(repere+1)%p;
        }
    }

    //Tentative de comprehension du jeudi 29 février:
    /* 
    public static int somme(int indice){
        int compte=0;
        for(int i=0;i<equipes[indice].size();i++){
            compte+=tabDistAllerRetour[i];
        }
        return compte;
    }
    public static void repartition(){
        int[][] M=new int[p][n];
        int[]S=new int[p];
        for(int i=2;i<p;i++){
            for(int j=i;j<n;j++){
                M[i][j]=min(M[i-1][j-k]+(k-1)(somme(j)-somme(j-k)));
            }
        }
    }
    */


    public static void afficheEquipes(){
        int taille=equipes.length;
        for(int i=0;i<taille;i++){
            System.out.println("equipe "+i);
            for(int j=0;j<equipes[i].size();j++){
                System.out.print(equipes[i].get(j)+" ");
            }
            System.out.println();
        }
        
    }

    public static int coutTotal(){
        //System.out.println("Calcul coutTotal");
        int taille=equipes.length;
        //System.out.println("nombre d'equipes: "+taille);
        int coutTot=0;
        for(int i=0;i<taille;i++){
            int tailleJ=equipes[i].size();
            //System.out.println("taille de l equipe "+i+" "+tailleJ);
            for(int j=0;j<tailleJ;j++){
                //System.out.println("tabAllerRetour[equipe["+i+"].get("+j+")]=" + tabDistAllerRetour[equipes[i].get(j)]);
                //System.out.println("equipe["+i+"].get("+j+")"+equipes[i].get(j));
                coutTot+=tabDistAllerRetour[equipes[i].get(j)-1]*(tailleJ-1); 
                //System.out.println("coutTotal:" +coutTot);
                                                                                                                       
            }
        }
        //System.out.println("CoutTotal final: "+ coutTot);
        return coutTot;
    }

    public static int solution(){
        return coutTotal();
    }

    /*
     * retourne l'equipe optimal ajouté au joueur
     */
    public LinkedList<Integer>[] ajoutJ(LinkedList<Integer>[] equipe, int joueur){
        equipe[0].addLast(joueur);
        for(int i = 0; i< equipe.length -1; i++){
            LinkedList<Integer> nouvequipe1 = (LinkedList) equipe[i].clone();
            LinkedList<Integer> nouvequipe2 = (LinkedList) equipe[i+1].clone();
            nouvequipe1.addLast(nouvequipe2.removeFirst());
            if(value(nouvequipe1) + value(nouvequipe2) <= value(equipe[i]) + value(equipe[i+1])){
                equipe[i] = nouvequipe1;
                equipe[i+1] = nouvequipe2;
            }
            else{
                return equipe;
            }
        }
        return equipe;

    }

    /*
     * retourne l'equipe optimal ajouté aux joueur
     */
    public LinkedList<Integer>[] ajoutAllJ(LinkedList<Integer>[] equipe, int[] joueur){
        for (int i : joueur) {
            ajoutJ(equipe, i);
        }
        return equipe;
    }

    /*
     * retourne l'equipe optimal
     * joueur est la list des joueur a ajouté en ordre de coût decroissant
     */
    


   

    /*
     * retourne la valeur des echanges d'un groupe de joueur
     */
    public static int value(LinkedList<Integer> list){
        int val = 0;
        int l = list.size() - 1;
        for (int v : list) {
            val += v*l;
        }
        return val;

    }



}
