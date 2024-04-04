public class Maxime {
    public int relie(int x1, int y1, int x2, int y2, Gisement[] l){
        double a = (y1-y2)/(x1 - x2);
        double b = y1 - a * x1;
        int petrole = 0;
        for (Gisement ligne : l) {
            double x = (ligne.y-b)/a;
            if((ligne.x1 <= x && ligne.x2 >= x)||(ligne.x1 >= x && ligne.x2 <= x))
                petrole += ligne.qt;
        }
        return petrole;
    }
}
