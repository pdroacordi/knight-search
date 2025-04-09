package entities;

public class MoveOption implements Comparable<MoveOption> {
    public Position position;
    public int accessibilityScore;

    public MoveOption(Position position, int accessibilityScore) {
        this.position = position;
        this.accessibilityScore = accessibilityScore;
    }

    @Override
    public int compareTo(MoveOption other) {
        return Integer.compare(this.accessibilityScore, other.accessibilityScore);
    }
}