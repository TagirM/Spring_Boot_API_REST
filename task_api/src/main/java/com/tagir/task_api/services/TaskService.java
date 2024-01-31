package com.tagir.task_api.services;

import com.tagir.task_api.entities.Task;
import com.tagir.task_api.entities.enums.TaskStatus;
import com.tagir.task_api.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    /**
     * Получить задачу из базы данных по id
     * @param id
     * @return Optional<Task> (задача)
     */
    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    /**
     * добавить задачу
     * @param task новая задача
     * @return task
     */
    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    /**
     * Получить все задачи
     * @return список задач
     */
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    /**
     * Поиск задачи по статусу
     * @param status статус
     * @return список задач
     */
    public Optional<List<Task>> getTasksByStatus(TaskStatus status){
        return taskRepository.findTaskByStatus(status);
    }

    /**
     * Обновить статус задачи
     * @param id задача с Id, которую требуется обновить
     * @param task задача, с обновленным статусом
     * @return task
     */
    public Task updateTaskStatus(Long id, Task task){
        task.setId(id);
        task.setCreatedTime(LocalDateTime.now());
        return taskRepository.save(task);
    }

    /**
     * Удалить задачу
     * @param id
     */
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
