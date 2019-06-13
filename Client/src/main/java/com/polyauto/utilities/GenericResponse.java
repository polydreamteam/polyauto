package com.polyauto.utilities;

import java.util.HashMap;

/**
 * Cette classe permet d'obtenir des réponse au format json en sortie des fonctions des controlleurs
 *
 * Exemple type de réponse :
 *
 * {
 *     "code":200,
 *     "message":"OK",
 *     "content" [
 *     ]
 * }
 */
public class GenericResponse
{
    public String code = "200";
    public String message = "OK";

    /**
     * Equivalent d'un tableau associatif php
     */
    public HashMap<String,Object> content;

    public GenericResponse()
    {
        content = new HashMap<>();
    }

    public void addToContent(String key, Object value)
    {
        content.put(key,value);
    }
}
