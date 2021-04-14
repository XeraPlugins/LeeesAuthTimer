package Leees.Auth.Timer;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    private final PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskTimer( this, new Runnable() {
            int time = Bukkit.getPluginManager().getPlugin("LeeesAuthTimer").getConfig().getInt("Config.CounterStartTime");

            @Override
            public void run() {
                if (this.time == 0) {
                    event.getPlayer().sendMessage("Took to long to login");
                }
                this.time--;
                player.sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', Bukkit.getPluginManager().getPlugin("LeeesAuthTimer").getConfig().getString("Config.HotBarMessage").replace("%timeleft%", "" + this.time))));
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100.0F, 1.0F);
            }
        }, 0L, 20L);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100.0F, 1.0F);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
