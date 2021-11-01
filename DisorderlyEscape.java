import java.lang.Math;
import java.math.BigInteger;

public class DisorderlyEscape {

    public static BigInteger rowCombinations(int w, int s) {
        // Give references
        // A way to get the total number of combinations given w and s is the following
        int num = w + s - 1;

        BigInteger res = factorial(new BigInteger(String.valueOf(num)));

        res = res.divide(factorial(new BigInteger(String.valueOf(w))));
        res = res.divide(factorial(new BigInteger(String.valueOf(s - 1))));

        return res;
    }

    // Factorial method for BigInteger Type
    public static BigInteger factorial(BigInteger a) {
        if (a.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        } else {
            return a.multiply(factorial(a.subtract(BigInteger.ONE)));
        }
    }

    public static void printRow(int [] row) {
        for (int i : row) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }

    /*
    public static int [][] possibleRows (int w, int s) {
        int [][] res = new int [rowCombinations(w,s).intValue()][w];
        int index = 0;
        for (int i = 0; i < s; i++) {
            
        }
    }*/

    public static String solution(int w, int h, int s) {

        int [] fisrtR = new int [w];
        for (int i : fisrtR) {
            i = 0;
        }

        // With a number of possible states s and a width w, the maximum number
        // of possible rows is the maximum posible number of w digits and base
        // s plus 1, counting for the 0
        int number = 1;
        for (int i = 0; i < w; i++) {
            System.out.println(number);
            number += (s-1) * s ^ i;
        }

        System.out.println(number);

        printRow(fisrtR);
        return "0";
    }



    public static void main(String[] args) {

        System.out.println("Hello World!");
        solution(2,2,2);
        System.out.println(Math.pow(2, 0));

        BigInteger aux = rowCombinations(2, 4);
        System.out.println(aux.toString());
        
    }
    
}
