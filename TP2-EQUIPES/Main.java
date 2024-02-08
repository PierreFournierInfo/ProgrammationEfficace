public class Main{
    public static void main(String[] args) {
        int[][] tab = Parser.parseTab(args[0]);
        Parser.printTab(tab);
        System.out.println(tab[1][0]);
        System.out.println(tab[1][3]);
    }
}