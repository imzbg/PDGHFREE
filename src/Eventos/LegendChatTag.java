package Eventos;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import me.zbg.pdghfree.Main;

public class LegendChatTag implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    private void onChat(final ChatMessageEvent e) {
        if (!Main.free.acontecendo) {
            if (Main.m.getConfig().getBoolean("FREE.Ativar_Tag")) {
                final Player p = e.getSender();
                if (e.getTags().contains("pdghfree") && p.getName().equalsIgnoreCase(Main.m.getUV().getString("Ultimo_Vencedor"))) {
                    e.setTagValue("pdghfree", String.valueOf(Main.m.getConfig().getString("FREE.Tag").replace('&', '§')) + ChatColor.RESET);
                }
            }
        }
        else if (!Main.m.getConfig().getBoolean("FREE.Chat") && Main.free.participantes.contains(e.getSender())) {
            e.setCancelled(true);
            e.getSender().sendMessage("§cChat bloqueado no evento Mata-Mata!");
        }
    }
}
