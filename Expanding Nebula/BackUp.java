public class BackUp {
    // Exclusive OR method
    public static boolean xor (boolean a, boolean b) {
        return !(a && b) && (a || b);
    }

    public static int possibleNebulas (boolean [][] presState, boolean [][]prevState, int cell, boolean firstColumn) {
        // Base case: If the process reaches the end of the grid, it is a valid answer
        if (cell == (presState.length * presState[0].length)) {
            //printGas(prevState);
            return 1;
        }

        // Variables
        int possibleNum = 0;                    // Number of possible previous grids
        int i = cell / presState[0].length;     // Column index
        int j = cell % presState[0].length;     // Row index

        // In any given 2x2 grid with position [i][j] in prevState[][]
        boolean bool1 = prevState[i][j];        // State of [0][0]
        boolean bool2 = prevState[i + 1][j];    // State of [1][0]
        boolean bool3 = prevState[i][j + 1];    // State of [0][1]

        //System.out.println("Position: " + i + " " + j );
        //System.out.println(presState[i][j]);
        //printGas(prevState);

        if (j == 0 && firstColumn) {
        // Left most location of the grid

            if (cell == 0) {
            // If position is [0][0]

                if (presState[i][j]) {
                    // [0][0] and [1][0] both false
                    possibleNum += possibleNebulas(presState, prevState, cell, false);
    
                    // [0][0] and [1][0 false, one true
                    prevState[i][j] = false;
                    prevState[i + 1][j] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, false);
    
                    // First column one true, one false
                    prevState[i][j] = true;
                    prevState[i + 1][j] = false;
                    possibleNum += possibleNebulas(presState, prevState, cell, false);
                } else {
                    // First column both false
                    possibleNum += possibleNebulas(presState, prevState, cell, false);
    
                    // First column one false, one true
                    prevState[i][j] = false;
                    prevState[i + 1][j] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, false);
    
                    // First column one true, one false
                    prevState[i][j] = true;
                    prevState[i + 1][j] = false;
                    possibleNum += possibleNebulas(presState, prevState, cell, false);
    
                    // First column both true
                    prevState[i][j] = true;
                    prevState[i + 1][j] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, false);
                }

            } else {
            // Modifications to first column

                // First element of a row remains unintact
                possibleNum += possibleNebulas(presState, prevState, cell, false);

                // Then the cell that does not affect any previous grid is modified
                prevState[i+1][j] = true;
                possibleNum += possibleNebulas(presState, prevState, cell, false);
                prevState[i+1][j] = false;
            }
        } else if (i == 0) {
        ///////////////////////  Case for true in the top most row  ///////////////////////
            if (presState[i][j]) {

                if (bool1 && bool2) {
                // If both of the two first cells are true, the grid is not valid

                    // Immediatly cancel the path
                    return 0;

                } else if (bool1 || bool2) {
                // If the one of the first 2 cells of the 2x2 grid is true, the rest need to be false
    
                    // Moving position
                    cell++;
                    // Second column of grid is false
                    prevState[i][j + 1] = false;
                    prevState[i+ 1][j + 1] = false;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);

                } else {
                // If the first 2 cells of the 2x2 grid are false, one of the other two must be true
    
                    // Moving position
                    cell++;
                    // Second column of grid is true and false
                    prevState[i][j + 1] = true;
                    prevState[i+ 1][j + 1] = false;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
    
                    // Second column of grid is false and true
                    prevState[i][j + 1] = false;
                    prevState[i+ 1][j + 1] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);

                }
        ///////////////////////  Case for false in the top most row  ///////////////////////
            } else {
                if (bool1 && bool2) {
                // If both two first cells are true, there are 4 possibilieties

                    // Moving position
                    cell++;
                    // One true and the other false (3 True)
                    prevState[i][j + 1] = true;
                    prevState[i+ 1][j + 1] = false;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
    
                    // One false and the other true (3 True)
                    prevState[i][j + 1] = false;
                    prevState[i+ 1][j + 1] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                    
                    // Both true (4 true)
                    prevState[i][j + 1] = true;
                    prevState[i+ 1][j + 1] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
    
                    // Both false (2 True)
                    prevState[i][j + 1] = false;
                    prevState[i+ 1][j + 1] = false;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
    
                } else if (bool1 || bool2) {
                // If one of the two first cells is true, there are 3 possibilities

                    // Moving position
                    cell++;
                    // One true and the other false (2 True)
                    prevState[i][j + 1] = true;
                    prevState[i+ 1][j + 1] = false;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
    
                    // One false and the other true (2 True)
                    prevState[i][j + 1] = false;
                    prevState[i+ 1][j + 1] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
    
                    // One both true (3 True)
                    prevState[i][j + 1] = true;
                    prevState[i+ 1][j + 1] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                } else {
                // If the first 2 cells of the 2x2 grid are false, there exist two possibilities
                
                    // Moving position
                    cell++;
                    // All cells of the grid become false (0 True)
                    prevState[i][j + 1] = false;
                    prevState[i+ 1][j + 1] = false;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
    
                    // Two cells of the grid become true (2 True)
                    prevState[i][j + 1] = true;
                    prevState[i+ 1][j + 1] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
    
                } 
            }

        } else if (presState[i][j]) {
            // Moving position
            cell++;
            if (bool1 && bool2) {
                return 0;
            } else if (!(bool1 || bool2)) {
                if (!bool3) {
                    prevState[i + 1][j + 1] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                    prevState[i + 1][j + 1] = false;
                } else {
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                }
            } else {
                if (bool3) {
                    return 0;
                } else {
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                }
            }

        } else {
            // Moving position
            cell++;
            // 0 True
            if (bool1 && bool2) {
            // 2 True
                if (!bool3) {
                    // 3 True
                    prevState[i + 1][j + 1] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                    //prevState[i + 1][j + 1] = false;

                    // 2 True
                    prevState[i + 1][j + 1] = false;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                } else {
                    // 4 True
                    prevState[i + 1][j + 1] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                    //prevState[i + 1][j + 1] = false;

                    // 3 True
                    prevState[i + 1][j + 1] = false;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                }
            } else if (!(bool1 || bool2)) {
                if (bool3) {
                    // 2 True
                    prevState[i + 1][j + 1] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                    //prevState[i + 1][j + 1] = false;
                } else {
                    // 0 True
                    prevState[i + 1][j + 1] = false;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                }
            // 1 True
            } else {
                if (bool3) {
                    // 3 True
                    prevState[i + 1][j + 1] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                    //prevState[i + 1][j + 1] = false;
                    
                    // 2 True
                    prevState[i + 1][j + 1] = false;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                } else {
                    // 2 True
                    prevState[i + 1][j + 1] = true;
                    possibleNum += possibleNebulas(presState, prevState, cell, true);
                    //prevState[i + 1][j + 1] = false;
                }
            }
        }
        return possibleNum;
    }
    
    public static int solutionVertical (boolean [][] g) {
        boolean [][] vertical = new boolean [g[0].length][g.length];

        for (int i = 0; i < vertical.length; i++) {
            for (int j = 0; j < vertical[0].length; j++) {
                vertical[i][j] = g[j][i];
            }
        }

        boolean [][] prevState = new boolean [g[0].length + 1][g.length + 1];
        int res;
        res = possibleNebulas(vertical, prevState, 0, true);
        return res;
    }
    
    public static int solution(boolean[][] g) {
        // Your code here
        /*if (g[0].length > g.length) {
            return solutionVertical(g);
        }*/
        
        boolean [][] prevState = new boolean [g.length + 1][g[0].length + 1];
        int res;
        res = possibleNebulas(g, prevState, 0, true);
        return res;
    }
    
    public static void main(String[] args) {
        
    }
}
