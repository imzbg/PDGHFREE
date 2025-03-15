package Hooks;

import br.com.devpaulo.legendchat.api.Legendchat;
import br.com.devpaulo.legendchat.channels.ChannelManager;
import me.zbg.pdghfree.Main;

public class LegendChatHook
{
    public boolean hookLegendChat() {
        try {
            if (Main.m.getServer().getPluginManager().getPlugin("Legendchat") != null) {
                final ChannelManager channelManager = Legendchat.getChannelManager();
                return true;
            }
        }
        catch (NoClassDefFoundError e) {
            return false;
        }
        return false;
    }
}
