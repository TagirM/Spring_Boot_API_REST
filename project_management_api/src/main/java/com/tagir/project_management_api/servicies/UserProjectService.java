package com.tagir.project_management_api.servicies;

import com.tagir.project_management_api.entities.Project;
import com.tagir.project_management_api.entities.User;
import com.tagir.project_management_api.entities.UserProject;
import com.tagir.project_management_api.exceptions.UniqueUserProjectException;
import com.tagir.project_management_api.repositories.ProjectRepository;
import com.tagir.project_management_api.repositories.UserProjectRepository;
import com.tagir.project_management_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProjectService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UserProjectRepository userProjectRepository;

    /**
     * Получить пользователя по Id
     * @param id
     * @return user
     */
    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(()->
                new RuntimeException("User not found by id = " + id));
    }

    /**
     * Получить проект по Id
     * @param id
     * @return project
     */
    public Project getProject(Long id){
        return projectRepository.findById(id).orElseThrow(()->
                new RuntimeException("Project not found by id = " + id));
    }

    /**
     * Добавить пользователя в базу данных
     * @param user ползователь
     * @return user
     */
    public User addUser(User user){
        return userRepository.save(user);
    }

    /**
     * Добавить проект в базу данных
     * @param project проект
     * @return project
     */
    public Project addProject(Project project){
        return projectRepository.save(project);
    }

    /**
     * Получить всех задействованных пользователей в реализации проекта по Id проекта
     * @param projectId Id проекта
     * @return список пользователей
     */
    public List<User> getUsersByProjectId(Long projectId){
        Optional<List<UserProject>> userProjects = userProjectRepository.findUserProjectByProjectId(projectId);
        if (userProjects.isPresent()){
            List<User> users = new ArrayList<>();
            for (UserProject userProj : userProjects.get()) {
                users.add(userRepository.findById(userProj.getUserId()).get());
            }
            return users;
        }
        else {
            throw new RuntimeException("Project with id = " + projectId +
                    " have not manage by users or it is absent in database");
        }
    }

    /**
     * Получить все проекты в которых участвует пользователь по Id пользователя
     * @param userId Id пользователя
     * @return список проектов
     */
    public List<Project> getProjectsByUserId(Long userId){
        Optional<List<UserProject>> userProjects = userProjectRepository.findUserProjectByUserId(userId);
        if (userProjects.isPresent()){
            List<Project> projects = new ArrayList<>();
            for (UserProject userProj :
                    userProjects.get()) {
                projects.add(projectRepository.findById(userProj.getProjectId()).get());
            }
            return projects;
        }
        else {
            throw new RuntimeException("User with id = " + userId +
                    " have not manage any project or he is absent in database");
        }
    }

    /**
     * Добавить пользователя в участники проекта
     * @param userId Id пользователя
     * @param projectId Id проекта
     */
    public void addUserToProject(Long userId, Long projectId){
        Optional<User> user = userRepository.findById(userId);
        Optional<Project> project = projectRepository.findById(projectId);
        if (user.isPresent()&&project.isPresent()){
            UserProject userProject = new UserProject();
            try{
                userProject.relatedEntityIdCalc(userId, projectId);
            }
            catch (UniqueUserProjectException e){
                e.getMessage();
            }
            userProject.setUserId(userId);
            userProject.setProjectId(projectId);
            userProjectRepository.save(userProject);
        }
        else {
            throw new RuntimeException("User with id = " + userId +
                    " or project with id = " + projectId + " are absent in database");
        }
    }

    /**
     * Исключить пользователя из проекта
     * @param userId Id пользователя
     * @param projectId Id проекта
     */
    public void removeUserFromProject(Long userId, Long projectId){
        Optional<List<UserProject>> userProjects = userProjectRepository.findUserProjectByProjectId(projectId);
        if (userProjects.isPresent()){
            for (UserProject userProject :
                    userProjects.get()) {
                if (userProject.getUserId().equals(userId)){
                    userProjectRepository.delete(userProject);
                }
            }
        }
    }
}
