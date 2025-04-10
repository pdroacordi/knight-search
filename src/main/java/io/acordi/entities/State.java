package io.acordi.entities;

import java.util.List;

public class State {

    public Position position;

    public long visitedMask;

    public State parent;

    public State(Position position, long visitedMask, State parent) {
        this.position = position;
        this.visitedMask = visitedMask;
        this.parent = parent;
    }


}
