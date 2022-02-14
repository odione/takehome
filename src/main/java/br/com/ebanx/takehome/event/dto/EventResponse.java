package br.com.ebanx.takehome.event.dto;

public record EventResponse(Balance origin, Balance destination) {

  public record Balance(Integer id, Integer balance) {}

  public static EventResponse createDeposit(Integer destination, Integer total) {
    var destinationResponse = new Balance(destination, total);
    return new EventResponse(null, destinationResponse);
  }

  public static EventResponse createWithdraw(Integer origin, Integer total) {
    var originResponse = new Balance(origin, total);
    return new EventResponse(originResponse, null);
  }
}

