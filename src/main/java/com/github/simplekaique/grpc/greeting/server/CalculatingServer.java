package com.github.simplekaique.grpc.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class CalculatingServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Hello gRPC");

        Server server = ServerBuilder.forPort(50052)
                .addService(new CalculatingServiceImpl())
                .build();

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            System.out.println("Received shutdown request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));

        server.awaitTermination();

    }

}
