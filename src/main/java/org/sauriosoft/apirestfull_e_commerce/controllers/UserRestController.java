package org.sauriosoft.apirestfull_e_commerce.controllers;

import org.sauriosoft.apirestfull_e_commerce.models.dtos.UserDTO;
import org.sauriosoft.apirestfull_e_commerce.models.entities.UserEntity;
import org.sauriosoft.apirestfull_e_commerce.models.exceptions.UserException;
import org.sauriosoft.apirestfull_e_commerce.models.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> response = new HashMap<>();
        List<UserEntity> usersEntity = userService.getAll();
        if (usersEntity.isEmpty()) {
            response.put("message", "No hay usuarios que mostrar");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            List<UserDTO> usersDTO = usersEntity.stream().map(UserDTO::from).collect(Collectors.toList());
            response.put("response", usersDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") final Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserEntity userEntity = userService.getUserById(id);
            response.put("response", UserDTO.from(userEntity));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserException ex) {
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            response.put("error", "Ha ocurrido un error en el servidor");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> setUser(@RequestBody final UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (Objects.nonNull(userDTO.getId())) {
                response.put("message", "El usuario ya existe");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                UserEntity userEntity = userService.addUser(UserEntity.from(userDTO));
                response.put("response", UserDTO.from(userEntity));
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        } catch (UserException ex) {
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            response.put("error", "Error al intentar guardar al usuario");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "id") final Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            userService.deleteUser(id);
            response.put("response", "Usuario eliminado exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserException ex) {
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            response.put("error", "Ha ocurrido un error en el servidor");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable(name = "id") final Long id, @RequestBody final UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserEntity userEntity = UserEntity.from(userDTO);
            userEntity = userService.updateUser(userEntity, id);
            response.put("response", UserDTO.from(userEntity));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (UserException ex) {
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            response.put("error", "Error al intentar actualizar al usuario");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
