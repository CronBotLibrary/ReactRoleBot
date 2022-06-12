package net.kuronekoserver.reactrolebot.command;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.command.SlashCommand;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.kuronekoserver.reactrolebot.util.EmbedUtil;

public abstract class Command extends SlashCommand {
    public Command(String name, String description) {
        this.name = name;
        this.help = description;
        this.guildOnly = true;
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        process(event);
    }

    abstract protected void process(SlashCommandEvent event);

    @Override
    protected void execute(CommandEvent event) {
        event.reply(
                EmbedUtil.generateErrorEmbed("Discordの方針により、通常のコマンドは廃止されます。\nスラッシュコマンドをご使用ください。")
        );
    }
}
