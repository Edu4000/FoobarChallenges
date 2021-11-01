import java.util.Stack;

public class Ejemplo {

    public static void main(String[] args) {
        Stack <String> nombres = new Stack <String> ();

        nombres.push("Hola");
        nombres.push("como");
        nombres.push("estas");

        System.out.println(nombres);

        Stack <Integer> numeros = new Stack <Integer> ();

        numeros.push(1);
        numeros.push(2);
        numeros.push(3);

        System.out.println(numeros);
        int aux;
        while (!numeros.empty()) {
            aux = numeros.pop();
            System.out.println(aux);
        }

        
    }
    
}
