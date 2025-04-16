package com.cord.trs.entity;

import com.cord.trs.enums.Role;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "user_tbl")
public class User {

    @Id
    @SequenceGenerator(name = "tbl_user_id_sequence", initialValue = 1, allocationSize = 1, sequenceName = "tbl_user_id_sequence")
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "tbl_user_id_sequence")
    @Column(name = "user_id")
    private long id;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "email", length = 250)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "phone_number", length = 250)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 150)
    private Role role;

}
