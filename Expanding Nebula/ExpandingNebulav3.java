public class ExpandingNebulav3 {

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

    public static int firstColumn (boolean [][] c, boolean [][] p, int cell) {
        int res = 0;
        int i = cell / c[0].length;

        p[i+1][0] = true;
        res += paths(c, p, cell, false);

        p[i+1][0] = false;
        res += paths(c, p, cell, false);

        return res;
    }

    public static int numTrue (boolean p[][], int i, int j) {
        int res = 0;
        if (p[i][j]) {
            res++;
        }
        if (p[i + 1][j]) {
            res++;
        }
        if (p[i][j + 1]) {
            res++;
        }
        /*if (p[i + 1][j + 1]) {
            res++;
        }*/
        return res;
    }

    public static int firstRow (boolean [][] c, boolean [][] p, int cell) {
        // Location of cell
        int i = cell / c[0].length;
        int j = cell % c[0].length;

        // Storing results
        int res = 0;

        printGas(p);

        if (cell == c.length) {
            cell++;
            return paths(c, p, cell, true);
        }
        
        if (c[i][j]) {
            cell++;

            if (p[i][j] && p[i+1][j]) {
                return 0;
            } else if (p[i][j] || p[i+1][j]) {
                p[i][j + 1] = false;
                p[i + 1][j + 1] = false;
                res += firstRow(c, p, cell);
            } else {
                p[i][j + 1] = true;
                p[i + 1][j + 1] = false;
                res += firstRow(c, p, cell);

                p[i][j + 1] = false;
                p[i + 1][j + 1] = true;
                res += firstRow(c, p, cell);
            }
        } else {
            cell++;

            if (p[i][j] && p[i+1][j]) {
                p[i][j + 1] = true;
                p[i + 1][j + 1] = true;
                res += firstRow(c, p, cell);

                p[i][j + 1] = true;
                p[i + 1][j + 1] = false;
                res += firstRow(c, p, cell);

                p[i][j + 1] = false;
                p[i + 1][j + 1] = true;
                res += firstRow(c, p, cell);

                p[i][j + 1] = false;
                p[i + 1][j + 1] = false;
                res += firstRow(c, p, cell);                
            } else if (p[i][j] || p[i+1][j]) {
                p[i][j + 1] = true;
                p[i + 1][j + 1] = true;
                res += firstRow(c, p, cell);

                p[i][j + 1] = true;
                p[i + 1][j + 1] = false;
                res += firstRow(c, p, cell);

                p[i][j + 1] = false;
                p[i + 1][j + 1] = true;
                res += firstRow(c, p, cell);
            } else {
                p[i][j + 1] = false;
                p[i + 1][j + 1] = false;
                res += firstRow(c, p, cell);

                p[i][j + 1] = true;
                p[i + 1][j + 1] = true;
                res += firstRow(c, p, cell);
            }
        }

        return res;
    }
    
    public static int paths (boolean [][] c, boolean [][] p, int cell, boolean firstColumn) {
        //  c[i][j] <=> | p[i][j]         p[i][j + 1]     | 
        //              | p[i + 1][j]     p[i + 1][j + 1] |

        // Base case: If end of grid is reached return 1
        if (cell == (c.length * c[0].length)) { 
            return 1;
        }
        
        // Location of cell
        int i = cell / c[0].length;
        int j = cell % c[0].length;

        int res = 0;
        int gasCells = numTrue(p, i, j);

        printGas(p);

        if (j == 0 && firstColumn) {

            firstColumn(c, p, cell);

        } else if (c[i][j]) {
            cell++;
            if (gasCells > 1) {
                return 0;
            } else if (gasCells == 1) {
                p[i + 1][j + 1] = false;
                res += paths(c, p, cell, true);
            } else {
                p[i + 1][j + 1] = true;
                res += paths(c, p, cell, true);
            }
        } else {

            cell++;
            if (gasCells == 0) {
                p[i + 1][j + 1] = false;
                res += paths(c, p, cell, true);
            } else {
                p[i + 1][j + 1] = true;
                res += paths(c, p, cell, true);
            }
        }
        return res;
    }

    public static int solution (boolean [][] g) {
        boolean [][] p = new boolean [g.length+1][g[0].length+1];

        int res = 0;

        res += firstRow(g, p, 0);

        p[0][0] = true;
        res += firstRow(g, p, 0);

        p[1][0] = true;
        res += firstRow(g, p, 0);

        p[0][0] = false;
        res += firstRow(g, p, 0);

        return res;
    }
    

    public static void main(String[] args) {
        boolean [][] gas1 = {{true, false, true}, 
                             {false, true, false}, 
                             {true, false, true}};

        System.out.println(solution(gas1));
        
    }
}
