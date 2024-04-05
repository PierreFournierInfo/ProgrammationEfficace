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
    private static int nbrGisements;
    private static ArrayList<Gisement> listGisements = new ArrayList<>();

	@SuppressWarnings("unchecked")
    public static void parse(String file){
    	try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String ligne;
            String[] result;
            Gisement gis;

            //Ligne 1 nbr gisements
            ligne = bufferedReader.readLine();
            nbrGisements = Integer.parseInt(ligne);

            //Liste gisements
            while((ligne = bufferedReader.readLine()) != null){
                result = ligne.split(" ");
                gis = new Gisement(Integer.parseInt(result[0]),Integer.parseInt(result[1]),Integer.parseInt(result[2]));
                listGisements.add(gis);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int solution(){
        if(listGisements.size() == 1)
            return listGisements.get(0).qt;
        ArrayList<Gisement>[] filonList = new ArrayList[1000001];
        for (Gisement gisement : listGisements) {
            if(filonList[gisement.y] == null)
                filonList[gisement.y] = new ArrayList<Gisement>();
            if(gisement.qt !=0)
                filonList[gisement.y].add(gisement);
        }
        ArrayList<ArrayList<Gisement>> strateList= new ArrayList<>();
        for(int i = 0; i<1000001;i++){
            if(filonList[i] != null)
                strateList.add(filonList[i]);
        }
        if(strateList.size() == 1){
            int r =0;
            for (Gisement gisement : strateList.get(0)) {
                if(r < gisement.qt)
                    r = gisement.qt;
            }
            return r;
        }
        int f = 0;
        for (int i = 0; i< strateList.size();i++) {
            for(int j = i+1 ; j< strateList.size(); j++){
                for(int t = 0; t<strateList.get(i).size();t++){
                    for(int p = 0; p<strateList.get(j).size();p++){
                        int a = (Maxime.relie(strateList.get(i).get(t).x1,strateList.get(i).get(t).y,strateList.get(j).get(p).x1,strateList.get(j).get(p).y,listGisements));
                        if(a>f)
                            f = a;
                        int b = (Maxime.relie(strateList.get(i).get(t).x1,strateList.get(i).get(t).y,strateList.get(j).get(p).x2,strateList.get(j).get(p).y,listGisements));
                        if(b>f)
                            f = b;
                        int c = (Maxime.relie(strateList.get(i).get(t).x2,strateList.get(i).get(t).y,strateList.get(j).get(p).x2,strateList.get(j).get(p).y,listGisements));
                        if(c>f)
                            f = c;
                        int d = (Maxime.relie(strateList.get(i).get(t).x2,strateList.get(i).get(t).y,strateList.get(j).get(p).x1,strateList.get(j).get(p).y,listGisements));
                        if(d>f)
                            f = d;
                    }
                }
            }
        }
        
        return f;
    }

    public static void printList(){
        for ( Gisement g : listGisements ){
            System.out.println("x1: " + g.x1 + " x2: " + g.x2 + " y: " + g.y);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        parse(args[0]);

        //printList();

        System.out.println(solution());
	}  
}
