package net.avishkar.springboot.service;

import net.avishkar.springboot.dto.RegistrationDto;
import net.avishkar.springboot.entity.User;

public interface UserService
{
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
