package com.example.restaurantsimulator.Threads;

import com.almasb.fxgl.entity.Entity;

import java.util.List;


import com.example.restaurantsimulator.models.Recepcionista;
import com.example.restaurantsimulator.models.Waiter;
import javafx.geometry.Point2D;

public class Handler implements Runnable {

    private Recepcionista monitorRecepcionista;
    private Waiter monitorMesero;
    private final Entity client;
    private final Entity pizza;

    private int numClient;
    private List<Entity> waitersList;

    private List<Point2D> posicionesMeseros;

    public Handler(Recepcionista monitorRecepcionista, Waiter monitorMesero, Entity client, int numClient, List<Entity> waitersList, List<Point2D> posicionesInicialesMeseros, Entity pizza) {
        this.monitorMesero = new Waiter(7);
        this.monitorRecepcionista = new Recepcionista(20);
        this.client = client;
        this.numClient = numClient;
        this.pizza = pizza;
        this.waitersList = waitersList;
        this.posicionesMeseros = posicionesInicialesMeseros;
    }

    @Override
    public void run() {
        monitorRecepcionista.setRecepcionista(monitorMesero);
        while (true) {


            Cliente cliente = new Cliente(monitorRecepcionista, monitorMesero, client, numClient,  waitersList, posicionesMeseros, pizza);
            cliente.start();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
