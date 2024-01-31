package com.tagir.project_management_api.controllers;

import com.tagir.project_management_api.entities.Project;
import com.tagir.project_management_api.entities.User;
import com.tagir.project_management_api.servicies.UserProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserProjectController {
    private final UserProjectService service;

    /**
     * Получить пользователя по Id
     * @param userId
     * @return user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId){
        return ResponseEntity.ok(service.getUser(userId));
    }

    /**
     * Добавить пользователя в базу данных
     * @param user ползователь
     * @return user
     */
    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.ok(service.addUser(user));
    }

    /**
     * Получить проект по Id
     * @param projectId
     * @return project
     */
    @GetMapping("/project/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable Long projectId){
        return ResponseEntity.ok(service.getProject(projectId));
    }

    /**
     * Добавить проект в базу данных
     * @param project проект
     * @return project
     */
    @PostMapping("/project")
    public ResponseEntity<Project> addProject(@RequestBody Project project){
        return ResponseEntity.ok(service.addProject(project));
    }

    /**
     * Получить всех задействованных пользователей в реализации проекта по Id проекта
     * @param projectId Id проекта
     * @return список пользователей
     */
    @GetMapping("/user_project/users/project/{projectId}")
    public ResponseEntity<List<User>> getUsersByProjectId(@PathVariable Long projectId){
        return ResponseEntity.ok(service.getUsersByProjectId(projectId));
    }

    /**
     * Получить все проекты в которых участвует пользователь по Id пользователя
     * @param userId Id пользователя
     * @return список проектов
     */
    @GetMapping("/user_project/projects/user/{userId}")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(service.getProjectsByUserId(userId));
    }

    /**
     * Добавить пользователя в участники проекта
     * @param userId Id пользователя
     * @param projectId Id проекта
     */
    @PostMapping("/user_project/project/{projectId}/user/{userId}")
    public ResponseEntity<Void> addUserToProject(@PathVariable Long projectId, @PathVariable Long userId){
        service.addUserToProject(userId, projectId);
        return ResponseEntity.ok().build();
    }

    /**
     * Исключить пользователя из проекта
     * @param userId Id пользователя
     * @param projectId Id проекта
     */
    @DeleteMapping("/user_project/project/{projectId}/user/{userId}")
    public ResponseEntity<Void> removeUserFromProject(@PathVariable Long projectId, @PathVariable Long userId){
        service.removeUserFromProject(userId, projectId);
        return ResponseEntity.ok().build();
    }
}
