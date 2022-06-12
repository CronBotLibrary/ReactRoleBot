package net.kuronekoserver.reactrolebot.command.impl;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.kuronekoserver.reactrolebot.command.Command;

public class RoleCommand extends Command {
    public RoleCommand() {
        super("role", "ロールをパネルへ追加/パネルから削除します。");
    }

    @Override
    protected void process(SlashCommandEvent event) {

    }
}
