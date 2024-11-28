package com.example.restaurantsimulator.Threads;


import com.example.pizzitas.models.MonitorMesero;

import java.util.Random;

public class Mesero extends Thread {
    private static int contadorMeseros = 0;
    private MonitorMesero monitorMesero;

    public Mesero(MonitorMesero monitorMesero) {
        this.monitorMesero = monitorMesero;
        this.setName("Mesero-" + contadorMeseros++);
    }

    @Override
    public void run() {
        while (true) {
            monitorMesero.liberarMesero(this);
            try {
                Thread.sleep((new Random().nextInt(4) + 5) * 1000);;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}