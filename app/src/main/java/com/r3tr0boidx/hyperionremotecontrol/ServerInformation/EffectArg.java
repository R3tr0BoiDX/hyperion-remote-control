package com.r3tr0boidx.hyperionremotecontrol.ServerInformation;

public class EffectArg<T> {

    private final String key;
    private final T data;

    public EffectArg(String _key, T _data) {
        this.key = _key;
        this.data = _data;
    }

    public Class<?> getType() {
        return data.getClass();
    }
}