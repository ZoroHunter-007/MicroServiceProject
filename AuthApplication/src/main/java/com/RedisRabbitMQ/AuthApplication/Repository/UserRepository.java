package com.RedisRabbitMQ.AuthApplication.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RedisRabbitMQ.AuthApplication.Entiry.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User>findByEmail(String email);
}
