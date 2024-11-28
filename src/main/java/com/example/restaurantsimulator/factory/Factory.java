package com.example.restaurantsimulator.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.FollowComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.example.restaurantsimulator.factory.Type.Type;

public class Factory implements EntityFactory {

    @Spawns("Client")
    public Entity newClient(SpawnData data) {
        FollowComponent followComponent = new FollowComponent();
        followComponent.setSpeed(1);
        return FXGL.entityBuilder(data)
                .type(Type.CLIENT)
                .view("customer.png")
                .with(followComponent)
                .buildAndAttach();
    }


    @Spawns("Waiter")
    public Entity newWaiter(SpawnData data) {
        FollowComponent followComponent = new FollowComponent();
        followComponent.setSpeed(1);
        return FXGL.entityBuilder(data)
                .type(Type.WAITERS)
                .view("waiter.png")
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


    @Spawns("recepcionist")
    public Entity newRecepcionist(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        return FXGL.entityBuilder(data)
                .type(Type.RECEPCIONIST)
                .view("recepcionista.png")
                .with(physics)
                .build();
    }
}
