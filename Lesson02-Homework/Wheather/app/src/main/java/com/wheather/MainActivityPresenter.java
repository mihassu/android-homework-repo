package com.wheather;

//Паттерн Singleton. В нем должны быть:

public final class MainActivityPresenter { //final класс (не наследуемый)

    private static MainActivityPresenter instance = null; //поле instance с типом самого класса
    private int counter;

    private MainActivityPresenter() { //приватный конструктор
        counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        counter++;
    }

    //публичный статичный метод который возвращает instance и если instance пустое, то создает его
    public static MainActivityPresenter getInstance() {
        if (instance == null) {
            instance = new MainActivityPresenter();
        }
        return instance;
    }
}
