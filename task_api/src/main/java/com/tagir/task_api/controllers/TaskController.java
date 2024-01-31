package com.tagir.task_api.controllers;

import com.tagir.task_api.entities.Task;
import com.tagir.task_api.entities.enums.TaskStatus;
import com.tagir.task_api.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    /**
     * добавить задачу
     * @param task новая задача
     * @return task
     */
    @PostMapping
    public Task addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    /**
     * Получить все задачи
     * @return список задач
     */
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    /**
     * Получить задачу из базы данных по id
     * @param id
     * @return Optional<Task> (задача)
     */
    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id){
        return taskService.getTaskById(id).orElseThrow(()->
                new RuntimeException("Task not found by id = " + id));
    }

    /**
     * Поиск задачи по статусу
     * @param status статус
     * @return список задач
     */
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status){
        return taskService.getTasksByStatus(status).orElseThrow(()->
                new RuntimeException("Task not found by status " + status));
    }

    /**
     * Обновить статус задачи
     * @param id задача с Id, которую требуется обновить
     * @param task задача, с обновленным статусом
     * @return task
     */
    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTaskStatus(id, task);
    }

    /**
     * Удалить задачу
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
