package com.dauren.lesson1.Dto;

public class FileDto {
    public int id;

    private byte[] data;

    public String name;

    public byte[] getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    public String getContent_type() {
        return content_type;
    }

    public String path;

    public long size;

    public String content_type;

    public FileDto(String name, String content_type, byte[] data, long size ) {
        this.name = name;
        this.content_type = content_type;
        this.data = data;
        this.size = size;
    }

    @Override
    public String toString() {
        return "somth";
    }

}
