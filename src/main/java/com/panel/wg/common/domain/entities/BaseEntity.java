package com.panel.wg.common.domain.entities;

import com.panel.wg.common.domain.events.Event;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseEntity<T> {
    private final List<Event> events = new ArrayList<>();

    private void raise(Event event) {
        events.add(event);
    }

    protected void handleEvent(Event event) {
//        setStateByEvent(event);
        validateInvariants();
        raise(event);
    }

//    protected abstract void setStateByEvent(Event event);

    protected List<Event> getChanges() {
        return events;
    }

    protected void clearEvents() {
        events.clear();
    }

    protected T id;

    public void setId(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    protected abstract void validateInvariants();

}