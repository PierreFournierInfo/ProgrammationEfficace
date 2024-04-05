import java.util.ArrayList;

public class Maxime {
    public static int relie(int x1, int y1, int x2, int y2, ArrayList<Gisement> listGisements){
        if(x1 - x2 == 0 || y1 - y2 == 0)
            return 0;
        double vectorx = x2 - x1;
        double vectory = y2 - y1;
        int petrole = 0;
        for (Gisement gisement : listGisements) {
            double coef = (gisement.y - y1)/vectory;
            double x = vectorx*coef + x1;
            if((x<=gisement.x1&&x>=gisement.x2)||(x>=gisement.x1&&x<=gisement.x2))
                petrole += gisement.qt;
        }
        return petrole;
    }
}
