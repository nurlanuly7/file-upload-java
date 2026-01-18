package com.dauren.lesson1;

import com.dauren.lesson1.Dto.FileDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class FileHandler {

    public static long uploadFile(FileDto file) throws Exception {
        String storedName = saveFileToDisk(file);

        try {
            return saveMetadataToDb(file, storedName);
        } catch (Exception e) {
            deleteFile(storedName);
            throw e;
        }
    }


    private static String saveFileToDisk(FileDto dto) throws IOException {
        java.nio.file.Path dir = java.nio.file.Path.of("Files");

        Files.createDirectories(dir);

        String storedName = UUID.randomUUID() + "_" + dto.getName();
        Files.write(dir.resolve(storedName), dto.getData());

        return storedName;
    }

    private static long saveMetadataToDb(FileDto dto, String storedName) {

        String URL = "jdbc:postgresql://localhost:5432/";
        String USER = "postgres";
        String PASSWORD = "postgres";

        String sql = """
        INSERT INTO file (name, path, size, content_type)
        VALUES (?, ?, ?, ?)
        RETURNING id
    """;

        long size = dto.getData().length;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dto.getName());
            ps.setString(2, "Files/");
            ps.setLong(3, size);
            ps.setString(4, dto.getContent_type());

            try (var rs = ps.executeQuery()) {
                rs.next();
                return rs.getLong("id"); 
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void deleteFile(String storedName) throws IOException {
        Files.deleteIfExists(Path.of("Files", storedName));
    }
}
