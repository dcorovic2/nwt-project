package service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import events.grpc.Events;
import events.grpc.eventsGrpc.eventsImplBase;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import model.GRPCModel;
import repository.GRPCRepository;

import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class EventsService extends eventsImplBase {
	
	private final GRPCRepository grpcRepository;
	
	public EventsService (GRPCRepository grpcRepository) {
		this.grpcRepository = grpcRepository;
	}

	@Override
    public void tracking(Events.APIRequest request, StreamObserver<Events.APIResponse> responseObserver) {
		System.out.print("USLO U SERVIS!");
		Events.APIResponse.Builder response = Events.APIResponse.newBuilder();
        
        response.setResource(request.getResource());
        response.setStatus(request.getStatus());
        response.setAction(request.getAction());
        response.setService(request.getService());
        response.setLiveStartDate(request.getLiveStartDate());
        GRPCModel model = new GRPCModel(request.getResource(),request.getStatus(),request.getAction(),request.getService(),new Date(request.getLiveStartDate().getSeconds() * 1000));
		System.out.println(model);
        grpcRepository.save(model);   
        
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
        
    }

}
