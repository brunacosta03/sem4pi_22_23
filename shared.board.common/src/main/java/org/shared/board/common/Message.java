package org.shared.board.common;

public class Message {
    private int version;
    private int code;
    private int d_length_1;
    private int d_length_2;
    private byte[] data;

    public Message(int version, int code, int d_length_1, int d_length_2, byte[] data) {
        this.version = version;
        this.code = code;
        this.d_length_1 = d_length_1;
        this.d_length_2 = d_length_2;
        this.data = data;
    }

    public int version() {
        return version;
    }

    public int code() {
        return code;
    }

    public int d_length_1() {
        return d_length_1;
    }

    public int d_length_2() {
        return d_length_2;
    }

    public byte[] data() {
        return data;
    }
}