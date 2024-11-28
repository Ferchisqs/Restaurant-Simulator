package com.example.restaurantsimulator.models;

import com.example.restaurantsimulator.Threads.Cliente;
import com.example.restaurantsimulator.Threads.Mesero;

import java.util.LinkedList;
import java.util.Queue;

public class Waiter {
    private int availebleWaiters;
    private Queue<Cliente> orderQueue;

    public Waiter(int numberOfWaiters) {
        this.availebleWaiters = numberOfWaiters;
        this.orderQueue = new LinkedList<>();
    }


    public synchronized void fulfillOrder(Cliente cliente) {

        if (availebleWaiters == 0) {
            System.out.println("There are no waiters available. Client " + cliente.getId() + "in the order queue");
            orderQueue.add(cliente);
            while (availebleWaiters == 0 || orderQueue.peek() != cliente) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Client " + cliente.getId() + "  exit the order queue");
            orderQueue.remove();
        }

        availebleWaiters--;
        System.out.println("Client order " + cliente.getId() + "  staffed by a waiter");
        availebleWaiters++;
        notifyAll();
    }


    public synchronized void freeWaiter(Mesero mesero) {
        if (availebleWaiters < 7){
            availebleWaiters++;
        }
        if (orderQueue.size() > 0) {
            notify();
        }
    }
}