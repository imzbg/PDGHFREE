package Eventos;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.zbg.pdghfree.Main;

public class PvP implements Listener
{
    @EventHandler
    public boolean pvp(final EntityDamageByEntityEvent e) {
        if (Main.free.acontecendo) {
            final Player p = (Player)e.getDamager();
            if (Main.free.participantes.contains(p) && e.getEntity().getType() == EntityType.PLAYER && p.getType() == EntityType.PLAYER && Main.free.comecando >= 1) {
                e.setCancelled(true);
                p.sendMessage("§cO pvp ainda não foi ativado!");
            }
        }
        return false;
    }
}
