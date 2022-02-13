package br.com.ebanx.takehome.event.dto;

public record EventRequest(
    EventType type, Integer amount, Integer destination,
    Integer origin) {

  public enum EventType {
    withdraw, deposit, transfer
  }
}
