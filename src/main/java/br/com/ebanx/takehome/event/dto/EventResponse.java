package br.com.ebanx.takehome.event.dto;

public record EventResponse(Balance origin, Balance destination) {

  public record Balance(String id, Integer balance) {}

  public static EventResponse fromDestination(String destination, Integer total) {
    var destinationResponse = new Balance(destination, total);
    return new EventResponse(null, destinationResponse);
  }

  public static EventResponse fromOrigin(String origin, Integer total) {
    var originResponse = new Balance(origin, total);
    return new EventResponse(originResponse, null);
  }

  public static EventResponse of(EventResponse origin, EventResponse destination) {
    return new EventResponse(origin.origin(), destination.destination());
  }
}

