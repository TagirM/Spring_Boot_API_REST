package com.tagir.task_api.repositories;

import com.tagir.task_api.entities.Task;
import com.tagir.task_api.entities.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Поиск задачи по статусу
     * @param status статус
     * @return
     */
    Optional<List<Task>> findTaskByStatus(TaskStatus status);
}
