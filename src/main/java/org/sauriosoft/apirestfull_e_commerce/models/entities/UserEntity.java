package org.sauriosoft.apirestfull_e_commerce.models.entities;


import lombok.*;
import org.sauriosoft.apirestfull_e_commerce.models.dtos.UserDTO;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "User_ID", length = 11)
    private Long id;

    @NotEmpty(message = "El campo email es obligatorio")
    @Email(message = "Email no válido")
    @Column(name = "User_Email", length = 500, nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "El campo contraseña es obligatorio")
    @Column(name = "User_Password", length = 500, nullable = false)
    private String password;

    @Column(name = "User_Firstname", length = 50, nullable = false)
    private String firstName;

    @Column(name = "User_Lastname", length = 50, nullable = false)
    private String lastName;

    @Column(name = "User_City", length = 90, nullable = false)
    private String city;

    @Column(name = "User_Email_Verified", columnDefinition = "TINYINT(1)", nullable = false)
    private Boolean emailVerified;

    @Column(name = "User_Verificacion_Code", length = 255, updatable = false, nullable = false)
    private String verificationCode;

    @Column(name = "User_IP", length = 50, nullable = false, unique = true)
    private String ip;

    @Column(name = "User_Phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "User_Country", length = 20, nullable = false)
    private String country;

    @Column(name = "User_Address", length = 100, nullable = false)
    private String address;

    public static UserEntity from(final UserDTO userDTO) {
        return UserEntity.builder().id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .city(userDTO.getCity())
                .ip(userDTO.getIp())
                .phone(userDTO.getPhone())
                .country(userDTO.getCountry())
                .address(userDTO.getAddress())
                .build();
    }

    @PrePersist
    public void prePersist() {
        emailVerified = false;
        verificationCode = UUID.randomUUID().toString();
    }
}
