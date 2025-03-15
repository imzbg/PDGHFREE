package Eventos;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import Hooks.VaultHook;
import me.zbg.pdghfree.Comandos;
import me.zbg.pdghfree.Main;

public class PlayerDeath implements Listener
{
    @EventHandler
    public boolean aoMorrer(final PlayerDeathEvent e) {
        final Player p = e.getEntity().getPlayer();
        final Player killer = e.getEntity().getKiller();
        if (Main.free.acontecendo && Main.free.participantes.contains(p)) {
            Main.free.participantes.remove(p);
            p.getInventory().clear();
            p.getInventory().setBoots((ItemStack)null);
            p.getInventory().setLeggings((ItemStack)null);
            p.getInventory().setChestplate((ItemStack)null);
            p.getInventory().setHelmet((ItemStack)null);
            if (Main.getSC().getClanManager().getClanPlayer(p) != null) {
                Main.getSC().getClanManager().getClanPlayer(p).setFriendlyFire(false);
            }
            p.sendMessage(Main.m.getConfig().getString("Mensagens.FREE.Morreu").replace('&', '§'));
            final int kills = Integer.valueOf(Main.free.getkills().get((Object)killer.getName().toLowerCase())) + 1;
            Bukkit.broadcastMessage(Main.m.getConfig().getString("Mensagens.FREE.Morte").replace('&', '§').replace("@matou", e.getEntity().getKiller().getName()).replace("@morreu", p.getName()).replace((CharSequence)"@kills", (CharSequence)new StringBuilder(String.valueOf(kills)).toString()));
            Main.free.getkills().put(killer.getName().toLowerCase(), Integer.valueOf(kills));
            final List<ItemStack> list = (List<ItemStack>)e.getDrops();
            final Iterator<ItemStack> drops = list.iterator();
            while (drops.hasNext()) {
                final ItemStack item = (ItemStack)drops.next();
                drops.remove();
            }
            if (Main.free.participantes.size() == 1) {
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
                    Main.m.getUV().set("Ultimo_Vencedor", (Object)vencedor.getName());
                    Main.m.saveUV();
                    for (final PotionEffect effect : vencedor.getActivePotionEffects()) {
                        vencedor.removePotionEffect(effect.getType());
                    }
                    VaultHook.eco.depositPlayer((OfflinePlayer)vencedor, (double)Main.m.getConfig().getInt("FREE.Premio"));
                    for (final String venc : Main.m.getConfig().getStringList("Mensagens.FREE.Vencedor")) {
                        Bukkit.broadcastMessage(venc.replace('&', '§').replace("@vencedor", vencedor.getName()).replace((CharSequence)"@premio", (CharSequence)Main.m.getConfig().getString("FREE.Premio")));
                    }
                    Main.free.anuncios = Main.m.getConfig().getInt("FREE.Anuncios");
                    Main.free.participantes.clear();
                    Main.free.acontecendo = false;
                    Main.free.apvp = false;
                    Main.free.comecando = 3;
                }
            }
            for (final String morreu : Main.m.getConfig().getStringList("FREE.Status.Restam")) {
                Bukkit.broadcastMessage(morreu.replace('&', '§').replace((CharSequence)"@jogadores", (CharSequence)new StringBuilder(String.valueOf(Main.free.participantes.size())).toString()));
            }
        }
        return false;
    }
}
