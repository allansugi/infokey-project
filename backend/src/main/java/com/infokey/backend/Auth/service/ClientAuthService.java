package com.infokey.backend.Auth.service;

import java.util.UUID;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infokey.backend.Auth.exception.PasswordRequirementException;
import com.infokey.backend.Token.TokenService;
import com.infokey.backend.User.dto.UserAccount;
import com.infokey.backend.User.exception.DuplicateUserException;
import com.infokey.backend.User.repository.UserRepository;
import com.infokey.backend.User.request.UserAccountLogin;
import com.infokey.backend.User.request.UserAccountRegister;

@Service
public class ClientAuthService implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public ClientAuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, com.infokey.backend.Token.TokenService tokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    /**
     * check password requirement and duplicate email, if pass then encrypt the password and store
     * the user info into the database
     * @param userAccount user info for register
     */
    @Override
    public void registerAccount(UserAccountRegister userAccount) {
        // TODO: make password requirement
        // currently only do length check but more requirement will be added
        if (userAccount.password().length() < 8) {
            throw new PasswordRequirementException();
        }

        try {
            UserAccount passwordEncodedUserAccount = new UserAccount(
                UUID.randomUUID().toString(), 
                userAccount.username(), 
                userAccount.email(), 
                passwordEncoder.encode(userAccount.password())
            );
            userRepository.create(passwordEncodedUserAccount);
        } catch (DuplicateKeyException e) {
            throw new DuplicateUserException();
        }
    }

    /**
     * authenticate user Info, if authenticated then return a bearer token
     * containing user id
     * @param userAccountLogin
     * @return
     */
    @Override
    public String loginAccount(UserAccountLogin userAccountLogin) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userAccountLogin.username(),userAccountLogin.password());
            Authentication authentication = authenticationManager.authenticate(authToken);
            UserAccount account = userRepository.findByUsername(authentication.getName()).get();
            return tokenService.generateToken(account.id());
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }
}
