import java.util.ArrayList;

public class MinionLabor{

    public static void printArray (int [] a) {
        for (int num : a) {
            System.out.print(num);
            System.out.print(", ");
        }
    }

    public static int [] solution (int [] data, int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<Integer> history = new ArrayList<Integer>();

        for (int id : data) {
            if (!history.contains(id)) {
                int count = 0;
                for (int i = 0; i < data.length; i++) {
                    if (data[i] == id) {
                        count++;
                    }
                }
                if (count <= n) {
                    result.add(id);
                } else {
                    history.add(id);
                }
            }
        }

        int res [] = new int[result.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int [] list = {5, 10, 15, 10, 7};

        printArray(solution(list, 1));
    }

}