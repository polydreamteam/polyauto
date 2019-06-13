package com.polyauto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception correspond à l'envoie d'une erreur HTTP 401
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED,reason = "Unauthorized")
public class UnauthorizedException extends RuntimeException
{
}
