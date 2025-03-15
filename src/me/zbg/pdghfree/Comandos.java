package me.zbg.pdghfree;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import net.milkbowl.vault.economy.Economy;

public class Comandos implements CommandExecutor, Listener
{
    public static Economy eco;
    public static Location spawn;
    public static Location saida;
    public static ItemStack espada;
    public static ItemStack capiroto;
    public static ItemStack capaceted;
    public static ItemStack peitorald;
    public static ItemStack calcad;
    public static ItemStack botad;
    public static ItemStack capacete;
    public static ItemStack peitoral;
    public static ItemStack calca;
    public static ItemStack bota;
    public static ItemStack pocao;
    public static ItemStack barret;
    public static ItemStack l11;
    public static ItemStack augg;
    public static ItemStack p90;
    public static ItemStack flint;
    public static ItemStack ghast;
    public static ItemStack sopa;
    public static ItemStack Maca;
    public static ItemStack pote;
    public static ItemStack aug;
    
    static {
        Comandos.eco = null;
        Comandos.spawn = new Location(Bukkit.getWorld("World"), 50.0, 50.0, 50.0);
        Comandos.saida = new Location(Bukkit.getWorld("World"), 50.0, 50.0, 50.0);
        Comandos.espada = new ItemStack(Material.DIAMOND_SWORD, 1);
        Comandos.barret = new ItemStack(Material.GOLD_AXE, 1);
        Comandos.l11 = new ItemStack(Material.IRON_AXE, 1);
        Comandos.augg = new ItemStack(Material.GOLD_RECORD, 1);
        Comandos.p90 = new ItemStack(Material.BONE, 1);
        Comandos.flint = new ItemStack(Material.FLINT, 64);
        Comandos.ghast = new ItemStack(Material.GHAST_TEAR, 64);
        Comandos.pote = new ItemStack(Material.BOWL, 1);
        Comandos.sopa = new ItemStack(Material.MUSHROOM_SOUP, 1);
        Comandos.aug = new ItemStack(Material.RECORD_9, 1);
        Comandos.capiroto = new ItemStack(Material.GOLDEN_APPLE, 4, (short)1);
        Comandos.capacete = new ItemStack(Material.DIAMOND_HELMET, 1);
        Comandos.peitoral = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        Comandos.calca = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        Comandos.bota = new ItemStack(Material.DIAMOND_BOOTS, 1);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String lb, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cComando apenas para players"));
            return false;
        }
        final Player p = (Player)sender;
        if (!p.hasPermission("pdgh.membro")) {
            p.sendMessage(Main.m.getConfig().getString("Mensagens.FREE.Sem_Permissao").replace('&', '§'));
            return true;
        }
        if (!cmd.getName().equalsIgnoreCase("free")) {
            return true;
        }
        if (args.length != 0) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("iniciar")) {
                    if (p.hasPermission("pdgh.admin")) {
                        if (!Main.free.acontecendo) {
                            Main.free.iniciar();
                        }
                        else {
                            p.sendMessage("§cO evento ja está acontecendo!");
                        }
                    }
                    else {
                        p.sendMessage(Main.m.getConfig().getString("Mensagens.FREE.Sem_Permissao").replace('&', '§'));
                    }
                    return false;
                }
                if (args[0].equalsIgnoreCase("entrar")) {
                    if (Main.free.acontecendo) {
                        if (!Main.free.participantes.contains(p)) {
                            if (Main.m.getLocs().getString("Spawn.yaw") != null || Main.m.getLocs().getString("Saida.yaw") != null) {
                                if (Main.free.anuncios >= 0) {
                                    if (Main.free.entrada) {
                                        if (p.getInventory().getBoots() != null || p.getInventory().getLeggings() != null || p.getInventory().getChestplate() != null || p.getInventory().getHelmet() != null) {
                                            p.sendMessage(Main.m.getConfig().getString("Mensagens.FREE.Esvazie_INV").replace('&', '§'));
                                            return false;
                                        }
                                        final ItemStack[] contents;
                                        if ((contents = p.getInventory().getContents()).length != 0) {
                                            final ItemStack item = contents[0];
                                            if (item == null) {
                                                Main.free.participantes.add(p);
                                                Main.free.getkills().put(p.getName().toLowerCase(), Integer.valueOf(0));
                                                p.sendMessage(Main.m.getConfig().getString("Mensagens.FREE.Entrou").replace('&', '§'));
                                                p.teleport(Comandos.spawn);
                                                p.setAllowFlight(false);
                                                p.setFlying(false);
                                                p.getInventory().addItem(new ItemStack[] {Comandos.espada });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.barret });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.l11 });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.augg });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.p90 });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.capiroto });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.pote });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.ghast });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.ghast });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.flint });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.flint });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.flint });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.flint });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.flint });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.flint });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.flint });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.flint });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.flint });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().addItem(new ItemStack[] {Comandos.sopa });
                                                p.getInventory().setHelmet(Comandos.capacete);
                                                p.getInventory().setChestplate(Comandos.peitoral);
                                                p.getInventory().setLeggings(Comandos.calca);
                                                p.getInventory().setBoots(Comandos.bota);
                                                if (Main.getSC().getClanManager().getClanPlayer(p) != null) {
                                                    Main.getSC().getClanManager().getClanPlayer(p).setFriendlyFire(true);
                                                }
                                                for (final PotionEffect effect : p.getActivePotionEffects()) {
                                                    p.removePotionEffect(effect.getType());
                                                }
                                                return false;
                                            }
                                            p.sendMessage(Main.m.getConfig().getString("Mensagens.FREE.Esvazie_INV").replace('&', '§'));
                                            return false;
                                        }
                                    }
                                    else {
                                        p.sendMessage("§cA entrada para o evento Free já foi fechada!");
                                    }
                                }
                                else {
                                    p.sendMessage(Main.m.getConfig().getString("Mensagens.FREE.Evento_Fechado").replace('&', '§'));
                                }
                            }
                            else {
                                p.sendMessage("§cA entrada ou a saida do evento Free não foi setada.");
                            }
                        }
                        else {
                            p.sendMessage(Main.m.getConfig().getString("Mensagens.FREE.Ja_Participando").replace('&', '§'));
                        }
                    }
                    else {
                        p.sendMessage(Main.m.getConfig().getString("Mensagens.FREE.Evento_Fechado").replace('&', '§'));
                    }
                    return false;
                }
                if (args[0].equalsIgnoreCase("cancelar") || args[0].equalsIgnoreCase("stop")) {
                	if (p.hasPermission("pdgh.admin")) {
                    if (Main.free.acontecendo) {
                        for (final Player acabou : Main.free.participantes) {
                            acabou.getInventory().clear();
                            acabou.getInventory().setHelmet((ItemStack)null);
                            acabou.getInventory().setChestplate((ItemStack)null);
                            acabou.getInventory().setLeggings((ItemStack)null);
                            acabou.getInventory().setBoots((ItemStack)null);
                            acabou.teleport(Comandos.saida);
                            if (Main.getSC().getClanManager().getClanPlayer(acabou) != null) {
                                Main.getSC().getClanManager().getClanPlayer(acabou).setFriendlyFire(false);
                            }
                            for (final PotionEffect effect : acabou.getActivePotionEffects()) {
                                acabou.removePotionEffect(effect.getType());
                            }
                        }
                        for (final String e : Main.m.getConfig().getStringList("Mensagens.FREE.Cancelado_Staff")) {
                            Bukkit.broadcastMessage(e.replace('&', '§'));
                        }
                        
                        Main.free.participantes.clear();
                        Main.free.entrada = false;
                        Main.free.acontecendo = false;
                        Main.free.apvp = false;
                        Main.free.comecando = 3;
                        Main.free.anuncios = Main.m.getConfig().getInt("FREE.Anuncios");
                    }
                	}
                    else {
                        p.sendMessage("§cO evento não começou!");
                    }
                    return false;
                }
                if (args[0].equalsIgnoreCase("status") || args[0].equalsIgnoreCase("info")) {
                    if (Main.free.acontecendo) {
                        for (final String e : Main.m.getConfig().getStringList("FREE.Status.Acontecendo")) {
                            p.sendMessage(e.replace('&', '§').replace((CharSequence)"@participando", (CharSequence)new StringBuilder(String.valueOf(Main.free.participantes.size())).toString()));
                        }
                        return false;
                    }
                    for (final String e : Main.m.getConfig().getStringList("FREE.Status.Nao_Acontecendo")) {
                        p.sendMessage(e.replace('&', '§').replace((CharSequence)"@ultimovencedor", (CharSequence)Main.m.getUV().getString("Ultimo_Vencedor")));
                    }
                    return false;
                }
                else {
                    if (args[0].equalsIgnoreCase("setspawn")) {
                        if (p.hasPermission("pdgh.diretor")) {
                            Main.m.getLocs().set("Spawn.x", (Object)Double.valueOf(p.getLocation().getX()));
                            Main.m.getLocs().set("Spawn.y", (Object)Double.valueOf(p.getLocation().getY()));
                            Main.m.getLocs().set("Spawn.z", (Object)Double.valueOf(p.getLocation().getZ()));
                            Main.m.getLocs().set("Spawn.yaw", (Object)Float.valueOf(p.getLocation().getYaw()));
                            Main.m.getLocs().set("Spawn.pitch", (Object)Float.valueOf(p.getLocation().getPitch()));
                            Main.m.getLocs().set("Spawn.world", (Object)p.getLocation().getWorld().getName());
                            Main.m.saveLocs();
                            final String yaw2 = Main.m.getLocs().getString("Spawn.yaw");
                            final String pitch2 = Main.m.getLocs().getString("Spawn.pitch");
                            final float yawfloat = Float.parseFloat(yaw2);
                            final float pitchfloat = Float.parseFloat(pitch2);
                            Comandos.spawn.setX(Main.m.getLocs().getDouble("Spawn.x"));
                            Comandos.spawn.setY(Main.m.getLocs().getDouble("Spawn.y"));
                            Comandos.spawn.setZ(Main.m.getLocs().getDouble("Spawn.z"));
                            Comandos.spawn.setYaw(yawfloat);
                            Comandos.spawn.setPitch(pitchfloat);
                            Comandos.spawn.setWorld(Bukkit.getServer().getWorld(Main.m.getLocs().getString("Spawn.world")));
                            p.sendMessage("§aVocê setou o spawn do evento Free!");
                        }
                        else {
                            p.sendMessage("§cVocê não tem permissão.");
                        }
                        return false;
                    }
                    if (args[0].equalsIgnoreCase("setsaida")) {
                        if (p.hasPermission("pdgh.diretor")) {
                            Main.m.getLocs().set("Saida.x", (Object)Double.valueOf(p.getLocation().getX()));
                            Main.m.getLocs().set("Saida.y", (Object)Double.valueOf(p.getLocation().getY()));
                            Main.m.getLocs().set("Saida.z", (Object)Double.valueOf(p.getLocation().getZ()));
                            Main.m.getLocs().set("Saida.yaw", (Object)Float.valueOf(p.getLocation().getYaw()));
                            Main.m.getLocs().set("Saida.pitch", (Object)Float.valueOf(p.getLocation().getPitch()));
                            Main.m.getLocs().set("Saida.world", (Object)p.getLocation().getWorld().getName());
                            Main.m.saveLocs();
                            final String yaw2 = Main.m.getLocs().getString("Saida.yaw");
                            final String pitch2 = Main.m.getLocs().getString("Saida.pitch");
                            final float yawfloat = Float.parseFloat(yaw2);
                            final float pitchfloat = Float.parseFloat(pitch2);
                            Comandos.saida.setX(Main.m.getLocs().getDouble("Saida.x"));
                            Comandos.saida.setY(Main.m.getLocs().getDouble("Saida.y"));
                            Comandos.saida.setZ(Main.m.getLocs().getDouble("Saida.z"));
                            Comandos.saida.setYaw(yawfloat);
                            Comandos.saida.setPitch(pitchfloat);
                            Comandos.saida.setWorld(Bukkit.getServer().getWorld(Main.m.getLocs().getString("Saida.world")));
                            p.sendMessage("§aVocê setou a saida do evento Free!");
                        }
                        else {
                            p.sendMessage("§cVocê não tem permissão.");
                        }
                        return false;
                    }
                    if (args[0].equalsIgnoreCase("reload")) {
                        if (p.hasPermission("pdgh.diretor")) {
                            Main.m.reloadConfig();
                            Main.m.reloadUV();
                            Main.m.reloadLocs();
                            p.sendMessage("§aConfiguração recarregada com sucesso.");
                        }
                        else {
                            p.sendMessage("§cVocê não tem permissão.");
                        }
                        return false;
                    }
                    if (args[0].equalsIgnoreCase("debug")) {
                        if (p.hasPermission("pdgh.diretor")) {
                            p.sendMessage(new StringBuilder(String.valueOf(Main.free.entrada)).toString());
                            p.sendMessage(new StringBuilder(String.valueOf(Main.free.anuncios)).toString());
                        }
                        else {
                            p.sendMessage("§cVocê não tem permissão.");
                        }
                        return false;
                    }
                    p.sendMessage(Main.m.getConfig().getString("Mensagens.FREE.Comando_nao_Encontrado").replace('&', '§'));
                }
            }
            if (args.length >= 2) {
                p.sendMessage(Main.m.getConfig().getString("Mensagens.FREE.Comando_nao_Encontrado").replace('&', '§'));
            }
            return false;
        }
        if (p.hasPermission("pdgh.diretor")) {
            for (final String e : Main.m.getConfig().getStringList("Mensagens.FREE.Admin")) {
                p.sendMessage(e.replace('&', '§'));
            }
            return false;
        }
        for (final String e : Main.m.getConfig().getStringList("Mensagens.FREE.Principal")) {
            p.sendMessage(e.replace('&', '§'));
        }
        return false;
    }
}
