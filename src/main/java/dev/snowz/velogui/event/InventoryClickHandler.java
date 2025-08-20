package dev.snowz.velogui.event;

import dev.simplix.protocolize.api.inventory.InventoryClick;

/**
 * Functional interface representing a handler for inventory click events.
 * This can be implemented to define custom behavior when an inventory is clicked.
 */
@FunctionalInterface
public interface InventoryClickHandler {

    /**
     * Handles an {@link InventoryClick} event.
     *
     * @param click The {@link InventoryClick} event to handle.
     */
    void onClick(InventoryClick click);
}
