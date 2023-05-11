package org.shared.board.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageFormat {
    Socket sock;
    DataOutputStream sOut;

    DataInputStream sIn;

    public MessageFormat(Socket sockp) {
        try {
            this.sock = sockp;
            this.sOut = new DataOutputStream(sockp.getOutputStream());
            this.sIn = new DataInputStream(sock.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(int version,
                            int code,
                            String text){
        byte[] data = text.getBytes();
        int dataLength = data.length;

        int d_length_1 = dataLength % 256;
        int d_length_2 = dataLength / 256;

        try {
            sOut.writeByte(version);
            sOut.writeByte(code);
            sOut.writeByte(d_length_1);
            sOut.writeByte(d_length_2);
            sOut.write(data,0, dataLength);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Message readMessage() {
        try {
            int version = sIn.readUnsignedByte();
            int code = sIn.readUnsignedByte();
            int d_length_1 = sIn.readUnsignedByte();
            int d_length_2 = sIn.readUnsignedByte();

            // calculate data length
            int dataLength = d_length_1 + (d_length_2 * 256);

            byte[] data = new byte[dataLength];

            if (dataLength > 0) {
                sIn.readFully(data);
            }

            return new Message(version, code, d_length_1, d_length_2, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
