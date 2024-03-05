package net.avishkar.springboot.repository;

import net.avishkar.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>
{
    User findByEmail(String email);
}
