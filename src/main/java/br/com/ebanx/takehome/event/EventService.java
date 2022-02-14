package br.com.ebanx.takehome.event;

import br.com.ebanx.takehome.balance.BalanceRepository;
import br.com.ebanx.takehome.event.dto.EventRequest;
import br.com.ebanx.takehome.event.dto.EventResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public record EventService(BalanceRepository repository) {

  public Mono<EventResponse> handleEvent(EventRequest eventRequest) {
    return switch (eventRequest.type()) {
      case deposit -> handleDeposit(eventRequest);
      case withdraw -> handleWithdraw(eventRequest);
      case transfer -> handleTransfer(eventRequest);
      default -> Mono.empty();
    };
  }

  private Mono<EventResponse> handleDeposit(EventRequest request) {
    return repository.deposit(request.destination(), request.amount())
        .map(total -> EventResponse.fromDestination(request.destination(), total));
  }

  private Mono<EventResponse> handleWithdraw(EventRequest request) {
    return repository.withdraw(request.origin(), request.amount())
        .map(total -> EventResponse.fromOrigin(request.origin(), total));
  }

  private Mono<EventResponse> handleTransfer(EventRequest request) {
    return handleWithdraw(request)
        .flatMap(respOrigin -> handleDeposit(request)
            .map(respDestination -> EventResponse.of(respOrigin, respDestination)));
  }
}
