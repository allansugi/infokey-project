package com.infokey.backend.User;

import com.infokey.backend.Auth.PasswordRequirementException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param userAccount userAccount registration info
     * @return 1 if it is successfully updated
     * @throws DuplicateKeyException if duplicate email exists
     * @throws PasswordRequirementException if userAccount registration don't pass password requirements
     */
    @Override
    public int save(UserAccount userAccount) {
        // TODO: implement check password requirement
        try {
            return userRepository.create(userAccount.withEncodedPassword(passwordEncoder));
        } catch (DuplicateKeyException e) {
            throw new DuplicateUserException();
        }
    }

    @Override
    public int update() {
        return 0;
    }

    @Override
    public int delete() {
        return 0;
    }

    @Override
    public List<UserAccount> findAll() {
        return List.of();
    }

    @Override
    public UserAccount findById(String id) {
        return null;
    }
}
