package com.tagir.project_management_api.entities;

import jakarta.persistence.*;
import lombok.*;

import static java.lang.Long.valueOf;

@Entity
@Table(name = "user_project")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProject extends EntityWithRelation {

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "user_id")
    private Long userId;

    /**
     * Метод расчета величины поля relatedEntityId;
     * @param userId
     * @param projectId
     * @return relatedEntityId
     */
    public Long relatedEntityIdCalc(Long userId, Long projectId) {
        Long relatedEntityId = valueOf(userId.hashCode())*11 + valueOf(projectId.hashCode())*13;
        this.setRelatedEntityId(relatedEntityId);
        return relatedEntityId;
    }
}
