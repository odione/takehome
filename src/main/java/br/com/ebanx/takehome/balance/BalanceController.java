package br.com.ebanx.takehome.balance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public record BalanceController(BalanceRepository repository) {

  @GetMapping(path = "/balance", params = "account_id")
  Mono<Integer> getBalance(@RequestParam("account_id") Integer accountId) {
    return repository.getBalanceById(accountId)
        .switchIfEmpty(Mono.error(BalanceNotFoundException::new));
  }

  @PostMapping(path = "/reset")
  void reset() {
    repository.reset();
  }
}
