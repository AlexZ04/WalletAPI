package ru.cft.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.cft.template.dto.ResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ResponseDto> catchUsedCredentialsException(UsedCredentialsException exception) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> catchUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.NOT_FOUND.value(), exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> catchUnauthorizedException(UnauthorizedException exception) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.UNAUTHORIZED.value(), exception.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> catchForbidException(ForbidException exception) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.FORBIDDEN.value(), exception.getMessage()),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> catchWalletException(WalletNotFoundException exception) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.NOT_FOUND.value(), exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> catchBadTransferException(CantMakeTranserException exception) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> catchNotEnoughMoneyException(NotEnoughMoneyException exception) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.FORBIDDEN.value(), exception.getMessage()),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> catchTransferNotFoundException(TransferNotFoundException exception) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.NOT_FOUND.value(), exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> catchSessionNotFoundException(SessionNotFoundException exception) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.NOT_FOUND.value(), exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> catchSessionAlreadyInactiveException(SessionAlreadyInactiveException exception) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> catchSelfTransactionException(SelfTransactionException exception) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
