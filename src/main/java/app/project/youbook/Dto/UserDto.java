package app.project.youbook.Dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link app.project.youbook.domain.User} entity
 */
@Data
public class UserDto implements Serializable {
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String email;
    private final String password;
    private final String status;
    private final List<RoleDto> roles;

}