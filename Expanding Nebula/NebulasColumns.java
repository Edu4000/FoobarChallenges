import java.util.Stack;

public class NebulasColumns {

    public static void printGas (boolean [][] gas) {
        for (boolean [] i : gas) {
            for (boolean j : i) {
                if (j) {
                    System.out.print(0);
                } else {
                    System.out.print(".");
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    // Method to count the amount of true values of a 2x2 grid excluding the bottom left
    public static int countGas (boolean [][] p, int i, int j) {
        int res = 0;
        if (p[i][j]){
            res++;
        }
        if (p[i + 1][j]){
            res++;
        }
        if (p[i][j + 1]) {
            res++;
        }
        return res;
    }

    // Method to stores the low row of the first anilized row
    // With the intention of detecting rows that repeat the same pattern before moving forward
    public static String lowRow (boolean [][] p) {
        String row = "";
        for (boolean a : p[1]){
            if (a) {
                row += "1";
            } else {
                row += "0";
            }
        }
        return row;
    }

    public static int start (boolean [][] c, boolean [][] p, int j, Stack <String> rows, Stack <Integer> results) {
        int res = 0;
        String row;
        // When reaching the last cell of the column the next column is called
        if (j == c[0].length) {
            // After the first row in c is analized we get the results for the first two rows of p
            // String row stores the bottom row of this result, because this directly affects the following rows
            row = lowRow(p);

            // If the row was already inspected the result is already stored
            if (rows.contains(row)) {
                // Getting index of row in stack to retrieve the result previously obtained
                int index = rows.indexOf(row);
                res = results.get(index);

            // If it has not been inspected yet, the other functions are called
            } else {
                res = nextRow(c, p, 1, 0);
                p[2][0] = true;
                res += nextRow(c, p, 1, 0);
                p[2][0] = false;

                // Storing the results to avoid repeating unnecessary process
                rows.add(row);
                results.add(res);
            }
            
            return res;
        }

        if (c[0][j]) {
            // In a 2x2 grid the left column are both true
            if (p[0][j] && p[1][j]) {
                return 0;  // The process is stopped and return 0

            // In a 2x2 grid the left column has one true value
            } else if (p[0][j] || p[1][j]) {
                p[0][j+1] = false;      p[1][j+1] = false;  // The other 2 cells are set to false
                res += start(c, p, j+1, rows, results);

            // In a 2x2 grid the left column has no true values
            } else {
                p[0][j+1] = false;      p[1][j+1] = true;  // One of the 2 cells becomes true
                res += start(c, p, j+1, rows, results);

                p[0][j+1] = true;       p[1][j+1] = false;  // One of the 2 cells becomes true
                res += start(c, p, j+1, rows, results);
            }
        } else {
            // In a 2x2 grid the left column are both true
            if (p[0][j] && p[1][j]) {
                p[0][j + 1] = true;     p[1][j+1] = false;  // One of the 2 cells becomes true (3 True)
                res += start(c, p, j+1, rows, results);
                
                p[0][j + 1] = false;     p[1][j+1] = true;  // One of the 2 cells becomes true (3 Trues)
                res += start(c, p, j+1, rows, results);

                p[0][j + 1] = true;     p[1][j+1] = true;    // The other two are set to true (4 Trues)
                res += start(c, p, j+1, rows, results);

                p[0][j + 1] = false;     p[1][j+1] = false;  // The other two are set to false (2 Trues)
                res += start(c, p, j+1, rows, results);

            // In a 2x2 grid the left column has one true value
            } else if (p[0][j] || p[1][j]) {
                p[0][j + 1] = true;     p[1][j+1] = false;  // One of the 2 cells becomes true (2 True)
                res += start(c, p, j+1, rows, results);
                
                p[0][j + 1] = false;     p[1][j+1] = true;  // One of the 2 cells becomes true (2 True)
                res += start(c, p, j+1, rows, results);

                p[0][j + 1] = true;     p[1][j+1] = true;   // The other two are set to true (3 Trues)
                res += start(c, p, j+1, rows, results);

            // In a 2x2 grid the left column has no true values
            } else {
                p[0][j + 1] = true;     p[1][j+1] = true;    // The other two are set to true (2 Trues)
                res += start(c, p, j+1, rows, results);

                p[0][j + 1] = false;     p[1][j+1] = false;  // The other two are set to false (0 trues)
                res += start(c, p, j+1, rows, results);
            }
        }
        
        return res;
    }

    public static int nextRow (boolean [][] c, boolean [][] p, int i, int j) {
        int res = 0;
        // When reaching the last row, the last method is called
        if (i == c.length - 1) {
            // Special method when reaching last row
            res = lastRow(c, p, j);
            return res;

        // When reaching the last cell of the row the next row is called
        } else if (j == c[0].length) {
            res = nextRow(c, p, i + 1, 0);
            p[i + 2][0] = true;
            res += nextRow(c, p, i + 1, 0);
            p[i + 2][0] = false;
            return res;
        }

        int gas = countGas(p, i, j);
        if (c[i][j]) {
            // There is no possibility to get true in a time step. Stop and return 0
            if (gas > 1) {
                return 0;

            // There already exist one True in the 2x2 grid
            } else if (gas == 1) {
                p[i + 1][j + 1] = false;
                res += nextRow(c, p, i, j+1);

            // There are no Trues in the 2x2 grid
            } else {
                p[i + 1][j + 1] = true;
                res += nextRow(c, p, i, j+1);
                p[i + 1][j + 1] = false;
            }

        } else {
            // There exist 2 True in a given place
            if (gas > 1) {
                p[i + 1][j + 1] = true;         // Set true the last cell (3 True)
                res += nextRow(c, p, i, j+1);

                p[i + 1][j + 1] = false;        // Set false the last cell (2 True)
                res += nextRow(c, p, i, j+1);

            } else if (gas == 1) {
                p[i + 1][j + 1] = true;         // The last cell needs to be set to true (2 True)
                res += nextRow(c, p, i, j+1);
                p[i + 1][j + 1] = false;

            } else {
                p[i + 1][j + 1] = false;        // Only option is to have all false (0 True)
                res += nextRow(c, p, i, j+1);
            }
        }

        return res;
    }

    public static int lastRow (boolean [][] c, boolean [][] p, int j) {
        int res = 0;
        // When reaching the end the result is assured to be a possible previous state for c
        if (j == c[0].length) {
            return 1;
        }

        int gas = countGas(p, (c.length-1), j);
        if (c[c.length-1][j]) {
            // There is no possibility to get true in a time step. Stop and return 0
            if (gas > 1) {
                return 0;

            // There already exist one True in the 2x2 grid
            } else if (gas == 1) {
                p[c.length][j + 1] = false;
                res += lastRow(c, p, j+1);

            // There are no Trues in the 2x2 grid
            } else {
                p[c.length][j + 1] = true;
                res += lastRow(c, p, j+1);
                p[c.length][j + 1] = false;
            }

        } else {
            // There exist 2 True in a given place
            if (gas > 1) {
                p[c.length][j + 1] = true;      // Set true the last cell (3 True)
                res += lastRow(c, p, j+1);

                p[c.length][j + 1] = false;     // Set false the last cell (2 True)
                res += lastRow(c, p, j+1);

            } else if (gas == 1) {
                p[c.length][j + 1] = true;      // The last cell needs to be set to true (2 True)
                res += lastRow(c, p, j+1);
                p[c.length][j + 1] = false;

            } else {
                p[c.length][j + 1] = false;     // Only option is to have all false (0 True)
                res += lastRow(c, p, j+1);
            }
        }

        return res;
    }

    public static int solutionVertical (boolean [][] g) {
        // Array g is flipped
        boolean [][] vertical = new boolean [g[0].length][g.length];

        for (int i = 0; i < vertical.length; i++) {
            for (int j = 0; j < vertical[0].length; j++) {
                vertical[i][j] = g[j][i];
            }
        }

        // Array to analyze previous state i created
        boolean [][] p = new boolean [g[0].length + 1][g.length + 1];

        // Stacks to store previous results and use them if necessary
        Stack <String> rows = new Stack <String> ();
        Stack <Integer> results = new Stack <Integer> ();

        // Calling function with all intial possible states
        int res = start(vertical, p, 0, rows, results);
        p[0][0] = true;
        res += start(vertical, p, 0, rows, results);
        p[1][0] = true;
        res += start(vertical, p, 0, rows, results);
        p[0][0] = false;
        res += start(vertical, p, 0, rows, results);
        
        return res;
    }

    public static int solution (boolean [][] g) {
        // Your code here
        // If the given array has a larger width then height, the array is flipped to improve speed
        if (g[0].length > g.length) {
            // Alternative solution method is called
            return solutionVertical(g);
        }

        // Array to analyze previous state i created
        boolean [][] p = new boolean [g.length+1][g[0].length+1];

        // Stacks to store previous results and use them if necessary
        Stack <String> rows = new Stack <String> ();
        Stack <Integer> results = new Stack <Integer> ();

        // Calling function with all intial possible states
        int res = start(g, p, 0, rows, results);
        p[0][0] = true;
        res += start(g, p, 0, rows, results);
        p[1][0] = true;
        res += start(g, p, 0, rows, results);
        p[0][0] = false;
        res += start(g, p, 0, rows, results);

        return res;
    }

    public static void main(String[] args) {
        boolean [][] gas1 = {{true, false, true}, 
                             {false, true, false}, 
                             {true, false, true}};

        boolean [][] gas2 = {{true, false, true, false, false, true, true, true}, 
                             {true, false, true, false, false, false, true, false}, 
                             {true, true, true, false, false, false, true, false}, 
                             {true, false, true, false, false, false, true, false}, 
                             {true, false, true, false, false, true, true, true}};

        boolean [][] gas3 = {{true, true, false, true, false, true, false, true, true, false}, 
                             {true, true, false, false, false, false, true, true, true, false},
                             {true, true, false, false, false, false, false, false, false, true}, 
                             {false, true, false, false, false, false, true, true, false, false}};

        boolean [][] gas4 = {{false, false, false, false, false, false, false, false, false, false, false, false, false}};

        System.out.println(solution(gas1));
        printGas(gas1);

        System.out.println(solution(gas2));
        printGas(gas2);
        
        System.out.println(solution(gas3));
        printGas(gas3);

        //System.out.println(solution(gas4));
        //printGas(gas4);
        
    }    
}
