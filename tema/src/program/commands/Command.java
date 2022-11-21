package program.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import program.dependencies.Player;
import program.dependencies.Table;

import java.util.ArrayList;

// Interface that declares the action method for all commands
public interface Command {
    void action(ArrayNode output, ArrayList<Player> player, Table table);
}
