package operator;

import java.util.Objects;

public class Value {

    Integer integer;

    public Value(Integer integer) {
        this.integer = integer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value = (Value) o;
        return Objects.equals(integer, value.integer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(integer);
    }
}
