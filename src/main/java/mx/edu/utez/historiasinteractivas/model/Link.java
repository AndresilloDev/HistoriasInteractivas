package mx.edu.utez.historiasinteractivas.model;

public class Link {
    private int from;
    private int to;

    // G E T T E R S   Y   S E T T E R S

    public Link() {
    }

    public Link(int from, int to) {
        this.from = from;
        this.to = to;
    }

    // G E T T E R S   Y   S E T T E R S

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Link{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
