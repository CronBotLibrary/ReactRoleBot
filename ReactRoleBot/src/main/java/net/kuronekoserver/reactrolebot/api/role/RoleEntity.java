package net.kuronekoserver.reactrolebot.api.role;

import net.dv8tion.jda.api.entities.Guild;
import net.kuronekoserver.reactrolebot.api.Entity;

public class RoleEntity extends Entity {
    public String name;
    public Boolean tts_blocked = null;

    public RoleEntity(Guild guild) {
        this.id = guild.getIdLong();
        this.name = guild.getName();
    }

    public RoleEntity() {}

}