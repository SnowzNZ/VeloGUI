package dev.snowz.velogui;

import com.velocitypowered.api.proxy.ProxyServer;

/**
 * A utility class for initializing and accessing the Velocity proxy server instance.
 * This class provides a centralized mechanism to store and retrieve the {@link ProxyServer} instance
 * for use in other parts of the application.
 */
public class VeloGUI {

    /**
     * The stored {@link ProxyServer} instance.
     */
    private static ProxyServer server;

    /**
     * Initializes the {@link VeloGUI} utility with the specified {@link ProxyServer} instance.
     *
     * @param proxyServer The {@link ProxyServer} instance to initialize with.
     */
    public static void init(ProxyServer proxyServer) {
        server = proxyServer;
    }

    /**
     * Retrieves the initialized {@link ProxyServer} instance.
     *
     * @return The {@link ProxyServer} instance.
     * @throws IllegalStateException If the {@link VeloGUI} has not been initialized.
     */
    public static ProxyServer getServer() {
        if (server == null) {
            throw new IllegalStateException("VeloGui has not been initialized! Call VeloGui.init(server) first!");
        }
        return server;
    }
}
