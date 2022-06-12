package net.kuronekoserver.reactrolebot.command;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<Command> commands = new ArrayList<>();

    public CommandManager() {
    }

    public void autoRegisterCommands() {
        new Reflections(this.getClass().getPackage().getName()+".impl")
                .getSubTypesOf(Command.class).forEach(c -> {
                    try {
                        registerCommand(c.newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void registerCommand(Command command) {
        commands.add(command);
    }

    public List<Command> getCommands() {
        return commands;
    }
}
