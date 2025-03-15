package Hooks;

import org.bukkit.plugin.RegisteredServiceProvider;

import me.zbg.pdghfree.Main;
import net.milkbowl.vault.economy.Economy;

public class VaultHook
{
    public static Economy eco;
    
    static {
        VaultHook.eco = null;
    }
    
    public boolean setupEconomy() {
        try {
            if (Main.m.getServer().getPluginManager().getPlugin("Vault") != null) {
                final RegisteredServiceProvider<Economy> economyProvider = (RegisteredServiceProvider<Economy>)Main.m.getServer().getServicesManager().getRegistration((Class)Economy.class);
                if (economyProvider != null) {
                    VaultHook.eco = (Economy)economyProvider.getProvider();
                }
                return VaultHook.eco != null;
            }
        }
        catch (Exception ex) {
            return false;
        }
        return false;
    }
}
