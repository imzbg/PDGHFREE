package me.zbg.pdghfree;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import Eventos.BlockCmd;
import Eventos.BlockDrop;
import Eventos.ChatNormal;
import Eventos.LegendChatTag;
import Eventos.PlayerDeath;
import Eventos.PvP;
import Eventos.QuitEvent;
import Eventos.Teleport;
import Hooks.LegendChatHook;
import Hooks.SimpleClansHook;
import Hooks.VaultHook;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

public class Main extends JavaPlugin
{
    public boolean update;
    public boolean sc;
    public static Main m;
    public static FREE free;
    private VaultHook vaulthook;
    private LegendChatHook lcHook;
    private SimpleClansHook scHook;
    private File filelocs;
    private FileConfiguration fileConfigurationlocs;
    private File fileUv;
    private FileConfiguration fileConfigurationUv;
    public String prefix;
    
    public Main() {
        this.update = false;
        this.filelocs = null;
        this.fileConfigurationlocs = null;
        this.fileUv = null;
        this.fileConfigurationUv = null;
        this.prefix = String.valueOf(String.valueOf(new StringBuilder().append(ChatColor.YELLOW).append("[").append(this.getDescription().getName()).toString())) + " " + this.getDescription().getVersion() + "] ";
    }
    
    public static SimpleClans getSC() {
        return SimpleClans.getInstance();
    }
    
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerDeath(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new QuitEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new PvP(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new Teleport(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new BlockCmd(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new BlockDrop(), (Plugin)this);
        if (this.getServer().getPluginManager().getPlugin("Vault") != null) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(this.prefix) + "§aVault encontrado!");
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(this.prefix) + "§cVault nao encontrado! §4DESABILITANDO plugin.");
            Bukkit.getPluginManager().disablePlugin((Plugin)this);
        }
        if (this.getServer().getPluginManager().getPlugin("Legendchat") != null) {
            this.getServer().getPluginManager().registerEvents((Listener)new LegendChatTag(), (Plugin)this);
            Bukkit.getConsoleSender().sendMessage(String.valueOf(this.prefix) + "§aLegendChat encontrado!");
        }
        else {
            Bukkit.getPluginManager().registerEvents((Listener)new ChatNormal(), (Plugin)this);
            Bukkit.getConsoleSender().sendMessage(String.valueOf(this.prefix) + "§cLegendChat nao encontrado!");
        }
        if (this.getServer().getPluginManager().getPlugin("SimpleClans") != null) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(this.prefix) + "§aSimpleClans encontrado!");
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(this.prefix) + "§cSimpleClans nao encontrado!");
        }
        final File verificarLocs = new File(this.getDataFolder(), "locs.yml");
        if (!verificarLocs.exists()) {
            this.saveResource("locs.yml", false);
        }
        final File verificarUV = new File(this.getDataFolder(), "ultimovencedor.yml");
        if (!verificarUV.exists()) {
            this.saveResource("ultimovencedor.yml", false);
        }
        this.getCommand("free").setExecutor((CommandExecutor)new Comandos());
        Main.m = this;
        Main.free = new FREE();
        AutoStart.load();
        Bukkit.getConsoleSender().sendMessage(String.valueOf(this.prefix) + "§aAutoStarter carregado.");
        this.saveDefaultConfig();
        this.startEconomy();
        this.legendchatHook();
        this.simpleclansHook();
        Bukkit.getConsoleSender().sendMessage(String.valueOf(this.prefix) + "§aPlugin habilitado com sucesso!");
        if (Main.m.getLocs().getString("Spawn.yaw") != null) {
            final String yawe = Main.m.getLocs().getString("Spawn.yaw");
            final String pitche = Main.m.getLocs().getString("Spawn.pitch");
            final float yawfloat = Float.parseFloat(yawe);
            final float pitchfloat = Float.parseFloat(pitche);
            Comandos.spawn.setX(Main.m.getLocs().getDouble("Spawn.x"));
            Comandos.spawn.setY(Main.m.getLocs().getDouble("Spawn.y"));
            Comandos.spawn.setZ(Main.m.getLocs().getDouble("Spawn.z"));
            Comandos.spawn.setYaw(yawfloat);
            Comandos.spawn.setPitch(pitchfloat);
            Comandos.spawn.setWorld(Bukkit.getServer().getWorld(Main.m.getLocs().getString("Spawn.world")));
            Bukkit.getConsoleSender().sendMessage(String.valueOf(this.prefix) + "§aSpawn carregado com sucesso!");
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(this.prefix) + "§cO Spawn não pode ser carregado! use /free setspawn!");
        }
        if (Main.m.getLocs().getString("Saida.yaw") != null) {
            final String yaws = Main.m.getLocs().getString("Saida.yaw");
            final String pitchs = Main.m.getLocs().getString("Saida.pitch");
            final float yawfloats = Float.parseFloat(yaws);
            final float pitchfloats = Float.parseFloat(pitchs);
            Comandos.saida.setX(Main.m.getLocs().getDouble("Saida.x"));
            Comandos.saida.setY(Main.m.getLocs().getDouble("Saida.y"));
            Comandos.saida.setZ(Main.m.getLocs().getDouble("Saida.z"));
            Comandos.saida.setYaw(yawfloats);
            Comandos.saida.setPitch(pitchfloats);
            Comandos.saida.setWorld(Bukkit.getServer().getWorld(Main.m.getLocs().getString("Saida.world")));
            Bukkit.getConsoleSender().sendMessage(String.valueOf(this.prefix) + "§aSaida carregada com sucesso!");
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(this.prefix) + "§ca Saida não pode ser carregada! use /free setsaida!");
        }
        final ItemMeta EspadaMeta = Comandos.espada.getItemMeta();
        EspadaMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
        EspadaMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
        EspadaMeta.setDisplayName("§4§lPDGH-FREE");
        Comandos.espada.setItemMeta(EspadaMeta);
        final ItemMeta CapaceteMeta = Comandos.capacete.getItemMeta();
        CapaceteMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, true);
        CapaceteMeta.setDisplayName("§4§lPDGH-FREE");
        Comandos.capacete.setItemMeta(CapaceteMeta);
        final ItemMeta PeitoralMeta = Comandos.peitoral.getItemMeta();
        PeitoralMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, true);
        PeitoralMeta.setDisplayName("§4§lPDGH-FREE");
        Comandos.peitoral.setItemMeta(PeitoralMeta);
        final ItemMeta CalcaMeta = Comandos.calca.getItemMeta();
        CalcaMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, true);
        CalcaMeta.setDisplayName("§4§lPDGH-FREE");
        Comandos.calca.setItemMeta(CalcaMeta);
        final ItemMeta BotaMeta = Comandos.bota.getItemMeta();
        BotaMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, true);
        BotaMeta.setDisplayName("§4§lPDGH-FREE");
        Comandos.bota.setItemMeta(BotaMeta);
    }
    
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(String.valueOf(this.prefix) + "§cDesabilitando plugin...");
    }
    
    public FileConfiguration getLocs() {
        if (this.fileConfigurationlocs == null) {
            this.filelocs = new File(this.getDataFolder(), "locs.yml");
            this.fileConfigurationlocs = (FileConfiguration)YamlConfiguration.loadConfiguration(this.filelocs);
        }
        return this.fileConfigurationlocs;
    }
    
    public void saveLocs() {
        try {
            this.getLocs().save(this.filelocs);
        }
        catch (Exception ex) {}
    }
    
    public void reloadLocs() {
        if (this.filelocs == null) {
            this.filelocs = new File(this.getDataFolder(), "locs.yml");
        }
        this.fileConfigurationlocs = (FileConfiguration)YamlConfiguration.loadConfiguration(this.filelocs);
        if (this.fileConfigurationlocs != null) {
            final YamlConfiguration locs = YamlConfiguration.loadConfiguration(this.filelocs);
            this.fileConfigurationlocs.setDefaults((Configuration)locs);
        }
    }
    
    public FileConfiguration getUV() {
        if (this.fileConfigurationUv == null) {
            this.fileUv = new File(this.getDataFolder(), "ultimovencedor.yml");
            this.fileConfigurationUv = (FileConfiguration)YamlConfiguration.loadConfiguration(this.fileUv);
        }
        return this.fileConfigurationUv;
    }
    
    public void saveUV() {
        try {
            this.getUV().save(this.fileUv);
        }
        catch (Exception ex) {}
    }
    
    public void reloadUV() {
        if (this.fileUv == null) {
            this.fileUv = new File(this.getDataFolder(), "ultimovencedor.yml");
        }
        this.fileConfigurationUv = (FileConfiguration)YamlConfiguration.loadConfiguration(this.fileUv);
        if (this.fileConfigurationUv != null) {
            final YamlConfiguration uv = YamlConfiguration.loadConfiguration(this.fileUv);
            this.fileConfigurationUv.setDefaults((Configuration)uv);
        }
    }
    
    private void startEconomy() {
        (this.vaulthook = new VaultHook()).setupEconomy();
    }
    
    private void legendchatHook() {
        (this.lcHook = new LegendChatHook()).hookLegendChat();
    }
    
    private void simpleclansHook() {
        (this.scHook = new SimpleClansHook()).hookSimpleclans();
    }

}
