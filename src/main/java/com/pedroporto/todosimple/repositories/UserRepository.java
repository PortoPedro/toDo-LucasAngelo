package com.pedroporto.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedroporto.todosimple.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
