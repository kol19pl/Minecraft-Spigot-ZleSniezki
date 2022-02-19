package kol19pl.zlesniezki;

import org.bukkit.plugin.java.JavaPlugin;
import com.google.gson.internal.$Gson$Types;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public final class Zlesniezki extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        ListaEnchantuw.register();
        this.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Złe śnieżki znowu rozrabiają");


    }


    @Override
    public void onDisable() {

        getLogger().info("Złe śnieżki odłożono do kuferka");
        // shutdown
        // reloads
        // p reload
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("WUJ")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                //player.sendMessage("Hummmmmmm!!!");
                Ulepsz(player);

                return true;
            } else {
                sender.sendMessage("Komenda dostempna tylko w grze!");
                return true;
            }


        }
        return false;


    }

    public void Ulepsz(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.equals(new ItemStack(Material.SNOWBALL))) {
            int playerLevel = player.getLevel();
            //getLogger().info("Gracz ma" + playerLevel);
            if (playerLevel >= 10 || player.isOp() ) {
                item.addUnsafeEnchantment(ListaEnchantuw.SNIEZKOWAKLONTFA, 1);
                ItemMeta meta = item.getItemMeta();
                List<String> lore = new ArrayList<String>();
                lore.add(ChatColor.GOLD + "Przeklęta śnieżka");
                assert meta != null;
                meta.setLore(lore);
                item.setItemMeta(meta);
                player.getInventory().removeItem(item);
                player.getInventory().addItem(item);
                getLogger().info(player.getName() + "  Użył mrocznej magi śnieżnej!!");
                if(!player.isOp())
                {
                    player.setLevel(playerLevel - 10);
                }
            }
            if (playerLevel < 10)
            {
                player.sendMessage("Aby użyć tego zaklęcia potrzebujesz 10 Levelu a masz "+ playerLevel+ " !!!!!");
            }


        }
        else player.sendMessage("Można zaklonć tylko pojedyńcze śnieżki!!!!!");
    }


    @EventHandler
    public void onProjectileLaunchEvent(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof Snowball) {
            Player player = (Player) event.getEntity().getShooter();
            assert player != null;
            ItemStack item = player.getInventory().getItemInMainHand();
            if (Objects.requireNonNull(item.getItemMeta()).hasEnchant(ListaEnchantuw.SNIEZKOWAKLONTFA))
            {
                event.getEntity().setCustomName("Przeklęta śnieżka");
                event.getEntity().setCustomNameVisible(true);
            }
            // getLogger().info("Złe śnieżka leci");

        }
    }


    //
    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent event) {

        if (event.getEntity() instanceof Snowball) {
            if (Objects.equals(event.getEntity().getCustomName(), "Przeklęta śnieżka")) {

                // String daneSniezki = event.getEntity().getCustomName();
                // getLogger().info(daneSniezki);

                World world = event.getEntity().getWorld();
                if (event.getHitBlock() != null) {
                    Location loc = event.getHitBlock().getLocation();
                    world.strikeLightning(loc);
                }
                if (event.getHitEntity() != null) {
                    Location loc = event.getHitEntity().getLocation();
                    world.strikeLightning(loc);
                }
                //Kulka sniegu żucona
            }
            //  event.setJoinMessage("Witaj");

            //player.sendRawMessage("Test")
        }
    }
}
