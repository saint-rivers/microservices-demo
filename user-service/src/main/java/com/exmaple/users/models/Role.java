package com.exmaple.users.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Role")
@Table(
        name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name"}, name = "role_unique")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    private String name;
}
