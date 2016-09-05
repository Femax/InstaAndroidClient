package ru.advantum.fedosov.insta.event;

/**
 * Created by fedosov on 9/5/16.
 */
public class LoginEvent {
    private final Type type;

    public LoginEvent(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        SUCCESS, FAILURE, NETWORK, INVALID_INPUT
    }
}
