package br.com.ebanx.takehome.balance;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class BalanceRepository {

  private final Map<String, Integer> balances = new ConcurrentHashMap<>();

  public Mono<Integer> getBalanceById(String id) {
    return Mono.fromCallable(() -> balances.get(id));
  }

  public Mono<Integer> deposit(String destination, Integer amount) {
    return getBalanceById(destination)
        .defaultIfEmpty(0)
        .map(saved -> saved + amount)
        .doOnNext(total -> balances.put(destination, total));
  }

  public Mono<Integer> withdraw(String origin, Integer amount) {
    return getBalanceById(origin)
        .map(saved -> saved - amount)
        .doOnNext(total -> balances.put(origin, total));
  }

  public void reset() {
    balances.clear();
  }
}
