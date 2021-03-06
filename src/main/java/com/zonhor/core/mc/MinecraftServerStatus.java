package com.zonhor.core.mc;

import com.zonhor.core.mc.MinecraftServer.Address;
import com.zonhor.core.mc.ping.MinecraftPinger;
import com.zonhor.core.mc.ping.PingResponse;
import com.zonhor.core.mc.ping.Pinger;
import com.zonhor.core.mc.query.MinecraftQuery;
import com.zonhor.core.mc.query.QueryResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public class MinecraftServerStatus {

    private static final int DEFAULT_TIMEOUT = 6000;

    private MinecraftServerStatus() {
    }


    /**
     * Ping a Minecraft server.
     * @param host the hostname or IP address of the server
     * @param port the port number the server is bound to
     * @return the latency in milliseconds determined by the ping from this machine to the server and back
     * @throws IOException if an error occurs connecting or communicating with the target server
     * @throws URISyntaxException if the given host and/or port number has invalid URI syntax
     */
    public static int ping(String host, int port) throws IOException, URISyntaxException {
        return doPing(com.zonhor.core.mc.InetServerAddress.resolve(host, port));
    }

    /**
     * Ping a Minecraft server.
     * @param address the address of the server
     * @return the latency in milliseconds determined by the ping from this machine to the server and back
     * @throws IOException if an error occurs connecting or communicating with the target server
     * @throws URISyntaxException if the given address has invalid URI syntax
     */
    public static int ping(String address) throws IOException, URISyntaxException {
        return doPing(com.zonhor.core.mc.InetServerAddress.resolve(new Address(address)));
    }

    /**
     * Ping a Minecraft server.
     * @param address the address of the server
     * @return the latency in milliseconds determined by the ping from this machine to the server and back
     * @throws IOException if an error occurs connecting or communicating with the target server
     */
    public static int ping(Address address) throws IOException {
        return doPing(com.zonhor.core.mc.InetServerAddress.resolve(address));
    }

    private static int doPing(com.zonhor.core.mc.InetServerAddress address) throws IOException {
        return executePingFunction(address, new PingFunction<Integer>() {
            @Override
            public Integer apply(Pinger pinger) throws IOException {
                return pinger.ping();
            }
        });
    }


    /**
     * Retrieve information on a Minecraft server.<p>
     * <b>Note:</b> This is like {@link #pingServerStatus(String, int)} but excludes the dynamic server status information
     * so that only mostly static information is retrieved.
     * @param host the hostname or IP address of the server
     * @param port the port number the server is bound to
     * @return a MinecraftServer instance containing server information
     * @throws IOException if an error occurs connecting or communicating with the target server
     * @throws URISyntaxException if the given host and/or port number has invalid URI syntax
     * @see <a href="http://wiki.vg/Server_List_Ping">Server List Ping Protocol (http://wiki.vg/Server_List_Ping)</a>
     */
    public static com.zonhor.core.mc.MinecraftServer pingServer(String host, int port) throws IOException, URISyntaxException {
        return doPingServer(com.zonhor.core.mc.InetServerAddress.resolve(host, port));
    }

    /**
     * Retrieve information on a Minecraft server.<p>
     * <b>Note:</b> This is like {@link #pingServerStatus(String)} but excludes the dynamic server status information
     * so that only mostly static information is retrieved.
     * @param address the address of the server
     * @return a MinecraftServer instance containing server information
     * @throws IOException if an error occurs connecting or communicating with the target server
     * @throws URISyntaxException if the given address has invalid URI syntax
     * @see <a href="http://wiki.vg/Server_List_Ping">Server List Ping Protocol (http://wiki.vg/Server_List_Ping)</a>
     */
    public static com.zonhor.core.mc.MinecraftServer pingServer(String address) throws IOException, URISyntaxException {
        return doPingServer(com.zonhor.core.mc.InetServerAddress.resolve(new Address(address)));
    }

    /**
     * Retrieve information on a Minecraft server.<p>
     * <b>Note:</b> This is like {@link #pingServerStatus(Address)} but excludes the dynamic server status information
     * so that only mostly static information is retrieved.
     * @param address the address of the server
     * @return a MinecraftServer instance containing server information
     * @throws IOException if an error occurs connecting or communicating with the target server
     * @see <a href="http://wiki.vg/Server_List_Ping">Server List Ping Protocol (http://wiki.vg/Server_List_Ping)</a>
     */
    public static com.zonhor.core.mc.MinecraftServer pingServer(Address address) throws IOException {
        return doPingServer(com.zonhor.core.mc.InetServerAddress.resolve(address));
    }

    private static com.zonhor.core.mc.MinecraftServer doPingServer(com.zonhor.core.mc.InetServerAddress address) throws IOException {
        return executePingFunction(address, new PingFunction<com.zonhor.core.mc.MinecraftServer>() {
            @Override
            public com.zonhor.core.mc.MinecraftServer apply(Pinger pinger) throws IOException {
                return pinger.pingServer();
            }
        });
    }


    /**
     * Retrieve information on a Minecraft server including its status and the ping latency.<p>
     * <b>Note:</b> This is like {@link #pingServer(String, int)} but includes the dynamic server status information.
     * @param host the hostname or IP address of the server
     * @param port the port number the server is bound to
     * @return a PingResponse instance containing server status information including the ping latency
     * @throws IOException if an error occurs connecting or communicating with the target server
     * @throws URISyntaxException if the given host and/or port number has invalid URI syntax
     * @see <a href="http://wiki.vg/Server_List_Ping">Server List Ping Protocol (http://wiki.vg/Server_List_Ping)</a>
     */
    public static PingResponse pingServerStatus(String host, int port) throws IOException, URISyntaxException {
        return doPingServerStatus(com.zonhor.core.mc.InetServerAddress.resolve(host, port));
    }

    /**
     * Retrieve information on a Minecraft server including its status and the ping latency.<p>
     * <b>Note:</b> This is like {@link #pingServer(String)} but includes the dynamic server status information.
     * @param address the address of the server
     * @return a PingResponse instance containing server status information including the ping latency
     * @throws IOException if an error occurs connecting or communicating with the target server
     * @throws URISyntaxException if the given address has invalid URI syntax
     * @see <a href="http://wiki.vg/Server_List_Ping">Server List Ping Protocol (http://wiki.vg/Server_List_Ping)</a>
     */
    public static PingResponse pingServerStatus(String address) throws IOException, URISyntaxException {
        long startTime = System.currentTimeMillis();
        com.zonhor.core.mc.InetServerAddress resolve = com.zonhor.core.mc.InetServerAddress.resolve(new Address(address));
        long endTime = System.currentTimeMillis();

        PingResponse pingResponse = doPingServerStatus(resolve);
        System.out.println("?????????");
        System.out.printf("???????????????%d ??????.", (endTime - startTime));
        System.out.println("");

        return pingResponse;
    }

    /**
     * Retrieve information on a Minecraft server including its status and the ping latency.<p>
     * <b>Note:</b> This is like {@link #pingServer(Address)} but includes the dynamic server status information.
     * @param address the address of the server
     * @return a PingResponse instance containing server status information including the ping latency
     * @throws IOException if an error occurs connecting or communicating with the target server
     * @see <a href="http://wiki.vg/Server_List_Ping">Server List Ping Protocol (http://wiki.vg/Server_List_Ping)</a>
     */
    public static PingResponse pingServerStatus(Address address) throws IOException {
        return doPingServerStatus(com.zonhor.core.mc.InetServerAddress.resolve(address));
    }

    private static PingResponse doPingServerStatus(com.zonhor.core.mc.InetServerAddress address) throws IOException {
        return executePingFunction(address, new PingFunction<PingResponse>() {
            @Override
            public PingResponse apply(Pinger pinger) throws IOException {
                return pinger.pingServerStatus();
            }
        });
    }


    /**
     * Retrieve detailed information on a Minecraft server that has querying enabled.
     * <b>Note:</b> The Query service must be explicitly enabled by the Minecraft server for this to work.
     * @param host the hostname or IP address of the server
     * @param port the port number the query service is bound to
     * @return a QueryResponse instance containing detailed server status information
     * @throws IOException if an error occurs connecting or communicating with the target server
     * @throws URISyntaxException if the given host and/or port number has invalid URI syntax
     * @see <a href="http://wiki.vg/Query">Query Protocol (http://wiki.vg/Query)</a>
     */
    public static QueryResponse queryServerStatus(String host, int port) throws IOException, URISyntaxException {
        return doQueryServerStatus(com.zonhor.core.mc.InetServerAddress.resolve(host, port));
    }

    /**
     * Retrieve detailed information on a Minecraft server that has querying enabled.
     * <b>Note:</b> The Query service must be explicitly enabled by the Minecraft server for this to work.
     * @param address the address of the server
     * @return a QueryResponse instance containing detailed server status information
     * @throws IOException if an error occurs connecting or communicating with the target server
     * @throws URISyntaxException if the given address has invalid URI syntax
     * @see <a href="http://wiki.vg/Query">Query Protocol (http://wiki.vg/Query)</a>
     */
    public static QueryResponse queryServerStatus(String address) throws IOException, URISyntaxException {
        return doQueryServerStatus(com.zonhor.core.mc.InetServerAddress.resolve(new Address(address)));
    }

    /**
     * Retrieve detailed information on a Minecraft server that has querying enabled.
     * <b>Note:</b> The Query service must be explicitly enabled by the Minecraft server for this to work.
     * @param address the address of the server
     * @return a QueryResponse instance containing detailed server status information
     * @throws IOException if an error occurs connecting or communicating with the target server
     * @see <a href="http://wiki.vg/Query">Query Protocol (http://wiki.vg/Query)</a>
     */
    public static QueryResponse queryServerStatus(Address address) throws IOException {
        return doQueryServerStatus(com.zonhor.core.mc.InetServerAddress.resolve(address));
    }

    private static QueryResponse doQueryServerStatus(com.zonhor.core.mc.InetServerAddress address) throws IOException {
        return MinecraftQuery.queryServerStatus(address, DEFAULT_TIMEOUT);
    }


    private static <T> T executePingFunction(com.zonhor.core.mc.InetServerAddress address, PingFunction<T> function) throws IOException {
        Pinger pinger = new MinecraftPinger(address, DEFAULT_TIMEOUT);

        return function.apply(pinger);
    }

    private interface PingFunction<T> {

        T apply(Pinger pinger) throws IOException;
    }

}
