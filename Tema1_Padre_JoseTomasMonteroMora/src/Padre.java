
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Padre {
    public static void main(String[] args) {
        try {
            // Iniciar el proceso hijo ejecutando el JAR
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "bin/Hijo.jar");
            Process procesoHijo = processBuilder.start();

            /**
             * Streams para comunicarse con el hijo
             */
            /* Este BufferedReader se utiliza para leer la salida del proceso hijo
             * procesoHijo.getInputStream() obtiene el flujo de entrada del proceso hijo,
             * que es la salida que el proceso hijo envía al proceso padre
            **/
            BufferedReader reader = new BufferedReader(new InputStreamReader(procesoHijo.getInputStream()));
            /*
             * Este PrintWriter se utiliza para enviar la entrada al proceso hijo,
             * procesoHijo.getOutputStream() obtiene el flujo de salida del proceso hijo,
			 * que es la entrada que el proceso padre envía al proceso hijo y
			 * el segundo parámetro true indica que el PrintWriter debe vaciar automáticamente
			 * el buffer después de cada operación de escritura
             */
            PrintWriter writer = new PrintWriter(procesoHijo.getOutputStream(), true);
            //Este BufferedReader se utiliza para leer la entrada del usuario desde la consola
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String input;
            boolean flag = false;
            while (!flag) {
                System.out.print("Ingrese un mensaje (o 'fin' para salir): ");
                input = userInput.readLine();
                writer.println(input); // Enviar la entrada al hijo
                writer.flush(); // Asegurar que se envía el dato
                Thread.sleep(100); //Esperar 100 milisegundos para que el hijo responda y pueda leer la entrada por si acaso

                if ("fin".equalsIgnoreCase(input.trim())) {
                	System.out.println("Saliendo del programa...");
                    flag = true; // Terminar el programa saliendo del bucle
                }else {
                	// Leer y mostrar el número aleatorio generado por el hijo
					String response = reader.readLine();
					System.out.println("Respuesta del hijo: " + response);
				}
            }
            // Esperar a que el hijo termine
            procesoHijo.waitFor();
            // Cerrar los streams y el proceso hijo
         		reader.close();
         		writer.close();
         		userInput.close();
         		procesoHijo.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}