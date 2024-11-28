package com.example.restaurantsimulator.Threads;


import com.example.restaurantsimulator.models.Waiter;

import java.util.Random;

public class Mesero extends Thread {
    private static int contadorMeseros = 0;
    private Waiter monitorMesero;

    public Mesero(Waiter monitorMesero) {
        this.monitorMesero = monitorMesero;
        this.setName("Waiter-" + contadorMeseros++);
    }

    @Override
    public void run() {
        while (true) {
            monitorMesero.freeWaiter(this);
            try {
                Thread.sleep((new Random().nextInt(4) + 5) * 1000);;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}