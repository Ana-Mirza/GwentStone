package program.dependencies;

import program.deck.Card;

import java.util.ArrayList;

public final class Table {
    private ArrayList<ArrayList<Card>> table;

    public Table() {
        table = new ArrayList<>();
        final int rows = 4;
        for (int i = 0; i < rows; i++) {
            table.add(new ArrayList<>());
        }
    }

    // Setters and Getters
    public void setTable(final ArrayList<ArrayList<Card>> table) {
        this.table = table;
    }

    public ArrayList<ArrayList<Card>> getTable() {
        return table;
    }
}
