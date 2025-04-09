package entities;

import java.util.List;

public class State {

    public Position position;

    public long visitedMask;

    public List<Position> path;

    public State(Position position, long visitedMask, List<Position> path) {
        this.position = position;
        this.visitedMask = visitedMask;
        this.path = path;
    }


}
