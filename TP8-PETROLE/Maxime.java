import java.util.ArrayList;

public class Maxime {
    public static int relie(int x1, int y1, int x2, int y2, ArrayList<Gisement> listGisements){
        if(x1 - x2 ==0)
            return 0;
        
        double a = ((double)y1-(double)y2)/((double)x1 - (double)x2);
        double b = (double)y1 - (double)a * (double)x1;
        int petrole = 0;
        for (Gisement ligne : listGisements) {
            double x = ((double)ligne.y-(double)b)/(double)a;
            if((ligne.x1 <= x && ligne.x2 >= x)||(ligne.x1 >= x && ligne.x2 <= x))
                petrole += ligne.qt;
        }
        return petrole;
    }
}
