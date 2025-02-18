import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;

public class Hijo {
    public static void main(String[] args) {
        try (
        	//Creo un BufferedReader llamado reader que lee de la entrada del sistema
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        	/*
        	 *Creo un PrintWriter llamado writer que escribe en la salida del sistema
        	 *el segundo parámetro true indica que el PrintWriter debe vaciar automáticamente
        	 *el buffer después de cada operación de escritura
        	 */
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true)
        ) {
        	//Creo una instancia de la clase Random, que se utilizará para generar números aleatorios
            Random random = new Random();
            String input;
            Boolean flag = false;
            while (!flag && (input = reader.readLine()) != null) {
                if ("fin".equalsIgnoreCase(input.trim())) {// Si recibe "fin", el proceso hijo termina.
                    flag = true; 
                }else {
                	// Genera un número aleatorio y lo envía al padre
                    int randomNumber = random.nextInt(11); // Números aleatorios entre 0 y 10
                    writer.println(randomNumber);//Escribo el número aleatorio en la salida
                    writer.flush();//Asegura que se envía el dato
                }  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}