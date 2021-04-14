package events.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: events.proto")
public final class eventsGrpc {

  private eventsGrpc() {}

  public static final String SERVICE_NAME = "events";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<events.grpc.Events.APIRequest,
      events.grpc.Events.APIResponse> getTrackingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "tracking",
      requestType = events.grpc.Events.APIRequest.class,
      responseType = events.grpc.Events.APIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<events.grpc.Events.APIRequest,
      events.grpc.Events.APIResponse> getTrackingMethod() {
    io.grpc.MethodDescriptor<events.grpc.Events.APIRequest, events.grpc.Events.APIResponse> getTrackingMethod;
    if ((getTrackingMethod = eventsGrpc.getTrackingMethod) == null) {
      synchronized (eventsGrpc.class) {
        if ((getTrackingMethod = eventsGrpc.getTrackingMethod) == null) {
          eventsGrpc.getTrackingMethod = getTrackingMethod = 
              io.grpc.MethodDescriptor.<events.grpc.Events.APIRequest, events.grpc.Events.APIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "events", "tracking"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  events.grpc.Events.APIRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  events.grpc.Events.APIResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new eventsMethodDescriptorSupplier("tracking"))
                  .build();
          }
        }
     }
     return getTrackingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static eventsStub newStub(io.grpc.Channel channel) {
    return new eventsStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static eventsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new eventsBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static eventsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new eventsFutureStub(channel);
  }

  /**
   */
  public static abstract class eventsImplBase implements io.grpc.BindableService {

    /**
     */
    public void tracking(events.grpc.Events.APIRequest request,
        io.grpc.stub.StreamObserver<events.grpc.Events.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTrackingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getTrackingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                events.grpc.Events.APIRequest,
                events.grpc.Events.APIResponse>(
                  this, METHODID_TRACKING)))
          .build();
    }
  }

  /**
   */
  public static final class eventsStub extends io.grpc.stub.AbstractStub<eventsStub> {
    private eventsStub(io.grpc.Channel channel) {
      super(channel);
    }

    private eventsStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected eventsStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new eventsStub(channel, callOptions);
    }

    /**
     */
    public void tracking(events.grpc.Events.APIRequest request,
        io.grpc.stub.StreamObserver<events.grpc.Events.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTrackingMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class eventsBlockingStub extends io.grpc.stub.AbstractStub<eventsBlockingStub> {
    private eventsBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private eventsBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected eventsBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new eventsBlockingStub(channel, callOptions);
    }

    /**
     */
    public events.grpc.Events.APIResponse tracking(events.grpc.Events.APIRequest request) {
      return blockingUnaryCall(
          getChannel(), getTrackingMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class eventsFutureStub extends io.grpc.stub.AbstractStub<eventsFutureStub> {
    private eventsFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private eventsFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected eventsFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new eventsFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<events.grpc.Events.APIResponse> tracking(
        events.grpc.Events.APIRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTrackingMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TRACKING = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final eventsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(eventsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TRACKING:
          serviceImpl.tracking((events.grpc.Events.APIRequest) request,
              (io.grpc.stub.StreamObserver<events.grpc.Events.APIResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class eventsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    eventsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return events.grpc.Events.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("events");
    }
  }

  private static final class eventsFileDescriptorSupplier
      extends eventsBaseDescriptorSupplier {
    eventsFileDescriptorSupplier() {}
  }

  private static final class eventsMethodDescriptorSupplier
      extends eventsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    eventsMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (eventsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new eventsFileDescriptorSupplier())
              .addMethod(getTrackingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
