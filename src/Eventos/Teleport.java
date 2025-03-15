package Eventos;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import me.zbg.pdghfree.Main;

public class Teleport implements Listener
{
    @EventHandler
    public boolean aoTeleportar(final PlayerTeleportEvent e) {
        if (Main.free.acontecendo && Main.free.participantes.contains(e.getPlayer()) && Main.free.comecando == 0 && Main.free.participantes.size() >= 2) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cVocê não pode se teleportar no evento Free!");
        }
        return false;
    }
}
