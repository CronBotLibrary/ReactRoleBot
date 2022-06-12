package net.kuronekoserver.reactrolebot.command.impl;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.kuronekoserver.reactrolebot.command.Command;

public class PanelCommand extends Command {
    public PanelCommand() {
        super("panel", "役職パネルを作成/削除します。");
    }

    @Override
    protected void process(SlashCommandEvent event) {
    }
}
