package com.github.simplekaique.grpc.calculator.client;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {

    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        System.out.println("Creating stub");

        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

        SumRequest calculatorRequest = SumRequest.newBuilder()
                .setFirstNumber(10)
                .setSecondNumber(5)
                .build();

        SumResponse response = stub.sum(calculatorRequest);

        System.out.println(calculatorRequest.getFirstNumber() + " + " + calculatorRequest.getSecondNumber() + " = " + response.getSumResult());

        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
