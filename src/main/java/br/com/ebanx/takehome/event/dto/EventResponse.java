package br.com.ebanx.takehome.event.dto;

public record EventResponse(Balance destination, Balance origin) {

  public record Balance(Integer id, Integer balance) {}

  public static EventResponse createDeposit(Integer destination, Integer total) {
    var destinationResponse = new Balance(destination, total);
    return new EventResponse(destinationResponse, null);
  }

  public static EventResponse createWithdraw(Integer origin, Integer total) {
    var originResponse = new Balance(origin, total);
    return new EventResponse(null, originResponse);
  }
}

