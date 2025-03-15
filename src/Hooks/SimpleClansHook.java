package Hooks;

import org.bukkit.plugin.Plugin;

import me.zbg.pdghfree.Main;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

public class SimpleClansHook
{
    public boolean hookSimpleclans() {
        try {
            Plugin[] plugins;
            for (int length = (plugins = Main.m.getServer().getPluginManager().getPlugins()).length, i = 0; i < length; ++i) {
                final Plugin plugin = plugins[i];
                if (plugin instanceof SimpleClans) {
                    return Main.m.sc = true;
                }
            }
        }
        catch (NoClassDefFoundError e) {
            return Main.m.sc = false;
        }
        return false;
    }
}
