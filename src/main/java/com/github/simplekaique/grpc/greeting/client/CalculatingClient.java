package com.github.simplekaique.grpc.greeting.client;

import com.proto.dummy.Calculator;
import com.proto.dummy.CalculatorRequest;
import com.proto.dummy.CalculatorResponse;
import com.proto.dummy.CalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatingClient {

    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        System.out.println("Creating stub");

        CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorClient = CalculatorServiceGrpc.newBlockingStub(channel);

        Calculator calculator = Calculator.newBuilder()
                .setFirstNumber(10)
                .setSecondNumber(5)
                .build();

        CalculatorRequest calculatorRequest = CalculatorRequest.newBuilder()
                .setCalculator(calculator)
                .build();

        CalculatorResponse response = calculatorClient.calculator(calculatorRequest);

        System.out.println(response.getResult());

        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
