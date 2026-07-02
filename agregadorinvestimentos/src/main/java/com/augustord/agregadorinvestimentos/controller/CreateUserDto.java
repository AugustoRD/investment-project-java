package com.augustord.agregadorinvestimentos.controller;

public record CreateUserDto(
    String username,
    String email,
    String password
) {
}