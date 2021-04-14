package service;

import events.grpc.Events;
import events.grpc.eventsGrpc.eventsImplBase;
import io.grpc.stub.StreamObserver;

public class EventsService extends eventsImplBase {
	@Override
    public void tracking(Events.APIRequest request, StreamObserver<Events.APIResponse> responseObserver) {

        String url = request.getService();
        Events.APIResponse.Builder response = Events.APIResponse.newBuilder();
        System.out.println("Vrijeme " +request.getLiveStartDate());
        if(url.equals("employee-service")){
            //success message
            response.setResponseMessage("Success");
            response.setAction(request.getAction());
            

        } else  {
            //failure message
            response.setResponseMessage("Invalid service name");
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

}
