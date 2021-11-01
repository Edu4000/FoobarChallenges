public class Product{

    public static int negativeProduct(int [] arr, int length) {
        int res = 1;
        int iterations = 1;
        int min;
        int minIndex = 0;
        while (iterations < length) {
            min = arr[0];
            for (int i = 0; i < length; i++) {
                if (arr[i] <= min) {
                    min = arr[i];
                    minIndex = i;
                }
            }
            res = res * min;
            arr[minIndex] = 0;
            iterations ++;
        }
        return res;
    }
    public static String result(int[] a) {
        String res = "";
        int[] negatives = new int [a.length];
        int zeros = 0;
        int positive = 0;
        int j = 0;
        int i = 0;
        int result;
        if (a.length > 1) {
            result = 1;
        } else {
            result = a[0];
            res = String.valueOf(result);
            return res;
        }
        while (i < a.length) {
            if (a[i] >= 1) {
                result = result * a[i];
                positive++;
            } else if (a[i] < 0) {
                negatives[j] = a[i];
                j++;
            } else {
                zeros++;
            }
            i++;
        }
        if ((j == 1 && zeros > 0) && positive == 0) {
            result = 0;
        } else if (j > 1 && positive >= 1) {
            if (j % 2 == 0) {
                for (int k = 0; k < j; k++) {
                    result = result * negatives[k];
                }
            } else {
                result = result * negativeProduct(negatives, j);
            }
        } else if (j > 1) {
            result = 1;
            if (j % 2 == 0) {
                for (int k = 0; k < j; k++) {
                    result = result * negatives[k];
                }
            } else {
                result = result * negativeProduct(negatives, j);
            }
        } else if (zeros == a.length) {
            result = 0;
        }
        res = String.valueOf(result);
        return res;
    }

    public static void main(String[] args) {
        int [] array = {10000,1000,10000,10000,1000,0};
        System.out.println(result(array));
    }
}