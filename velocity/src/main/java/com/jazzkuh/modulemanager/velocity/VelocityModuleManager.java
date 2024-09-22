package com.jazzkuh.modulemanager.velocity;

import com.jazzkuh.modulemanager.common.ModuleManager;
import com.jazzkuh.modulemanager.velocity.handlers.listeners.AbstractListener;
import com.jazzkuh.modulemanager.velocity.handlers.listeners.ListenerComponentHandler;
import com.jazzkuh.modulemanager.velocity.handlers.tasks.TaskComponentHandler;
import lombok.Getter;
import org.slf4j.Logger;

public final class VelocityModuleManager<P extends IVelocityPlugin> extends ModuleManager {

    @Getter
    private final P plugin;

    public VelocityModuleManager(P plugin, Logger logger) {
        super(logger);
        this.plugin = plugin;

        getComponentRegistry().registerComponentHandler(AbstractListener.class, new ListenerComponentHandler());
        getComponentRegistry().registerComponentHandler(Runnable.class, new TaskComponentHandler());
    }
}
