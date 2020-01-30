package com.github.kimhun456;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(long value);
}

class MyObserver implements Observer {

    @Override
    public void update(long value) {
        System.out.println("I am [" + this.toString() + "] new Value : " + value);
    }
}


interface Subject {
    void notifyAllObservers();

    void subscribe(Observer observer);

    void unSubscribe(Observer observer);
}

class MySubject implements Subject {

    long someValue;

    List<Observer> observerList = new ArrayList<>();

    public void setSomeValue(long newValue) {
        someValue = newValue;
        notifyAllObservers();
    }

    @Override
    public void notifyAllObservers() {
        observerList.forEach(observer -> {
            observer.update(someValue);
        });
    }

    @Override
    public void subscribe(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unSubscribe(Observer observer) {
        observerList.remove(observer);
    }
}