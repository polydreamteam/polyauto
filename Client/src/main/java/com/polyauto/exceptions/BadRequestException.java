package com.polyauto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception correspond à l'envoie d'une erreur HTTP 400
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "Bad request invalid parameters")
public class BadRequestException extends RuntimeException
{
}
