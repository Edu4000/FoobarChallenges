public class Staircases {

    public static int possibleSums (int num, int sum) {
        int res = 0;
        int previous;
        int limit = minimumBiggestInt(num + sum);

        if (sum == num && num == 2) {
            return 0;
        } else if (num <= 2) {
            return 1;
        }

        while (num >= limit) {
            //System.out.println("num: " + num);
            //System.out.println("sum: " + sum);
            previous = num;

            if (previous > sum && num != sum) {

                //System.out.println("First case");
                //System.out.print(num + " + " + sum + "  ");
                res += possibleSums(sum, 0);
                //System.out.println("");
                //System.out.println(res);

            } else if (sum == num && num == 2) {

                //System.out.println("Nothing");

            } else {

                //System.out.println("Second case");
                //System.out.print(num + " + " + sum + "  ");
                res += possibleSums(num - 1, sum - (num - 1));
                //System.out.println("");
                //System.out.println(res);

            }

            sum++;
            num--;
        }
        return res;
    }

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
        System.out.println(solution(200));

        //System.out.println(minimumBiggestInt(20));
    }
}
