package com.taskmanager.backend.repository;

import com.taskmanager.backend.model.Project;
import com.taskmanager.backend.model.Task;
import com.taskmanager.backend.model.TaskStatus;
import com.taskmanager.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProject(Project project);

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByDueDateBefore(LocalDateTime date);

    List<Task> findByAssignedTo(User user);

    List<Task> findByCreatedBy(User user);

    List<Task> findByCreatedByOrAssignedTo(User createdBy, User assignedTo);
}
