package org.sauriosoft.apirestfull_e_commerce.models.repositories;

import org.sauriosoft.apirestfull_e_commerce.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
