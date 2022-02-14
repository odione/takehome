package br.com.ebanx.takehome.balance;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BalanceController {

  private final BalanceRepository repository;

  private final Mono<String> resetResponse = Mono.just("OK");

  @GetMapping(path = "/balance", params = "account_id")
  Mono<Integer> getBalance(@RequestParam("account_id") String accountId) {
    return repository.getBalanceById(accountId)
        .switchIfEmpty(Mono.error(BalanceNotFoundException::new));
  }

  @PostMapping(path = "/reset")
  Mono<String> reset() {
    repository.reset();
    return resetResponse;
  }
}
