package net.avishkar.springboot.repository;

import net.avishkar.springboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long>
{
    Role findByName(String name);
}
