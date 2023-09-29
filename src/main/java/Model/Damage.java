package Model;

import java.util.Objects;

public class Damage {

    private int from;
    private int to;

    public Damage(int from, int to) {
        this.from = from;
        this.to = to;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Damage damage)) return false;
        return from == damage.from && to == damage.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
