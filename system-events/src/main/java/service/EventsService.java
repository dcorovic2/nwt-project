package service;

import events.grpc.Events;
import events.grpc.eventsGrpc.eventsImplBase;
import io.grpc.stub.StreamObserver;

public class EventsService extends eventsImplBase {
	@Override
    public void tracking(Events.APIRequest request, StreamObserver<Events.APIResponse> responseObserver) {

        Events.APIResponse.Builder response = Events.APIResponse.newBuilder();
        
        response.setResource(request.getResource());
        response.setStatus(request.getStatus());
        response.setAction(request.getAction());
        response.setService(request.getService());
        response.setLiveStartDate(request.getLiveStartDate());
            
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

}
