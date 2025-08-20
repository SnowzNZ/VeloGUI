package dev.snowz.velogui.event;

import com.velocitypowered.api.proxy.Player;
import dev.simplix.protocolize.api.inventory.InventoryClick;
import dev.simplix.protocolize.api.item.BaseItemStack;
import dev.snowz.velogui.VeloGUI;

/**
 * Represents a click event within a GUI inventory.
 * Provides information about the clicked item, the player who clicked, and the symbol
 * representing the clicked slot in the inventory structure.
 */
public class GuiClickEvent {

    /**
     * The original {@link InventoryClick} event that triggered this GUI event.
     */
    private final InventoryClick originalClick;

    /**
     * The symbol associated with the clicked slot in the inventory structure.
     */
    private final char symbol;

    /**
     * The {@link BaseItemStack} that was clicked in the inventory.
     */
    private final BaseItemStack clickedItem;

    /**
     * Constructs a new {@link GuiClickEvent}.
     *
     * @param click       The original {@link InventoryClick} event.
     * @param symbol      The symbol associated with the clicked slot.
     * @param clickedItem The item that was clicked.
     */
    public GuiClickEvent(InventoryClick click, char symbol, BaseItemStack clickedItem) {
        this.originalClick = click;
        this.symbol = symbol;
        this.clickedItem = clickedItem;
    }

    /**
     * Gets the symbol representing the clicked slot in the inventory structure.
     *
     * @return The symbol for the clicked slot.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Gets the slot index of the clicked item.
     *
     * @return The slot index of the clicked item.
     */
    public int getSlot() {
        return originalClick.slot();
    }

    /**
     * Gets the {@link Player} who performed the click.
     *
     * @return The {@link Player} who clicked.
     * @throws IllegalStateException If the player cannot be found.
     */
    public Player getPlayer() {
        return VeloGUI.getServer()
            .getPlayer(originalClick.player().uniqueId())
            .orElseThrow(() -> new IllegalStateException("Player not found!"));
    }

    /**
     * Gets the {@link BaseItemStack} that was clicked.
     *
     * @return The clicked item.
     */
    public BaseItemStack getClickedItem() {
        return clickedItem;
    }

    /**
     * Sets whether the click event should be cancelled.
     *
     * @param cancelled {@code true} to cancel the event, {@code false} to allow it.
     */
    public void setCancelled(boolean cancelled) {
        originalClick.cancelled(cancelled);
    }
}
