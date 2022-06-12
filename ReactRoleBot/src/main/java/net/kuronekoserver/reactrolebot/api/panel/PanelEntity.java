package net.kuronekoserver.reactrolebot.api.panel;

import net.dv8tion.jda.api.entities.Guild;
import net.kuronekoserver.reactrolebot.api.Entity;

public class PanelEntity extends Entity {
    public String name;
    public Boolean tts_blocked = null;

    public PanelEntity(Guild guild) {
        this.id = guild.getIdLong();
        this.name = guild.getName();
    }

    public PanelEntity() {}

}