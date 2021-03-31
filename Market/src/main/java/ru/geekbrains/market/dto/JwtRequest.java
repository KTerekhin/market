package ru.geekbrains.market.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class JwtRequest {
    private String username;
    private String password;
    private UUID cartId;
}
