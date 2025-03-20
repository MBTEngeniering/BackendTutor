package com.onlinenachhilfe.backend.controller;
import com.onlinenachhilfe.backend.dto.UserDto;
import com.onlinenachhilfe.backend.model.Rolle;
import com.onlinenachhilfe.backend.model.User;
import com.onlinenachhilfe.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/findById/{id}")
    public Optional<User> findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/findAll")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        if (userService.deleteUserById(id)) {
            return "User successfully deleted.";
        } else {
            return "User not found.";
        }
    }

    @PutMapping("/update/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUserById(id, user);
        if (updatedUser != null) {
            return updatedUser;
        } else {
            throw new RuntimeException("User not found for update.");
        }
    }

    @GetMapping("/findByNachname/{nachname}")
    public Optional<User> findUserByNachname(@PathVariable String nachname) {
        return userService.findUserByNachname(nachname);
    }

    @GetMapping("/findByVorname/{vorname}")
    public Optional<User> findUserByVorname(@PathVariable String vorname) {
        return userService.findUserByVorname(vorname);
    }

    @GetMapping("/findByTelefonnummer/{telefonnummer}")
    public Optional<User> findUserByTelefonnummer(@PathVariable String telefonnummer) {
        return userService.findUserByTelefonnummer(telefonnummer);
    }

    @GetMapping("/findByEmailadresse/{emailadresse}")
    public Optional<User> findUserByEmailadresse(@PathVariable String emailadresse) {
        return userService.findUserByEmailadresse(emailadresse);
    }

    @GetMapping("/findByAddress")
    public Optional<User> findUserByAddress(
            @RequestParam String strasse,
            @RequestParam String hausnummer,
            @RequestParam String postleitzahl) {
        return userService.findUserByAddress(strasse, hausnummer, postleitzahl);
    }

    // DTO to Entity mapping
    private User dtoToEntity(UserDto dto) {
        User user = new User();
        user.setAnrede(dto.getAnrede());
        user.setTitel(dto.getTitel());
        user.setNachname(dto.getNachname());
        user.setVorname(dto.getVorname());
        user.setStrasse(dto.getStrasse());
        user.setHausnummer(dto.getHausnummer());
        user.setPostleitzahl(dto.getPostleitzahl());
        user.setStadt(dto.getStadt());
        user.setLand(dto.getLand());
        user.setEmailadresse(dto.getEmailadresse());
        user.setPasswort(dto.getPasswort());
        user.setTelefonnummer(dto.getTelefonnummer());
        user.setRolle(dto.getRolle().stream()
                .map(Rolle::valueOf) //
                .collect(Collectors.toSet()));
        return user;
    }

    // Entity to DTO mapping
    private UserDto entityToDto(User user) {
        UserDto dto = new UserDto();
        dto.setAnrede(user.getAnrede());
        dto.setTitel(user.getTitel());
        dto.setNachname(user.getNachname());
        dto.setVorname(user.getVorname());
        dto.setStrasse(user.getStrasse());
        dto.setHausnummer(user.getHausnummer());
        dto.setPostleitzahl(user.getPostleitzahl());
        dto.setStadt(user.getStadt());
        dto.setLand(user.getLand());
        dto.setEmailadresse(user.getEmailadresse());
        dto.setPasswort(user.getPasswort());
        dto.setTelefonnummer(user.getTelefonnummer());
        dto.setRolle(user.getRolle().stream()
                .map(Enum::name)
                .collect(Collectors.toSet()));
        return dto;
    }
}
