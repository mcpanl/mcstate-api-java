package com.zonhor.utils;

import com.zonhor.core.mc.ping.Connection;

import java.io.IOException;

public class CoreMC {
    private static final byte HANDSHAKE_PACKET_ID = 0x00;
    private static final byte STATUS_PACKET_ID = 0x00;
    private static final byte PING_PACKET_ID = 0x01;
    private static final byte PROTOCOL_VERSION = 47;
    private static final byte HANDSHAKE_STATE = 1;
    private static final long PING_TOKEN = 3735928559L; // Arbitrary value

    private static String readStatus(Connection connection) throws IOException {
        Connection.RequestPacket request = connection.createPacket();
        request.writeVarInt(STATUS_PACKET_ID);
        request.send();

        Connection.ResponsePacket response = connection.readPacket();
        int id = response.readVarInt();
        if (id != STATUS_PACKET_ID) {
            throw new IOException("Received invalid status response packet");
        }
        return response.readVarUTF();
    }

    private static void handshake(Connection connection, String host, int port) throws IOException {
        Connection.RequestPacket handshakePacket = connection.createPacket();

        handshakePacket.writeVarInt(HANDSHAKE_PACKET_ID);
        handshakePacket.writeVarInt(PROTOCOL_VERSION);
        handshakePacket.writeVarUTF(host);
        handshakePacket.writeShort(port);
        handshakePacket.writeVarInt(HANDSHAKE_STATE);

        handshakePacket.send();
    }

    public static String getServerInfo(String ip, Integer port) {
        try {
            long startTime = System.currentTimeMillis();
            Connection connection = Connection.Connector.connect2(ip, port);
            handshake(connection, ip, port);
            String response = readStatus(connection);
            System.out.println(response);
            long endTime = System.currentTimeMillis();

            System.out.printf("执行时长：%d 毫秒.", (endTime - startTime));
            System.out.println("");
            return response;
        } catch (Exception e) {
            System.out.println("出错了~");
            return "offline";
        }
    }
}
