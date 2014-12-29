package net.nekonekoserver.nekoneko.nc.jump;

import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NCJump extends JavaPlugin implements Listener {
    public final Logger logger = Logger.getLogger("Minecraft");
    public PluginManager plm = getServer().getPluginManager();

    @Override
    public void onEnable() {
        super.onEnable();
        this.saveDefaultConfig();
        this.reloadConfig();
        plm.registerEvents(this, this);        
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        PluginDescriptionFile pdfFile = getDescription();
    } 
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ncjump")) {
            if (args.length == 0) {
                sender.sendMessage("/ncjump reload");
                return true;
            }
            if (cmd.getName().equalsIgnoreCase("reload")) {
                this.reloadConfig();
                sender.sendMessage("Configを再読込しました");
                return true;
            }
        }
        return false;
    }
    
    @EventHandler
    public void onJump(final PlayerMoveEvent event) {
        Player p = event.getPlayer();
        Location block = event.getPlayer().getLocation();
        int ConfigHeight = this.getConfig().getInt("Height");
        int ConfigLenght = this.getConfig().getInt("Lenght");
        if (block.getBlock().getType() == Material.STONE_PLATE) {
            double Height = ConfigHeight / 15.0D;
            double Lenght = ConfigLenght / 8.0D;
            p.setVelocity(p.getLocation().getDirection().setY(Height).multiply(Lenght));
        }
    }
}
