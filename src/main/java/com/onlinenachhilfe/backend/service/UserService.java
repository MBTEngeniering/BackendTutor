package com.onlinenachhilfe.backend.service;

import com.onlinenachhilfe.backend.model.User;
import com.onlinenachhilfe.backend.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User updateUserById(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setAnrede(updatedUser.getAnrede());
            existingUser.setTitel(updatedUser.getTitel());
            existingUser.setNachname(updatedUser.getNachname());
            existingUser.setVorname(updatedUser.getVorname());
            existingUser.setStrasse(updatedUser.getStrasse());
            existingUser.setHausnummer(updatedUser.getHausnummer());
            existingUser.setPostleitzahl(updatedUser.getPostleitzahl());
            existingUser.setStadt(updatedUser.getStadt());
            existingUser.setLand(updatedUser.getLand());
            existingUser.setEmailadresse(updatedUser.getEmailadresse());
            existingUser.setPasswort(updatedUser.getPasswort());
            existingUser.setTelefonnummer(updatedUser.getTelefonnummer());
            existingUser.setRolle(updatedUser.getRolle());
            return userRepository.save(existingUser);
        }).orElse(null);
    }

    public Optional<User> findUserByNachname(String nachname) {
        return userRepository.findByNachname(nachname);
    }

    public Optional<User> findUserByVorname(String vorname) {
        return userRepository.findByVorname(vorname);
    }

    public Optional<User> findUserByTelefonnummer(String telefonnummer) {
        return userRepository.findByTelefonnummer(telefonnummer);
    }

    public Optional<User> findUserByEmailadresse(String emailadresse) {
        return userRepository.findByEmailadresse(emailadresse);
    }

    public Optional<User> findUserByAddress(String strasse, String hausnummer, String postleitzahl) {
        return userRepository.findByStrasseAndHausnummerAndPostleitzahl(strasse, hausnummer, postleitzahl);
    }
}
