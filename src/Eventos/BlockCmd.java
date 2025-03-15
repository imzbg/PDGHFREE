package Eventos;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.zbg.pdghfree.Main;

public class BlockCmd implements Listener
{
    @EventHandler
    public boolean onCommand(final PlayerCommandPreprocessEvent e) {
        if (Main.free.acontecendo && Main.free.participantes.contains(e.getPlayer()) && !e.getPlayer().hasPermission("pdgh.admin")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cVocê não pode usar comandos no evento Free!");
        }
        return false;
    }
}
