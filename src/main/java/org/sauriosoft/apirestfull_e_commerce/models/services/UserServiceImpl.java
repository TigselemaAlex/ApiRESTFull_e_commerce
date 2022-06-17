package org.sauriosoft.apirestfull_e_commerce.models.services;

import org.sauriosoft.apirestfull_e_commerce.models.entities.UserEntity;
import org.sauriosoft.apirestfull_e_commerce.models.exceptions.UserException;
import org.sauriosoft.apirestfull_e_commerce.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserException("No se puede encontrar al usuario: ".concat(id.toString()))
        );
    }

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity, Long id) {
        UserEntity userToUpdate = getUserById(id);
        userToUpdate.setPassword(userEntity.getPassword());
        userToUpdate.setFirstName(userEntity.getFirstName());
        userToUpdate.setLastName(userEntity.getLastName());
        userToUpdate.setCity(userEntity.getCity());
        userToUpdate.setPhone(userEntity.getPhone());
        userToUpdate.setCountry(userEntity.getCountry());
        userToUpdate.setAddress(userEntity.getAddress());
        return userRepository.save(userToUpdate);
    }

    @Override
    public Boolean deleteUser(Long id) {
        UserEntity userToDelete = getUserById(id);
        if (Objects.nonNull(userToDelete)) {
            userRepository.deleteById(userToDelete.getId());
            return true;
        }
        return false;
    }

}
