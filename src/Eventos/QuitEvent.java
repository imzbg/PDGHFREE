package Eventos;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import Hooks.VaultHook;
import me.zbg.pdghfree.Comandos;
import me.zbg.pdghfree.Main;

public class QuitEvent implements Listener
{
    @EventHandler
    public boolean aoQuitar(final PlayerQuitEvent e) {
        if (Main.free.acontecendo && Main.free.participantes.contains(e.getPlayer())) {
            final Player p = e.getPlayer();
            Main.free.participantes.remove(p);
            p.getInventory().clear();
            p.getInventory().setHelmet((ItemStack)null);
            p.getInventory().setChestplate((ItemStack)null);
            p.getInventory().setLeggings((ItemStack)null);
            p.getInventory().setBoots((ItemStack)null);
            if (Main.getSC().getClanManager().getClanPlayer(p) != null) {
                Main.getSC().getClanManager().getClanPlayer(p).setFriendlyFire(false);
            }
            for (final PotionEffect effect : p.getActivePotionEffects()) {
                p.removePotionEffect(effect.getType());
            }
            if (Main.free.participantes.size() == 1 && Main.free.anuncios == 0) {
                for (final Player vencedor : Main.free.participantes) {
                    vencedor.teleport(Comandos.saida);
                    vencedor.getInventory().clear();
                    vencedor.getInventory().setHelmet((ItemStack)null);
                    vencedor.getInventory().setChestplate((ItemStack)null);
                    vencedor.getInventory().setLeggings((ItemStack)null);
                    vencedor.getInventory().setBoots((ItemStack)null);
                    if (Main.getSC().getClanManager().getClanPlayer(vencedor) != null) {
                        Main.getSC().getClanManager().getClanPlayer(vencedor).setFriendlyFire(false);
                    }
                    Main.m.getUV().set("Ultimo_Vencedor", (Object)new StringBuilder(String.valueOf(vencedor.getName())).toString());
                    Main.m.saveUV();
                    for (final PotionEffect effect2 : vencedor.getActivePotionEffects()) {
                        vencedor.removePotionEffect(effect2.getType());
                    }
                    VaultHook.eco.depositPlayer((OfflinePlayer)vencedor, (double)Main.m.getConfig().getInt("FREE.Premio"));
                    for (final String venc : Main.m.getConfig().getStringList("Mensagens.FREE.Vencedor")) {
                        Bukkit.broadcastMessage(venc.replace('&', '§').replace("@vencedor", new StringBuilder(String.valueOf(vencedor.getName())).toString()).replace((CharSequence)"@premio", (CharSequence)Main.m.getConfig().getString("FREE.Premio")));
                    }
                    Main.free.entrada = false;
                    Main.free.anuncios = Main.m.getConfig().getInt("FREE.Anuncios");
                    Main.free.participantes.clear();
                    Main.free.acontecendo = false;
                    Main.free.apvp = false;
                    Main.free.comecando = 3;
                }
            }
        }
        return false;
    }
}
