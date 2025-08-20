package dev.snowz.velogui.example;

import com.velocitypowered.api.proxy.Player;
import dev.simplix.protocolize.data.ItemType;
import dev.snowz.velogui.builder.InventoryBuilder;
import dev.snowz.velogui.builder.ItemBuilder;
import dev.snowz.velogui.inventory.CustomInventory;
import net.kyori.adventure.text.Component;

public class ExampleInventory {
    public void createAndOpenMenu(Player player) {
        CustomInventory inventory = InventoryBuilder.create()
            .setTitle("My Cool Menu")
            .setStructure(
                "# # # # # # # # #",
                "# i . . p . . i #",
                "# . . . . . . . #",
                "# # # # # # # # #"
            )
            .addIngredient(
                '#', new ItemBuilder(ItemType.BLACK_STAINED_GLASS_PANE)
                    .name("&r")
                    .hideFlags()
                    .build()
            )
            .addIngredient(
                'i', new ItemBuilder(ItemType.DIAMOND)
                    .name("&bSpecial Item")
                    .lore(
                        "&7Click to do something cool!",
                        "&7Another line of lore"
                    )
                    .build()
            )
            .addIngredient(
                'p', new ItemBuilder(ItemType.PLAYER_HEAD)
                    .name("&aYour Profile")
                    .build()
            )
            .onClick(click -> {
                click.setCancelled(true);

                switch (click.getSymbol()) {
                    case 'i' -> handleSpecialItem(click.getPlayer());
                    case 'p' -> openProfile(click.getPlayer());
                }
            })
            .build();

        inventory.open(player);
    }

    private void handleSpecialItem(Player player) {
        player.sendMessage(Component.text("You got a special item!"));
    }

    private void openProfile(Player player) {
        player.sendMessage(Component.text("Opening profile..."));
    }
}
