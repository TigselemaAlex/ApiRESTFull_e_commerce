package org.sauriosoft.apirestfull_e_commerce.models.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID", length = 11)
    private Long id;

    @Column(name = "User_Email" ,length = 500, nullable = false, unique = true)
    private String email;

    @Column(name = "User_Password" ,length = 500, nullable = false)
    private String password;

    @Column(name="User_Firstname", length = 50, nullable = false)
    private String firstName;

    @Column(name = "User_Lastname", length = 50, nullable = false)
    private String lastName;

    @Column(name = "User_City", length = 90, nullable = false)
    private String city;

    @Column(name = "User_Email_Verified", columnDefinition = "TINYINT default 1", length = 1, nullable = false)
    private Boolean emailVerified;

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "User_Verificacion_Code", length = 20, insertable = false)
    private String verificationCode;

    @Column(name = "User_IP", length = 50, nullable = false, unique = true)
    private String ip;

    @Column(name = "User_Phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "User_Country", length = 20, nullable = false)
    private String country;

    @Column(name = "User_Address", length = 100, nullable = false)
    private String address;

}
