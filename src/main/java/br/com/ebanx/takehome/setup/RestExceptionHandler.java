package br.com.ebanx.takehome.setup;

import br.com.ebanx.takehome.balance.BalanceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  Integer handle(BalanceNotFoundException e) {
    return 0;
  }
}
