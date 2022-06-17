package org.sauriosoft.apirestfull_e_commerce.models.dtos;


import lombok.*;
import org.sauriosoft.apirestfull_e_commerce.models.entities.UserEntity;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {

    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String city;

    private Boolean emailVerified;

    private String verificationCode;

    private String ip;

    private String phone;

    private String country;

    private String address;

    public static UserDTO from(final UserEntity userEntity) {
        return UserDTO.builder().id(userEntity.getId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .city(userEntity.getCity())
                .emailVerified(userEntity.getEmailVerified())
                .verificationCode(userEntity.getVerificationCode())
                .ip(userEntity.getIp())
                .phone(userEntity.getPhone())
                .country(userEntity.getCountry())
                .address(userEntity.getAddress())
                .build();
    }
}
