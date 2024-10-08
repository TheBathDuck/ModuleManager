package com.jazzkuh.modulemanager.minestom.handlers.listeners;

import net.minestom.server.listener.manager.PacketListenerManager;
import net.minestom.server.network.packet.client.ClientPacket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface PacketListener {
    default void register(PacketListenerManager packetListenerManager) {
        Method[] methods = this.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (!method.isAnnotationPresent(Listen.class)) continue;
            Listen annotation = method.getAnnotation(Listen.class);

            Class<?>[] paramTypes = method.getParameterTypes();
            if (paramTypes.length != 2 || !ClientPacket.class.isAssignableFrom(paramTypes[0])) {
                throw new IllegalArgumentException("Method annotated with @Listen must have a single parameter of type ClientPacket or a subclass of ClientPacket.");
            }

            Class<? extends ClientPacket> registeredPacket = paramTypes[0].asSubclass(ClientPacket.class);
            packetListenerManager.setListener(annotation.connectionState(), registeredPacket, (packet, player) -> {
                try {
                    method.invoke(this, packet, player);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}