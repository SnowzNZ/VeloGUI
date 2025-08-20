package dev.snowz.velogui.inventory;

import com.velocitypowered.api.proxy.Player;
import dev.simplix.protocolize.api.Protocolize;

/**
 * Represents a custom inventory that can be opened for a player.
 * This wraps around a {@link dev.simplix.protocolize.api.inventory.Inventory} instance
 * to provide additional functionality.
 */
public class CustomInventory {

    /**
     * The underlying {@link dev.simplix.protocolize.api.inventory.Inventory} instance.
     */
    private final dev.simplix.protocolize.api.inventory.Inventory inventory;

    /**
     * Constructs a new {@link CustomInventory} instance.
     *
     * @param inventory The underlying inventory to wrap.
     */
    public CustomInventory(dev.simplix.protocolize.api.inventory.Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Opens the inventory for the specified player.
     *
     * @param player The player for whom to open the inventory.
     */
    public void open(Player player) {
        Protocolize.playerProvider().player(player.getUniqueId()).openInventory(inventory);
    }

    /**
     * Gets the underlying {@link dev.simplix.protocolize.api.inventory.Inventory} instance.
     *
     * @return The underlying inventory.
     */
    public dev.simplix.protocolize.api.inventory.Inventory getHandle() {
        return inventory;
    }
}
