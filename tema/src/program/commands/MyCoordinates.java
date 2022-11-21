package program.commands;

import fileio.Coordinates;

public final class MyCoordinates {
    private int x;
    private int y;
    public MyCoordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    public MyCoordinates(final Coordinates coord) {
        this(coord.getX(), coord.getY());
    }

    // Setters and Getters
    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }
}
