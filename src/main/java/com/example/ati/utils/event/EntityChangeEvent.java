package com.example.ati.utils.event;

import com.example.ati.domain.Bed;

public class EntityChangeEvent implements Event {
    private ChangeEventType type;
    private Bed old_data;
    private Bed data;

    public EntityChangeEvent(ChangeEventType type, Bed old_data, Bed data) {
        this.type = type;
        this.old_data = old_data;
        this.data = data;
    }

    public EntityChangeEvent(ChangeEventType type, Bed data) {
        this.type = type;
        this.data = data;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Bed getOld_data() {
        return old_data;
    }

    public Bed getData() {
        return data;
    }

    //new si old data cu signatura de entity-ul pe care dorim sa il schimbam
    //constructor + getter
}
