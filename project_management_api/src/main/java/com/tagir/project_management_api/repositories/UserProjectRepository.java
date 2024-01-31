package com.tagir.project_management_api.repositories;

import com.tagir.project_management_api.entities.Project;
import com.tagir.project_management_api.entities.User;
import com.tagir.project_management_api.entities.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, Long> {

    Optional<List<UserProject>> findUserProjectByUserId(Long userId);

    Optional<List<UserProject>> findUserProjectByProjectId(Long projectId);
}
