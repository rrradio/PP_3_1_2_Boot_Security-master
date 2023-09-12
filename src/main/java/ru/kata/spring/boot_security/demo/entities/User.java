package ru.kata.spring.boot_security.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 25, message = "Имя должно быть от 2 до 25 символов")
    private String username;

    @Min(value = 1, message = "Возраст должен быть больше 0")
    @Max(value = 120, message = "Вряд ли вы такой старый..")
    private int age;

    @Email(message = "Введите реальный адрес")
    @NotEmpty(message = "Введите адрес почты")
    private String email;

    @NotEmpty
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, age, email, password, roles);
    }


}
