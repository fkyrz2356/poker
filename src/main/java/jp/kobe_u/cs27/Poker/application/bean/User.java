package jp.kobe_u.cs27.Poker.application.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @Column(nullable = false, unique = true)
    @NotNull(message = "ID is required")
    @Size(min = 5, max = 20, message = "ID must be between 5 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "ID can only contain letters, numbers and underscores")
    private String id;

    @Column(nullable = false)
    @NotNull(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Password can only contain letters, numbers and underscores")
    private String password;
}
