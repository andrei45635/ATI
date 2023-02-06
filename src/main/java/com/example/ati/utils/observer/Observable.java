package com.example.ati.utils.observer;

import com.example.ati.utils.event.Event;

public interface Observable<E extends Event> {
    void addObserver(Observer<E> obs);
    void notifyObservers(E e);
}
