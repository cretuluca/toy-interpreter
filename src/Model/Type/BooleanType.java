package Model.Type;

import Model.Value.BooleanValue;

public class BooleanType implements Type {

    @Override
    public boolean equals(Object another){
        return another instanceof BooleanType;
    }

    @Override
    public String toString() {
        return "Boolean";
    }

    @Override
    public Type deepCopy() {
        return new BooleanType();
    }
}
