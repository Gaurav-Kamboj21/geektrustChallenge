package com.makespace.Commands;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.makespace.Exceptions.NoSuchCommandException;

public class CommandInvoker {
    private static final Map<String, Command> commandMap = new HashMap<>();

    public void register(String commandName, Command command){
        commandMap.put(commandName,command);
    }

    private Command get(String commandName){
        return commandMap.get(commandName);
    }

    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException {
        Command command = get(commandName);
        if(command == null){
            throw new NoSuchCommandException("No such command found");
        }
        command.execute(tokens);
    }
}
