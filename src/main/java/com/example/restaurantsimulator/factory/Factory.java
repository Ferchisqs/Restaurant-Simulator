package com.example.restaurantsimulator.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.FollowComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.example.restaurantsimulator.factory.Type.Type;

public class Factory implements EntityFactory {

    @Spawns("Client")
    public Entity newClient(SpawnData data) {
        FollowComponent followComponent = new FollowComponent();
        followComponent.setSpeed(1);
        return FXGL.entityBuilder(data)
                .type(Type.CLIENT)
                .view("client.png")
                .with(followComponent)
                .buildAndAttach();
    }


    @Spawns("Waiter")
    public Entity newWaiter(SpawnData data) {
        FollowComponent followComponent = new FollowComponent();
        followComponent.setSpeed(1);
        return FXGL.entityBuilder(data)
                .type(Type.WAITERS)
                .view("Waiter.png")
                .with(followComponent)
                .buildAndAttach();
    }

    @Spawns("Food")
    public Entity newFood(SpawnData data) {
        FollowComponent followComponent = new FollowComponent();
        followComponent.setSpeed(1);
        return FXGL.entityBuilder(data)
                .type(Type.FOOD)
                .view("Food.png")
                .with(followComponent)
                .buildAndAttach();
    }
}
