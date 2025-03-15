package me.zbg.pdghfree;

import java.util.Calendar;

import org.bukkit.plugin.Plugin;

public class AutoStart
{
    public static void load() {
        Main.m.getServer().getScheduler().runTaskTimerAsynchronously((Plugin)Main.m, (Runnable)new Runnable() {
            @Override
            public void run() {
                for (final String hora : Main.m.getConfig().getStringList("AutoStart.Horarios")) {
                    if (Calendar.getInstance().get(11) == Integer.parseInt(hora.substring(0, 2)) && Calendar.getInstance().get(12) == Integer.parseInt(hora.substring(3, 5)) && !Main.free.acontecendo) {
                        Main.free.iniciar();
                    }
                }
            }
        }, 0L, 1000L);
    }
}
