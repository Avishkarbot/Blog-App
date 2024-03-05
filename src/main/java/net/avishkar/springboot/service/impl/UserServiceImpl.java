package net.avishkar.springboot.service.impl;

import net.avishkar.springboot.dto.RegistrationDto;
import net.avishkar.springboot.entity.Role;
import net.avishkar.springboot.entity.User;
import net.avishkar.springboot.repository.RoleRepository;
import net.avishkar.springboot.repository.UserRepository;
import net.avishkar.springboot.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService
{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    // Constructor based dependency injection


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto)
    {
        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        // Use spring security to encrypt the password
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
}
