import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ServidorHTTP {

    public static void main(String[] args) throws IOException {
        // Render inyecta el puerto en la variable de entorno PORT (por defecto 8080 si es local)
        String portEnv = System.getenv("PORT");
        int puerto = (portEnv != null) ? Integer.parseInt(portEnv) : 8080;

        // Crear el servidor HTTP escuchando en todas las interfaces (0.0.0.0)
        HttpServer servidor = HttpServer.create(new InetSocketAddress(puerto), 0);

        // Registrar el endpoint /status requerido para la entrega
        servidor.createContext("/status", new StatusHandler());

        servidor.setExecutor(null); // Usar el ejecutor por defecto
        servidor.start();

        System.out.println("Servidor HTTP ejecutandose en el puerto: " + puerto);
    }

    static class StatusHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String respuesta = "Servidor en ejecucion: OK - 200";
            
            // Log en consola para que se vea reflejado en la terminal de Render
            System.out.println("Peticion recibida en /status desde: " + exchange.getRemoteAddress());

            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=utf-8");
            exchange.sendResponseHeaders(200, respuesta.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(respuesta.getBytes());
            os.close();
        }
    }
}
