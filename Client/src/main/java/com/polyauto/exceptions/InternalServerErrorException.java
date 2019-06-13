package com.polyauto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception correspond à l'envoie d'une erreur HTTP 500
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,reason = "Internal server error")
public class InternalServerErrorException extends RuntimeException
{
}
