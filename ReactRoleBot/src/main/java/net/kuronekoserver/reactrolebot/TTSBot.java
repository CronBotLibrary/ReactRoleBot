package net.kuronekoserver.reactrolebot;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.source.local.LocalAudioSourceManager;
import com.vdurmont.emoji.EmojiParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.kuronekoserver.reactrolebot.command.Command;
import net.kuronekoserver.reactrolebot.command.CommandManager;
import net.kuronekoserver.reactrolebot.profile.Profile;
import net.kuronekoserver.reactrolebot.profile.ProfileManager;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

public class TTSBot {
    public static TTSBot INSTANCE;
    public JDA bot;

    public final CommandManager commandManager;
    public final ProfileManager profileManager;

    public TextChannel errorReportChannel;
    public TextChannel blockReportChannel;

    public final Profile profile;

    public TTSBot() {
        INSTANCE = this;

        this.commandManager = new CommandManager();
        this.profileManager = new ProfileManager();

        this.profile = profileManager.loadProfile("config");

        this.commandManager.autoRegisterCommands();

        CommandClientBuilder cb = new CommandClientBuilder()
                .setPrefix(this.profile.prefix)
                .setOwnerId("608788412367110149")
                .setActivity(null)
                .setEmojis(EmojiParser.parseToUnicode(":o:"), EmojiParser.parseToUnicode(":bulb:"), EmojiParser.parseToUnicode(":x:"))
                .setLinkedCacheSize(200);

        cb.addCommands(
                commandManager.getCommands().toArray(new Command[0])
        );

        cb.addSlashCommands(
                commandManager.getCommands().toArray(new Command[0])
        );

        try {
            bot = JDABuilder.create(this.profile.token, Arrays.asList(GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_VOICE_STATES))
                    .enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                    .disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.EMOTE, CacheFlag.ONLINE_STATUS)
                    .setActivity(Activity.playing("ロード中..."))
                    .addEventListeners(
                            cb.build(),
                            new Listener()
                    )
                    .setBulkDeleteSplittingEnabled(true)
                    .build();
        } catch (LoginException e) {
            System.out.println("ログインに失敗しました。トークンが間違っています。");
            System.exit(1);
        }
    }

    public void reloadStatus() {
        bot.getPresence().setActivity(
                Activity.playing(this.profile.prefix+"helpでヘルプを表示 | "+bot.getGuilds().size()+"サーバーで稼働中")
        );
    }
}
