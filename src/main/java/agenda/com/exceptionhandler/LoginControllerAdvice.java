package agenda.com.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice(basePackages = "agenda.com.demo.auth")
public class LoginControllerAdvice {

    @ResponseBody
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> loginduplicate(TaskNotFoundException taskNotFoundException){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(),"Usuario já existe");
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
