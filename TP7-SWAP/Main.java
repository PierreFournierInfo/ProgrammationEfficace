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
    private static int nbrDisque;
    private static ArrayList<Disque> listDisque = new ArrayList<>();
    private static int dispo = 0;
    private static int achete = 0;

	@SuppressWarnings("unchecked")
    public static void parse(String file){
    	try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String ligne;
            Disque disk;
            String[] result;

            //Ligne 1 nbr disque
            ligne = bufferedReader.readLine();
            nbrDisque = Integer.parseInt(ligne);

            //Liste disque
            while((ligne = bufferedReader.readLine()) != null){
                result = ligne.split(" ");
                disk = new Disque(Integer.parseInt(result[0]),Integer.parseInt(result[1]));
                listDisque.add(disk);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Disque> separe(ArrayList<Disque> d){
        ArrayList<Disque> t = new ArrayList<>();
        for (Disque disque : d) {
            if(disque.avant < disque.apres){
                if(t.isEmpty()){
                    t.add(disque);
                }
                else{
                    for (Disque disque2 : t) {
                        if(disque.avant < disque2.avant){
                            t.add((t.indexOf(disque2)),disque);
                            break;
                        }
                    }
                }
                d.remove(disque);
            }
        }
        return t;
    }

    public static void phase1(){
        ArrayList<Disque> l = separe(listDisque);
        int n = l.size();//récupérer la taille de la liste triée de maxime
        for(int i=0; i<n; i++){
            if(i==0){
                achete+=l.get(i).avant;
                dispo=l.get(i).apres;
            }else{
                if(dispo<l.get(i).avant){
                    achete+=l.get(i).avant-dispo;
                    dispo+=l.get(i).apres;
                }else{
                    dispo+=(l.get(i).apres-l.get(i).avant);
                }
            }
        }
    }

    public static void printList(){
        for ( Disque d : listDisque ){
            System.out.println(d.avant + " " + d.apres);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        parse(args[0]);

        printList();

        System.out.println(achete);
	}  
}
