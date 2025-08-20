package dev.snowz.velogui.builder;

import dev.simplix.protocolize.api.chat.ChatElement;
import dev.simplix.protocolize.api.item.ItemFlag;
import dev.simplix.protocolize.api.item.ItemStack;
import dev.simplix.protocolize.data.ItemType;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A builder class for creating and customizing {@link ItemStack} instances
 * with properties such as name, lore, amount, and item flags.
 * This class provides a convenient and fluent API for item customization.
 */
public class ItemBuilder {

    /**
     * The {@link ItemStack} being built and customized.
     */
    private final ItemStack item;

    /**
     * The lore of the item, represented as a list of strings.
     */
    private final List<String> lore;

    /**
     * Constructs a new {@link ItemBuilder} for a given {@link ItemType}.
     *
     * @param type The type of item to initialize the {@link ItemStack} with.
     */
    public ItemBuilder(ItemType type) {
        this.item = new ItemStack(type);
        this.lore = new ArrayList<>();
    }

    /**
     * Sets the display name of the item.
     *
     * @param name The display name to set, in legacy text format.
     * @return This {@link ItemBuilder} instance for chaining.
     */
    public ItemBuilder name(String name) {
        item.displayName(ChatElement.of(LegacyComponentSerializer.legacyAmpersand().deserialize(name)));
        return this;
    }

    /**
     * Adds lines of lore to the item.
     * Each line is converted to a {@link ChatElement} in legacy text format.
     *
     * @param lines The lines of lore to add.
     * @return This {@link ItemBuilder} instance for chaining.
     */
    @SuppressWarnings("unchecked")
    public ItemBuilder lore(String... lines) {
        lore.addAll(Arrays.asList(lines));
        item.lore((List<ChatElement<?>>) (List<?>) lore.stream().map(line -> ChatElement.of(LegacyComponentSerializer.legacyAmpersand().deserialize(
            line))).toList());
        return this;
    }

    /**
     * Sets the amount of items in the stack.
     *
     * @param amount The amount of items to set.
     * @return This {@link ItemBuilder} instance for chaining.
     */
    public ItemBuilder amount(int amount) {
        item.amount((byte) amount);
        return this;
    }

    /**
     * Hides specific item attributes by applying the {@link ItemFlag#HIDE_ATTRIBUTES}
     * and {@link ItemFlag#HIDE_UNBREAKABLE} flags.
     *
     * @return This {@link ItemBuilder} instance for chaining.
     */
    public ItemBuilder hideFlags() {
        item.flagSet(ItemFlag.HIDE_ATTRIBUTES);
        item.flagSet(ItemFlag.HIDE_UNBREAKABLE);
        return this;
    }

    /**
     * Builds and returns the customized {@link ItemStack}.
     *
     * @return The built {@link ItemStack} instance.
     */
    public ItemStack build() {
        return item;
    }
}
