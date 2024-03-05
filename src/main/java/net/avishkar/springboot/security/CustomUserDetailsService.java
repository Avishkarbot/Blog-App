package net.avishkar.springboot.security;

import lombok.AllArgsConstructor;
import net.avishkar.springboot.entity.User;
import net.avishkar.springboot.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(usernameOrEmail);
        if(user != null)
        {
            org.springframework.security.core.userdetails.User authenticatedUser = new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
            );
            return authenticatedUser;
        }else{
            throw new UsernameNotFoundException("Invalid email or password hello");
        }
    }
}

// user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())