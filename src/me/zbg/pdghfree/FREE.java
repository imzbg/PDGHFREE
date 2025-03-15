package me.zbg.pdghfree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

public class FREE
{
    public List<Player> participantes;
    private HashMap<String, Integer> killsplayer;
    public int premio;
    public int anuncios;
    public int comecando;
    public boolean acontecendo;
    public boolean apvp;
    public boolean entrada;
    World pre;
    
    public FREE() {
        this.participantes = new ArrayList<Player>();
        this.premio = Main.m.getConfig().getInt("FREE.Premio");
        this.anuncios = Main.m.getConfig().getInt("FREE.Anuncios");
        this.comecando = 3;
        this.acontecendo = false;
        this.apvp = false;
        this.entrada = false;
        this.pre = Bukkit.getWorld("world");
    }
    
    public void iniciar() {
        this.participantes.clear();
        this.killsplayer = new HashMap<String, Integer>();
        this.apvp = true;
        this.acontecendo = true;
        this.entrada = true;
        this.premio = Main.m.getConfig().getInt("FREE.Premio");
        this.anuncios = Main.m.getConfig().getInt("FREE.Anuncios");
        new BukkitRunnable() {
            public void run() {
                if (!FREE.this.acontecendo) {
                    this.cancel();
                }
                else if (FREE.this.anuncios > 0) {
                    if (FREE.this.acontecendo) {
                        for (final String e : Main.m.getConfig().getStringList("Mensagens.FREE.Anuncios_Comecando")) {
                            Bukkit.broadcastMessage(e.replace("@anuncios", new StringBuilder(String.valueOf(FREE.this.anuncios - 1)).toString()).replace('&', '§').replace("@participando", new StringBuilder(String.valueOf(Main.free.participantes.size())).toString()).replace("@tag", Main.m.getConfig().getString("FREE.Tag").replace('&', '§')).replace((CharSequence)"@premio", (CharSequence)Main.m.getConfig().getString("FREE.Premio")));
                        }
                        final FREE this$0 = FREE.this;
                        --this$0.anuncios;
                    }
                    else {
                        this.cancel();
                    }
                }
                else {
                    Main.free.entrada = false;
                    if (FREE.this.participantes.size() >= 2) {
                        new BukkitRunnable() {
                            public void run() {
                                if (FREE.this.apvp) {
                                    if (FREE.this.comecando == 1) {
                                        for (final String e : Main.m.getConfig().getStringList("Mensagens.FREE.PvP_Ativado")) {
                                            Bukkit.broadcastMessage(e.replace('&', '§').replace((CharSequence)"@participando", (CharSequence)new StringBuilder(String.valueOf(Main.free.participantes.size())).toString()));
                                        }
                                        Main.free.comecando = 0;
                                    }
                                    else if (FREE.this.comecando > 0) {
                                        for (final String e : Main.m.getConfig().getStringList("Mensagens.FREE.Ativando_PvP")) {
                                            Bukkit.broadcastMessage(e.replace('&', '§').replace("@segundos", new StringBuilder(String.valueOf((FREE.this.comecando - 1) * 100 / 20)).toString()).replace((CharSequence)"@participando", (CharSequence)new StringBuilder(String.valueOf(Main.free.participantes.size())).toString()));
                                        }
                                        final FREE access$0 = FREE.this;
                                        --access$0.comecando;
                                    }
                                    else {
                                        this.cancel();
                                    }
                                }
                                else {
                                    this.cancel();
                                }
                            }
                        }.runTaskTimer((Plugin)Main.m, 0L, 100L);
                    }
                    else {
                        for (final String e : Main.m.getConfig().getStringList("Mensagens.FREE.Faltou_Jogadores")) {
                            Bukkit.broadcastMessage(e.replace('&', '§'));
                        }
                        for (final Player p : FREE.this.participantes) {
                            p.getInventory().clear();
                            p.getInventory().setHelmet((ItemStack)null);
                            p.getInventory().setChestplate((ItemStack)null);
                            p.getInventory().setLeggings((ItemStack)null);
                            p.getInventory().setBoots((ItemStack)null);
                            p.teleport(Comandos.saida);
                            if (Main.getSC().getClanManager().getClanPlayer(p) != null) {
                                Main.getSC().getClanManager().getClanPlayer(p).setFriendlyFire(false);
                            }
                            for (final PotionEffect effect : p.getActivePotionEffects()) {
                                p.removePotionEffect(effect.getType());
                            }
                        }
                        Main.free.entrada = false;
                        FREE.this.anuncios = Main.m.getConfig().getInt("FREE.Anuncios");
                        FREE.this.comecando = 3;
                        FREE.this.participantes.clear();
                        FREE.this.acontecendo = false;
                        FREE.this.apvp = false;
                    }
                }
            }
        }.runTaskTimer((Plugin)Main.m, 0L, 200L);
    }
    
    public HashMap<String, Integer> getkills() {
        return this.killsplayer;
    }
    
    public void setpKills(final HashMap<String, Integer> pkills) {
        this.killsplayer = pkills;
    }
}
