package program.dependencies;

import program.deck.Card;

import java.util.ArrayList;

public class Table {
    private ArrayList<ArrayList<Card>> table;

    // Constructor
    public Table() {
        table = new ArrayList<ArrayList<Card>>();
        for (int i = 0; i < 4; i++)
            table.add(new ArrayList<Card>());
    }

    // Setters and Getters
    public void setTable(ArrayList<ArrayList<Card>> table) {
        this.table = table;
    }

    public ArrayList<ArrayList<Card>> getTable() {
        return table;
    }
}
