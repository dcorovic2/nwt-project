package server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.EventsService;

public class GRPCserver {
    public static void main(String args[]) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9091).addService(new EventsService()).build();
        server.start();
        System.out.println("Server started on " + server.getPort());
        server.awaitTermination();

    }
}



