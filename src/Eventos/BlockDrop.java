package Eventos;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.zbg.pdghfree.Main;

public class BlockDrop implements Listener
{
    @EventHandler
    public void aoDropar(final PlayerDropItemEvent e) {
        if (Main.free.acontecendo && Main.free.participantes.contains(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cVocê não pode dropar itens do evento Free!");
        }
    }
}
