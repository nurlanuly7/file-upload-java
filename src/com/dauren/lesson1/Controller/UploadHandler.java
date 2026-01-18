package com.dauren.lesson1.Controller;

import com.dauren.lesson1.Dto.FileDto;
import com.dauren.lesson1.FileHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UploadHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("Handler called");

        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        byte[] data = exchange.getRequestBody().readAllBytes();
        System.out.println("Bytes received: " + data.length);

        String filename = exchange.getRequestHeaders().getFirst("X-Filename");
        String contentType = exchange.getRequestHeaders().getFirst("Content-Type");
        long size = data.length;

        FileDto file = new FileDto(filename, contentType, data, size);

        try {
            FileHandler.uploadFile(file);

            String response = "File saved";
            byte[] respBytes = response.getBytes(StandardCharsets.UTF_8);

            exchange.sendResponseHeaders(200, respBytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(respBytes);
            }

        } catch (Exception e) {
            e.printStackTrace(); // ОБЯЗАТЕЛЬНО логируем

            String response = "Upload failed: " + e.getMessage();
            byte[] respBytes = response.getBytes(StandardCharsets.UTF_8);

            exchange.sendResponseHeaders(500, respBytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(respBytes);
            }
        }

        exchange.close();
    }
}
