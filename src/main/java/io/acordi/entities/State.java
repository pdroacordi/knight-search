package io.acordi.entities;

public class State {

    public int pos;
    public long visitedMask;
    public State parent;

    public State(int pos, long visitedMask, State parent) {
        this.pos = pos;
        this.visitedMask = visitedMask;
        this.parent = parent;
    }

}
