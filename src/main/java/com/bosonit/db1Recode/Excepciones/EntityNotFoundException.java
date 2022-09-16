package com.bosonit.db1Recode.Excepciones;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {
    int httpCode;
    Date timeStamp;

    public EntityNotFoundException(String mensaje, int httpCode) {
        super(mensaje);
        setTimeStamp(new Date());
        setHttpCode(httpCode);
    }
}
