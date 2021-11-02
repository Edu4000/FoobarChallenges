import java.util.Stack;

public class Bunny {

    // To see the maze
    public static void printArray (int [][] a) {
        for (int[] num : a) {
            for (int value : num) {
                System.out.print(value);
                System.out.print(" ");
            } 
            System.out.println("");
        }
        System.out.println("");
    }

    public static int[][] copyArray(int[][] map) {
        int [][] res = new int [map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                res[i][j] = map[i][j];
            }
        }
        return res;
    }

    // To get coordinates of removable walls
    public static Stack<Integer> removableWalls (int[][] map) {
        Stack <Integer> res = new Stack <Integer> ();
        int freeRoute;;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++){
                freeRoute = 0;
                if (map[i][j] == 1) {
                    if (i + 1 < map.length && map[i + 1][j] == 0) {
                        freeRoute++;
                    }
                    if (j + 1 < map[0].length && map[i][j + 1] == 0) {
                        freeRoute++;
                    }
                    if (i - 1 >= 0 && map[i - 1][j] == 0) {
                        freeRoute++;
                    }
                    if (j - 1 >= 0 && map[i][j-1] == 0) {
                        freeRoute++;
                    }
                    if (freeRoute >= 2) {
                        res.push(i);
                        res.push(j);
                    }
                }
            }
        }
        return res;
    }

    public static int numZeros (int map[][]) {
        int counter = 0;
        for (int [] arr : map) {
            for (int num : arr) {
                if (num == 0) {
                    counter++;
                }
            }
        }
        return counter + 1;
    }

    public static int[][] movesTo(int[][] map, int[] posA, int[]posB) {
        Stack <Integer> direction = new Stack<Integer>(); // 0:right / 1:down / 2:left / 3:up
        int i = posA[0];
        int j = posA[1];
        int [][] aux = copyArray(map);
        int [][] aux2 = copyArray(map);

        int redirection;
        int moves = 1;
        int zeros = numZeros(map);
        int min;

        while ((i != posB[0] || j != posB[1]) && moves < zeros) {
            if (i < posB[0] && aux[i + 1][j] == 0) {
                aux[i][j] = 1;
                i++;
                moves++;
                direction.push(1);
            } else if (i > posB[0] && aux[i - 1][j] == 0) {
                aux[i][j] = 1;
                i--;
                moves++;
                direction.push(3);
            } else if (j < posB[1] && aux[i][j + 1] == 0) {
                aux[i][j] = 1;
                j++;
                moves++;
                direction.push(0);
            } else if (j > posB[1] && aux[i][j - 1] == 0) {
                aux[i][j] = 1;
                j--;
                moves++;
                direction.push(2);
            } else if (i + 1 < aux.length && aux[i + 1][j] == 0) {
                aux[i][j] = 1;
                i++;
                moves++;
                direction.push(1);
            } else if (j + 1 < aux[0].length && aux[i][j + 1] == 0) {
                aux[i][j] = 1;
                j++;
                moves++;
                direction.push(0);
            } else if (i - 1 >= 0 && aux[i - 1][j] == 0) {
                aux[i][j] = 1;
                i--;
                moves++;
                direction.push(3);
            } else if (j - 1 >= 0 && aux[i][j - 1] == 0) {
                aux[i][j] = 1;
                j--;
                moves++;
                direction.push(2);
            } else if (!direction.empty()) {
                aux[i][j] = 1;
                redirection = direction.pop();
                if (redirection % 2 == 0) {
                    if (redirection < (redirection + 2) % 4) {
                        j--;
                    } else {
                        j++;
                    }
                } else {
                    if (redirection < (redirection + 2) % 4) {
                        i--;
                    } else {
                        i++;
                    }

                }
                moves--;
            } else {
                moves = zeros;
            }
        }
        min = moves;
        aux[posB[0]][posB[1]] = min;
        moves = 1;
        i = posA[0];
        j = posA[1];
        direction.clear();
        
        while ((i != posB[0] || j != posB[1]) && moves < zeros) {
            if (j < posB[1] && aux2[i][j + 1] == 0) {
                aux2[i][j] = 1;
                j++;
                moves++;
                direction.push(0);
            } else if (j > posB[1] && aux2[i][j - 1] == 0) {
                aux2[i][j] = 1;
                j--;
                moves++;
                direction.push(2);
            } else if (i < posB[0] && aux2[i + 1][j] == 0) {
                aux2[i][j] = 1;
                i++;
                moves++;
                direction.push(1);
            } else if (i > posB[0] && aux2[i - 1][j] == 0) {
                aux2[i][j] = 1;
                i--;
                moves++;
                direction.push(3);
            } else if (j + 1 < aux2[0].length && aux2[i][j + 1] == 0) {
                aux2[i][j] = 1;
                j++;
                moves++;
                direction.push(0);
            } else if (i + 1 < aux2.length && aux2[i + 1][j] == 0) {
                aux2[i][j] = 1;
                i++;
                moves++;
                direction.push(1);
            } else if (j - 1 >= 0 && aux2[i][j - 1] == 0) {
                aux2[i][j] = 1;
                j--;
                moves++;
                direction.push(2);
            } else if (i - 1 >= 0 && aux2[i - 1][j] == 0) {
                aux2[i][j] = 1;
                i--;
                moves++;
                direction.push(3);
            } else  if (!direction.empty()) {
                aux2[i][j] = 1;
                redirection = direction.pop();
                if (redirection % 2 == 0) {
                    if (redirection < (redirection + 2) % 4) {
                        j--;
                    } else {
                        j++;
                    }
                } else {
                    if (redirection < (redirection + 2) % 4) {
                        i--;
                    } else {
                        i++;
                    }

                }
                moves--;
            } else {
                moves = zeros;
            }
        }
        
        if (moves < min) {
            min = moves;
            aux2[posB[0]][posB[1]] = min;
            return aux2;
        }
        return aux;
    }

    private static int solution(int[][] map) {
        
        Stack <Integer> walls = new Stack <Integer> ();
        walls = removableWalls(map);
        int [] lengths = new int [1 + walls.size() / 2];
        int index = 0;
        int [][] mapAux = copyArray(map);

        int [] posA = {0,0};
        int [] posB = {mapAux.length - 1, mapAux[0].length - 1};

        int length = movesTo(mapAux, posA, posB)[posB[0]][posB[1]];

        if (length == map.length + map[0].length - 1) {
            return length;
        } else {
            lengths[index] = length;
            index++;
        }

        while (!walls.empty()) {
            length = 0;
            mapAux = copyArray(map);
            posA[0] = 0;
            posA[1] = 0;

            posB[1] = walls.pop();
            posB[0] = walls.pop();

            mapAux[posB[0]][posB[1]] = 0;

            length += movesTo(mapAux, posA, posB)[posB[0]][posB[1]];

            posA[0] = posB[0];
            posA[1] = posB[1];

            posB[0] = map.length - 1;
            posB[1] = map[0].length - 1;

            mapAux = movesTo(mapAux, posA, posB);
            length += mapAux[posB[0]][posB[1]];
            length--;

            if (length == map.length + map[0].length - 1) {
                return length;
            } else {
                lengths[index] = length;
                index++;
            }
        }

        int min = lengths[0];

        for (int l : lengths) {
            if (l < min) {
                min = l;
            }
        }
        return min;
    }


    public static void main(String[] args) {
        int [][] list = {{0, 1},
                          {1,0}};

        int [][] list2 = {{0, 1, 1, 0}, 
                        {0, 0, 0, 1}, 
                        {1, 1, 0, 0}, 
                        {1, 1, 1, 0}};

        int [][] list3 = {{0, 0, 0, 0, 0, 0},
                        {1, 1, 1, 1, 0, 1}, 
                        {0, 0, 0, 0, 0, 0}, 
                        {0, 1, 0, 1, 1, 1}, 
                        {0, 1, 1, 1, 1, 1}, 
                        {0, 0, 0, 0, 0, 0}};

        int [][] list4 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0},
                        {0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
                        {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                        {0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                        {0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0},
                        {1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1},
                        {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0},
                        {0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

                    

        /* Stack <Integer> trial = removableWalls(list);
        while (!trial.empty()) {
            int j = trial.pop();
            int i = trial.pop();
            System.out.println("(" + i + ", " + j + ")");
        } */
        System.out.println(solution(list4));

        //int [] posA = {0,0};
        //int [] posB = {1,1};
        //System.out.println(movesTo(list, posA, posB)[posB[0]][posB[1]]);
        
    }
        
    
}