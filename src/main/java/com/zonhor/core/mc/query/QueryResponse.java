package com.zonhor.core.mc.query;

import com.zonhor.core.mc.MinecraftServer;

import java.util.Collections;
import java.util.List;

/**
 * An extension of {@link MinecraftServer} that includes extra server information and
 * extra player data relating to the current status of the server.
 */
public class QueryResponse extends MinecraftServer {

    private final String mapName;
    private final String serverType;
    private final List<String> plugins;

    public QueryResponse(Address address, Description description, PlayersList players, Version version, String mapName,
            String serverType, List<String> plugins) {
        super(address, description, players, version);
        this.mapName = mapName;
        this.serverType = serverType;
        this.plugins = plugins;
    }


    @Override
    public Players getPlayers() {
        return super.getPlayers();
    }

    /**
     * @return <code>null</code>
     */
    @Override
    public String getFavicon() {
        return super.getFavicon();
    }

    /**
     * @return the name of the server's default world
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * @return the type of the server which is usually the software name and version
     */
    public String getServerType() {
        return serverType;
    }

    /**
     * @return an unmodifiable list of plugins installed on the server
     */
    public List<String> getPlugins() {
        return Collections.unmodifiableList(plugins);
    }


    @Override
    public String toString() {
        return "QueryResponse{address: " + getAddress() + ", players: " + getPlayers() + ", version: " + getVersion() + ", description: " +
                getDescription() + ", mapName: " + getMapName() + ", serverType: " + getServerType() + ", plugins: " + getPlugins() + "}";
    }

    /**
     * An extension of {@link Players} that includes the current online player count
     * and a complete list of online player names.
     */
    public static class PlayersList extends Players {

        private final int count;
        private final List<String> list;

        /**
         * @param max the maximum amount of players
         * @param count the current player count
         * @param list a list of online player names
         */
        public PlayersList(int max, int count, List<String> list) throws IllegalArgumentException {
            super(max);
            this.count = count;
            this.list = (list != null ? list : Collections.<String>emptyList());
        }

        /**
         * @return the player count
         */
        public int getCount() {
            return count;
        }

        /**
         * @return an unmodifiable list of online player names
         */
        public List<String> getList() {
            return Collections.unmodifiableList(list);
        }

        @Override
        public String toString() {
            return getCount() + "/" + getMax();
        }

    }

}
