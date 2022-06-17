package org.sauriosoft.apirestfull_e_commerce.models.services;

import org.sauriosoft.apirestfull_e_commerce.models.entities.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> getAll();

    UserEntity getUserById(Long id);

    UserEntity addUser(UserEntity userEntity);

    UserEntity updateUser(UserEntity userEntity, Long id);

    Boolean deleteUser(Long id);

}
