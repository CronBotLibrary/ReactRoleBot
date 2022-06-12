package net.kuronekoserver.reactrolebot;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Listener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        TTSBot.INSTANCE.reloadStatus();
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        TTSBot.INSTANCE.reloadStatus();
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        TTSBot.INSTANCE.reloadStatus();
    }
}
