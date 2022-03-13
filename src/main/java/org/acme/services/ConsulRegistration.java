package org.acme.services;

import io.quarkus.runtime.StartupEvent;
import io.vertx.ext.consul.ServiceOptions;
import io.vertx.mutiny.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.mutiny.core.Vertx;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ConsulRegistration {

    @ConfigProperty(name = "consul.host") String host;
    @ConfigProperty(name = "consul.port") int port;

    @ConfigProperty(name = "hero-service-port", defaultValue = "9000") int hero;
    @ConfigProperty(name = "villain-service-port", defaultValue = "9001") int villain;

    public void init(@Observes StartupEvent ev, Vertx vertx) {
        ConsulClient client = ConsulClient.create(vertx, new ConsulClientOptions().setHost(host).setPort(port));

        client.registerServiceAndAwait(
                new ServiceOptions().setPort(hero).setAddress("localhost").setName("my-rest-service").setId("hero"));
        client.registerServiceAndAwait(
                new ServiceOptions().setPort(villain).setAddress("localhost").setName("my-rest-service").setId("villain"));

    }
    
}
