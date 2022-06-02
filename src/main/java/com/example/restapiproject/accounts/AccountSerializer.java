package com.example.restapiproject.accounts;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class AccountSerializer extends JsonSerializer<Account> {

    @Override
    public void serialize(Account account, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        /*
            id 만 노출되게 한다. password가 노출되면 위험하다.
        "manager":{
            "id":3,
            "email":"user@email.com",
            "password":"{bcrypt}$2a$10$4mu//27CH7HR.X9BJMWJB.1QE6vrFa5Q7T2xfkyzOMObQI.FlLBNC",
            "roles":[
                "USER",
                "ADMIN"
            ]
         },
         */
        gen.writeNumberField("id", account.getId());
        gen.writeEndObject();
    }
}
