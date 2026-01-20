/*
 * This file is part of SilkTouchAmethyst, licensed under the MIT License.
 *
 *  Copyright (c) firewolf8385
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package com.github.firewolf8385.silktouchamethyst;

import org.bstats.bukkit.Metrics;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class SilkTouchAmethystPlugin extends JavaPlugin implements Listener {

    /**
     * Runs when the plugin enables.
     * Sets up metrics tracking and registers the event listener.
     */
    @Override
    public void onEnable() {
        int pluginId = 28945;
        Metrics metrics = new Metrics(this, pluginId);

        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    /**
     * Runs when a player breaks a block.
     * @param event BlockBreakEvent.
     */
    @EventHandler
    public void onBreak(@NotNull final BlockBreakEvent event) {
        final Player player = event.getPlayer();

        // Makes sure the player is not in creative mode.
        if(player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        // Makes sure the block is a Budding Amethyst.
        if(event.getBlock().getType() != Material.BUDDING_AMETHYST) {
            return;
        }

        final ItemStack pickaxe = player.getInventory().getItemInMainHand();

        // Makes sure the pickaxe being used has item meta.
        if(pickaxe.getItemMeta() == null) {
            return;
        }

        // Make sure the pickaxe has silk touch.
        if(!pickaxe.getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
            return;
        }

        // Drops the Budding Amethyst.
        event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.BUDDING_AMETHYST));
    }
}