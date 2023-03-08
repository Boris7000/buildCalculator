package com.psuti.buildcalculator.service.impl;

import com.psuti.buildcalculator.dao.TokenRepository;
import com.psuti.buildcalculator.dao.UserRepository;
import com.psuti.buildcalculator.entities.User;
import com.psuti.buildcalculator.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class UserService implements UserCrudService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }
    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User user, Integer id){
        if(!userRepository.existsById(id)) {
            throw new EntityExistsException("User with id:'"+ user.getId() +"' doesn't exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void removeById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void remove(User user) {
        tokenRepository.findTokenByUserId(user.getId()).ifPresent(tokenRepository::delete);
        userRepository.delete(user);
    }
    @Override
    public void removeByUsername(String username) {
        userRepository.removeByEmail(username);
    }
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(()->{
            throw new EntityExistsException("user with id: " + id + " doesn't exists");
        });
    }
    @Override
    public User getByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(()->{
            throw new UsernameNotFoundException("user with username: " + username
                    + " doesn't exists");
        });
    }
    @Override
    public boolean existsById(Integer id) {
        return userRepository.existsById(id);
    }
    @Override
    public boolean existsByUsername(String username) {
        return userRepository.findByEmail(username).isPresent();
    }
}
