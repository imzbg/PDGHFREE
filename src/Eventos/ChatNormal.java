package Eventos;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.player.AsyncPlayerChatEvent;
 import me.zbg.pdghfree.Main;

public class ChatNormal implements Listener {
  @EventHandler
   public boolean onChat(AsyncPlayerChatEvent e) {
    if (Main.free.acontecendo && 
       !Main.m.getConfig().getBoolean("FREE.Chat") && 
      Main.free.participantes.contains(e.getPlayer())) {
      e.setCancelled(true);
       e.getPlayer().sendMessage("§cChat bloqueado no evento FREE!");
	} 
		return false;
	}
}