package com.polyauto.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class Authenticator
{
    @Autowired
    private static Environment env;

    public static boolean verifyToken()
    {

    }
}
