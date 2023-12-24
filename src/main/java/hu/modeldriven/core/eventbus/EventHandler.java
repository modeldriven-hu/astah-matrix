package hu.modeldriven.core.eventbus;

import java.util.List;

public interface EventHandler<T extends Event> {
    void handleEvent(T e);

    public List<Class<? extends Event>> subscribedEvents();
}
