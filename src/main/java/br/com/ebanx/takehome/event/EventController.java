package br.com.ebanx.takehome.event;

import br.com.ebanx.takehome.balance.BalanceNotFoundException;
import br.com.ebanx.takehome.event.dto.EventRequest;
import br.com.ebanx.takehome.event.dto.EventResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/event")
public record EventController(EventService service) {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<EventResponse> event(@RequestBody EventRequest eventRequest) {
    return service.handleEvent(eventRequest)
        .switchIfEmpty(Mono.error(BalanceNotFoundException::new));
  }
}
