package com.github.simplekaique.grpc.greeting.server;

import com.proto.dummy.Calculator;
import com.proto.dummy.CalculatorRequest;
import com.proto.dummy.CalculatorResponse;
import com.proto.dummy.CalculatorServiceGrpc;
import io.grpc.stub.StreamObserver;

public class CalculatingServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    public void calculator(CalculatorRequest request, StreamObserver<CalculatorResponse> responseStreamObserver){

        Calculator calculator = request.getCalculator();
        int firstNumber = calculator.getFirstNumber();
        int secondNumber = calculator.getSecondNumber();

        int result = firstNumber + secondNumber;

        CalculatorResponse response = CalculatorResponse.newBuilder()
                .setResult(result)
                .build();

        responseStreamObserver.onNext(response);

        responseStreamObserver.onCompleted();

    }

}
