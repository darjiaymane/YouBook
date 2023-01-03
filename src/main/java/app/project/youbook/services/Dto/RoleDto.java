package app.project.youbook.services.Dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link app.project.youbook.domain.Role} entity
 */
@Data
public class RoleDto implements Serializable {
    private final Long id;
    private final String name;
}