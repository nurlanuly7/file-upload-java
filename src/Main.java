import com.dauren.lesson1.Controller.UploadHandler;
import com.dauren.lesson1.FileHandler;
import com.dauren.lesson1.Server;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            System.out.println(server.getAddress());

        server.createContext("/upload", new UploadHandler());

            server.start();
        }
    }
