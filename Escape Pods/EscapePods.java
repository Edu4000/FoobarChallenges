public class EscapePods {

    public static boolean leadsToExit(int [] exits, int room) {
        for (int i : exits) {
            if (i == room) {
                return true;
            }
        }
        return false;
    }
    
    public static int solution(int[] entrances, int[] exits, int[][] path) {
        // Your code here
        int next_room;
        int res = 0;
        int maximum_flow = 0;

        for (int i : entrances) {
            for (int j = 0; j < path[i].length; j++) {
                if (path[i][j] != 0) {
                    maximum_flow = path[i][j];
                    next_room = j;
                    while (!leadsToExit(exits, next_room)) {
                        for (int k = 0; k < path[next_room].length; k++) {
                            if (path[next_room][k] != 0) {
                                if (path[next_room][k] <= maximum_flow) {
                                    maximum_flow = path[next_room][k];
                                }
                                path[next_room][k] = 0;
                                next_room = k;
                                break;
                            }
                            
                        }
                    }
                    res += maximum_flow;
                    maximum_flow = 0;
                }
            }

            res += maximum_flow;
            maximum_flow = 0;
        }
        return res;
    }


    public static void main(String[] args) {

        int [] entrances = {0, 1};
        int [] exits = {4, 5};
        
        int [][] path =  {{0, 0, 4, 6, 0, 0}, 
                          {0, 0, 5, 2, 0, 0}, 
                          {0, 0, 0, 0, 6, 4}, 
                          {0, 0, 0, 0, 6, 6}, 
                          {0, 0, 0, 0, 0, 0}, 
                          {0, 0, 0, 0, 0, 0}};
        
        System.out.println(solution(entrances, exits, path));


        int [] entrances2 = {0};
        int [] exits2 = {3};
        int [][] path2 = {{0, 7, 0, 0}, {0, 0, 6, 0}, {0, 0, 0, 8}, {9, 0, 0, 0}};
        
        System.out.println(solution(entrances2, exits2, path2));
    }
}