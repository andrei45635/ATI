package com.example.ati.utils.observer;

import com.example.ati.utils.event.Event;

public interface Observer<E extends Event> {
    void update(E e);
}
