public class Staircases {

    public static int possibleSums (int num, int sum) {
        int res = 0;    // answer initialized to 0
        int previous;   // auxiliar
        int limit = minimumBiggestInt(num + sum);   // measures the number of possible 

        if (sum == num && num == 2) {
            return 0;
        } else if (num <= 2) {
            return 1;
        }

        while (num >= limit) {
            previous = num;

            if (previous > sum && num != sum) {

                res += possibleSums(sum, 0);

            } else if (sum == num && num == 2) {

                //System.out.println("Nothing");

            } else {

                res += possibleSums(num - 1, sum - (num - 1));

            }
            sum++;
            num--;
        }
        return res;
    }

    // Auxiliar Function 
    public static int minimumBiggestInt(int n) {
        int res = 1;
        while (n > 0) {
            n -= res;
            res++;
        }
        return res - 1;
    }

    public static int solution (int n) {
        return possibleSums(n, 0) - 1;
    }

    public static void main(String[] args) {
        // Most basic case
        System.out.print("Example N = 3. Answer: ");
        System.out.println(solution(3));
        // Case of problem description
        System.out.print("Example N = 10. Answer: ");
        System.out.println(solution(10));
        // Most complex case
        System.out.print("Example N = 200. Answer: ");
        System.out.println(solution(200));
    }
}
