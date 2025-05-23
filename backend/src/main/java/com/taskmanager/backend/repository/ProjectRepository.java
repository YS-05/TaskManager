package com.taskmanager.backend.repository;

import com.taskmanager.backend.model.Project;
import com.taskmanager.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByOwner(User owner);
}
