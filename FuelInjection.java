import java.math.BigInteger;

public class FuelInjection {
    
    public static int solution (String n) {
        BigInteger num = new BigInteger(n);

        BigInteger div = new BigInteger("2");
        BigInteger one = new BigInteger("1");
        BigInteger zero = new BigInteger("0");

        int steps = 0;
        BigInteger remainder;
        BigInteger quotient;
        
        //System.out.print(num);

        while (num.intValue() != 1) {
            quotient = num.divideAndRemainder(div)[0];
            remainder = num.divideAndRemainder(div)[1];

            //System.out.print(" (" + quotient + "." + remainder + ")");

            if (remainder.equals(zero)) {
                num = num.divide(div);
            } else if (quotient.equals(one)) {
                num = num.subtract(one);
            } else if (quotient.divideAndRemainder(div)[1].equals(zero)) {
                num = num.subtract(one);
            } else {
                num = num.add(one);
            }
            //System.out.print(" -> " + num);
            steps++;
        }

        //System.out.println("");
        return steps;
    }

    public static void main(String[] args) {
        System.out.println(solution("67406534763712726211641660058884099201115885104434760023882136841288313069618515692832974315825313495922298231949373138672355948043152766571296567808332659269564994572656140000344389574120022435714463495031743122390807731823194181973658513020233176985452498279081199404472314802811655824768082110985166340672084454492229252801189742403957029450467388250214501358353312915261004066118140645880633941658603299497698209063510889929202021079926591625770444716951045960277478891794836019580040978608315291377690212791863007764174393209716027254457637891941312587717764400411421385408982726881092425574514688"));
    }
}