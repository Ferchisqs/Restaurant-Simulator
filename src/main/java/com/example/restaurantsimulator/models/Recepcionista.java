package com.example.restaurantsimulator.models;

import com.almasb.fxgl.entity.Entity;
import com.example.restaurantsimulator.Threads.Cliente;
import javafx.geometry.Point2D;

import java.util.*;

public class Recepcionista {

    private boolean fullRestaurant;
    private int occupatedTables;
    private final int capacityTables;
    private Queue<Cliente> clientsQueue;
    private Map<Cliente, Integer> assignmentOfTables;
    private Waiter waiter;

    public Recepcionista(int _capacityTables) {
        this.fullRestaurant = false;
        this.occupatedTables = 0;
        this.capacityTables = _capacityTables;
        this.clientsQueue = new LinkedList<>();
        this.assignmentOfTables = new HashMap<>();
    }

    public void setRecepcionista(Waiter _waiter) { this.waiter = _waiter; }

    public synchronized void customerArrived(Cliente client) {
        System.out.println("Customer " + client.getId() + " arrived at the restaurant.");

        if(occupatedTables == capacityTables) {
            System.out.println("Restaurant full. Customer " + client.getId() + " in waiting queue");
            clientsQueue.add(client);
            while (fullRestaurant || clientsQueue.peek() != client ) {
                try{
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Customer " + client.getId() + " exit the queue");
            clientsQueue.remove();
        }

        int tableAssigned;
        Set<Integer> availableTables = new HashSet<>();
        for (int i = 1; i <=  capacityTables; i++) {
            availableTables.add(i);
        }
        for (Integer occupedTable : assignmentOfTables.values()) {
            availableTables.remove(occupedTable);
        }

        if(!availableTables.isEmpty()) {
            tableAssigned = availableTables.iterator().next();
        } else  {
            occupatedTables++;
            tableAssigned = occupatedTables;
        }

        assignmentOfTables.put(client, tableAssigned);

        if(occupatedTables == capacityTables) {
            fullRestaurant = true;
        }

        System.out.println("Receptionist assigned the Client " + client.getId() + " at the table " + tableAssigned);

        notifyAll();

        }

        public synchronized  void leaveRestaurant(Cliente cliente, Entity client, Entity food) {
            occupatedTables--;

            if(occupatedTables < capacityTables) {
                fullRestaurant = false;
                notifyAll();
            }

            assignmentOfTables.remove(client);
            Point2D posicionLiberada = cliente.getPosicionAsignada();
            List<Point2D> posiciones = cliente.getPosiciones();

            posiciones.add(posicionLiberada);
            System.out.println("Customer " + cliente.getId() + " leave the restaurant.");
            client.setVisible(false);
            food.setVisible(false);
        }

        public synchronized void deliverFood(Cliente customer) {
            System.out.println("Food delivered at the customer " + customer.getId());

            notify();
        }
}

