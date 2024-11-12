package Model.Value;

import Model.Type.BooleanType;
import Model.Type.Type;

public class BooleanValue implements Value {
    Boolean value;

    public BooleanValue(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }

    @Override
    public Value deepCopy() {
        return null;
    }
}
