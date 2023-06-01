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
    @NotNull
    @Size(min = 5, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    private String id;

    @Column(nullable = false)
    @NotNull
    @Size(min = 8)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    private String password;
}
