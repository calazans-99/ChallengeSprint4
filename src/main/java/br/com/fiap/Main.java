package br.com.fiap;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Main class.
 */
public class Main {
    // Base URI for the Grizzly HTTP server
    public static final String BASE_URI = "http://localhost:8080/";
    private static Connection connection;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // Create a resource config that scans for JAX-RS resources and providers in br.com.fiap package
        final ResourceConfig rc = new ResourceConfig().packages("br.com.fiap");

        // Create and start a new instance of Grizzly HTTP server exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Initialize the database connection.
     */
    public static void initializeDatabaseConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
        String user = "RM556620";
        String password = "290999";

        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Conexão com o banco de dados Oracle estabelecida com sucesso.");
    }

    /**
     * Close the database connection.
     */
    public static void closeDatabaseConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão com o banco de dados encerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            // Inicializa a conexão com o banco de dados
            initializeDatabaseConnection();

            // Inicia o servidor HTTP
            final HttpServer server = startServer();
            System.out.println(String.format("Aplicação Jersey iniciada com endpoints disponíveis em %s%nPressione Enter para parar...", BASE_URI));
            System.in.read();

            // Para o servidor ao encerrar a aplicação
            server.stop();
        } catch (SQLException e) {
            System.out.println("Erro ao estabelecer conexão com o banco de dados: " + e.getMessage());
        } finally {
            // Encerra a conexão com o banco de dados ao sair
            closeDatabaseConnection();
        }
    }

    /**
     * Getter para a conexão do banco de dados.
     * @return A conexão atual com o banco de dados.
     */
    public static Connection getConnection() {
        return connection;
    }
}
