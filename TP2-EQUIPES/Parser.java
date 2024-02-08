import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser {

    public static void printTab ( int[][] tab ){
        for (int i = 0; i < tab.length ; i ++ ) {
                for ( int j = 0; j < tab.length ; j ++ ){
                    System.out.print(tab[i][j] + " ");
                }
                System.out.println();
            }
    }

    public static int[][] parseTab(String file){
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

            tab = new int[m+1][m+1];

            for (int i = 0; i < m+1 ; i ++ ) {
                for ( int j = 0; j < m+1 ; j ++ ){
                    tab[i][j] = -1;
                }
            }

            line = bufferedReader.readLine();
            while ( line != null ){
                result = line.split(" ");
                x = Integer.parseInt(result[0]);
                y = Integer.parseInt(result[1]);
                l = Integer.parseInt(result[2]);

                tab[x-1][y-1] = l;

                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            tab = new int[1][1];
            e.printStackTrace();
        }

        return tab;
    }
}