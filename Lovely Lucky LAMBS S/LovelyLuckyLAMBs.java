import java.util.ArrayList;

public class LovelyLuckyLAMBs {
    
    public static int solution (int total_lambs) {
        ArrayList<Integer> generous = new ArrayList<Integer>();
        ArrayList<Integer> stingy = new ArrayList<Integer>();

        int remainder = total_lambs;
        int nextLamb = 1;
        while (remainder >= nextLamb) {
            generous.add(nextLamb);
            remainder -= nextLamb;
            nextLamb = 2 * nextLamb;
        }

        remainder = total_lambs;
        nextLamb = 1;
        while (remainder >= nextLamb) {
            if (stingy.size() < 1) {
                stingy.add(nextLamb);
                remainder -= nextLamb;
            } else {
                stingy.add(nextLamb);
                remainder -= nextLamb;
                nextLamb = stingy.get(stingy.size()-1) + stingy.get(stingy.size()-2);
            }
        }
        
        return stingy.size() - generous.size();
    }


    public static void main(String[] args) {

        System.out.println(solution(143));
        
    }
}
