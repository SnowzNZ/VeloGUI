package dev.snowz.velogui.builder;

import dev.simplix.protocolize.api.chat.ChatElement;
import dev.simplix.protocolize.api.inventory.Inventory;
import dev.simplix.protocolize.api.item.BaseItemStack;
import dev.simplix.protocolize.api.item.ItemStack;
import dev.simplix.protocolize.data.inventory.InventoryType;
import dev.snowz.velogui.event.GuiClickEvent;
import dev.snowz.velogui.inventory.CustomInventory;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * A builder class for creating custom inventories with structured layouts,
 * item mappings, and click event handling.
 * This class simplifies the creation and management of inventories in a
 * GUI context.
 */
public class InventoryBuilder {

    /**
     * The layout structure of the inventory, defined as rows of symbols.
     */
    private final List<String> structure;

    /**
     * A mapping between symbols and the {@link ItemStack} objects they represent.
     */
    private final Map<Character, ItemStack> ingredients;

    /**
     * A grid representing the inventory structure as symbols.
     */
    private final char[][] symbolGrid;

    /**
     * The title of the inventory.
     */
    private String title;

    /**
     * A handler for managing click events within the inventory.
     */
    private Consumer<GuiClickEvent> clickHandler;

    /**
     * Private constructor to enforce usage of the static {@link #create()} method.
     */
    private InventoryBuilder() {
        this.structure = new ArrayList<>();
        this.ingredients = new HashMap<>();
        this.symbolGrid = new char[6][9];
        this.title = "Inventory";
    }

    /**
     * Creates a new instance of {@link InventoryBuilder}.
     *
     * @return A new {@link InventoryBuilder} instance.
     */
    public static InventoryBuilder create() {
        return new InventoryBuilder();
    }

    /**
     * Sets the structure of the inventory using an array of strings.
     * Each string represents a row in the inventory and must be exactly 9 characters long.
     *
     * @param rows The structure of the inventory, defined as rows of symbols.
     * @return This {@link InventoryBuilder} instance for chaining.
     * @throws IllegalArgumentException If any row is not exactly 9 characters long.
     */
    public InventoryBuilder setStructure(String... rows) {
        structure.clear();
        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            if (row.length() != 9) {
                throw new IllegalArgumentException("Each row must be exactly 9 characters long");
            }
            structure.add(row);

            for (int j = 0; j < row.length(); j++) {
                symbolGrid[i][j] = row.charAt(j);
            }
        }
        return this;
    }

    /**
     * Sets the title of the inventory.
     *
     * @param title The title to set for the inventory.
     * @return This {@link InventoryBuilder} instance for chaining.
     */
    public InventoryBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Adds a mapping between a symbol and an {@link ItemStack}.
     *
     * @param symbol The symbol representing the item in the inventory structure.
     * @param item   The {@link ItemStack} to associate with the symbol.
     * @return This {@link InventoryBuilder} instance for chaining.
     */
    public InventoryBuilder addIngredient(char symbol, ItemStack item) {
        ingredients.put(symbol, item);
        return this;
    }

    /**
     * Sets the click handler for this inventory.
     *
     * @param handler A {@link Consumer} to handle {@link GuiClickEvent} events.
     * @return This {@link InventoryBuilder} instance for chaining.
     */
    public InventoryBuilder onClick(Consumer<GuiClickEvent> handler) {
        this.clickHandler = handler;
        return this;
    }

    /**
     * Builds the custom inventory based on the current configuration.
     *
     * @return A {@link CustomInventory} instance representing the configured inventory.
     * @throws IllegalStateException If the structure has not been set.
     */
    public CustomInventory build() {
        validateStructure();

        int rows = structure.size();
        Inventory inventory = new Inventory(InventoryType.chestInventoryWithRows(rows));
        inventory.title(ChatElement.of(LegacyComponentSerializer.legacyAmpersand().deserialize(title)));

        for (int row = 0; row < structure.size(); row++) {
            String rowPattern = structure.get(row);
            for (int col = 0; col < rowPattern.length(); col++) {
                char symbol = rowPattern.charAt(col);
                int slot = row * 9 + col;

                ItemStack item = ingredients.get(symbol);
                if (item != null) {
                    inventory.item(slot, item.deepClone());
                }
            }
        }

        if (clickHandler != null) {
            inventory.onClick(click -> {
                int slot = click.slot();
                int row = slot / 9;
                int col = slot % 9;

                if (row < symbolGrid.length && col < symbolGrid[row].length) {
                    char symbol = symbolGrid[row][col];
                    BaseItemStack clickedItem = inventory.item(slot);
                    GuiClickEvent event = new GuiClickEvent(click, symbol, clickedItem);
                    clickHandler.accept(event);
                }
            });
        }

        return new CustomInventory(inventory);
    }

    /**
     * Validates that the structure of the inventory has been set.
     *
     * @throws IllegalStateException If the structure is empty or not set.
     */
    private void validateStructure() {
        if (structure.isEmpty()) {
            throw new IllegalStateException("Structure must be set before building");
        }
    }
}
