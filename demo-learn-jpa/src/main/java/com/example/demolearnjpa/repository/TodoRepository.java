package com.example.demolearnjpa.repository;

import com.example.demolearnjpa.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository  extends JpaRepository<Todo, Integer> {


}
