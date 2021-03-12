package home.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
  
	@ExceptionHandler(value=NoSuchElementException.class)
	public ResponseEntity<BadRequest> noSuchElementException() {
	  return new ResponseEntity<>(new BadRequest(), HttpStatus.NOT_FOUND);
  }

	
	public class BadRequest{
		String name= "bad #id";
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
