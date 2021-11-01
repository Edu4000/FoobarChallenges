import java.util.ArrayList;

public class AlreadyDidThat {

   public static int solution (String n, int b) {
        String x = "";
        String y = "";
        String z = n;
        String aux = "";

        ArrayList<String> id = new ArrayList<String>();
        
        while (!id.contains(z)) {
            id.add(z);
            int i = 0;
            while (i < z.length()-1) {
                if (z.charAt(i) < z.charAt(i + 1)) {
                    String max = z.substring(i+1, i+2);
                    String min = z.substring(i, i+1);
                    z = z.substring(0, i) + max + min + z.substring(i+2); 
                    i = 0;               
                } else {
                    i++;
                }
            }

            x = "";
            y = "";

            x = z;
            for (int j = 0; j < z.length(); j++) {
                y += z.charAt(z.length()-j-1);
            }

            z = "";
            aux = "";
                
            int offset = 0;
            for (int j = n.length() - 1; j >= 0; j--) {

                if (x.charAt(j) - y.charAt(j) - offset >= 0) {
                    aux += x.charAt(j) - y.charAt(j) - offset;
                    offset = 0;
                } else {
                    aux += x.charAt(j) - y.charAt(j) - offset + b;
                    offset = 1;
                }

            }
            for (int j = 0; j < n.length(); j++) {
                z += aux.charAt(aux.length()-j-1);
            }
        }
        
        for (int i = 0; i < id.size(); i++) {
            if (id.get(i).equals(z)) {
                return id.size() - i;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        System.out.println(solution("210022", 3));
    }
   
}