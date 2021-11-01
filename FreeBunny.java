public class FreeBunny {

    public static void printArr (int [][] arr) {
        for (int[] i : arr) {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println("");
        }
    }

    public static int factorial (int n) {
        int res = 1;
        while (n > 1) {
            res = res * n;
            n--;
        }
        return res;
    }

    public static int nCr (int n, int r) {
        int res = factorial(n);
        res = res / (factorial(r) * factorial(n-r));
        return res;
    }

    public static void addNumber (int [][] arr, int col, int num) {
        int j = 0;
        while (arr[col][j] != 0) {
            j++;
        }
        arr[col][j] = num + 1;
    }

    public static int[][] solution (int num_buns, int num_required) {

        int num_var = num_buns + 1 - num_required;
        int num_keys = num_buns * nCr(num_buns - 1, num_required - 1);
        int [][] selection = new int [num_var][num_keys / num_var];

        for (int i = 0; i < selection.length; i++) {
            for (int j = 0; j < selection[i].length; j++) {
                selection[i][j] = j;
            }
        }

        int [][] res = new int [num_buns][num_keys / num_buns];

        int [] indexes = new int [num_var];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }

        int counter = 0;
        int point = num_var - 1;

        while (counter < num_keys) {
            // TODO add number in rows
            for (int j : indexes) {

                addNumber(res, j, selection[counter % num_var][counter / num_var]);

                counter++;
            }

            point = num_var - 1;
            while (point >= 0) {
                if (indexes[point] != num_buns - (num_var - point)) {
                    indexes[point]++;
                    point = -1;
                } else {
                    if (point == 0) {
                        break;
                    }
                    int new_position = indexes[point - 1] + 2;
                    if (new_position > num_buns - (num_var - point)) {
                        new_position = num_buns - (num_var - point);
                    }
                    int diff = indexes[point] - new_position;
                    for (int i = point; i < indexes.length; i++) {
                        indexes[i] -= diff;
                    }

                    point--;
                }
            }
        }

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                res[i][j]--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        printArr(solution(7, 3));
    }
    
}
