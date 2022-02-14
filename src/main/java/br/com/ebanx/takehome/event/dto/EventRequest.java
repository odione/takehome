package br.com.ebanx.takehome.event.dto;

public record EventRequest(
    EventType type, Integer amount, String destination,
    String origin) {

  public enum EventType {
    withdraw, deposit, transfer
  }
}
