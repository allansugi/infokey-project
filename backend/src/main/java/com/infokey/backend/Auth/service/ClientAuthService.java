package com.infokey.backend.Auth.service;

import java.util.UUID;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infokey.backend.Auth.LoginResponse;
import com.infokey.backend.Auth.exception.PasswordRequirementException;
import com.infokey.backend.Auth.handler.PasswordLengthHandler;
import com.infokey.backend.Auth.handler.PasswordLowercaseHandler;
import com.infokey.backend.Auth.handler.PasswordNumericHandler;
import com.infokey.backend.Auth.handler.PasswordSpecialCharacterHandler;
import com.infokey.backend.Auth.handler.PasswordUppercaseHandler;
import com.infokey.backend.Auth.handler.RegisterHandler;
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
    private final RegisterHandler handler;

    public ClientAuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, com.infokey.backend.Token.TokenService tokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;

        RegisterHandler handler = RegisterHandler.link(
            new PasswordLengthHandler(),
            new PasswordLowercaseHandler(),
            new PasswordUppercaseHandler(),
            new PasswordNumericHandler(),
            new PasswordSpecialCharacterHandler()
        );

        this.handler = handler;
    }

    /**
     * check password requirement and duplicate email, if pass then encrypt the password and store
     * the user info into the database
     * @param userAccount user info for register
     */
    @Override
    public void registerAccount(UserAccountRegister userAccount) {
        if (!handler.check(userAccount)) {
            throw new PasswordRequirementException();
        }

        try {
            String userId = UUID.randomUUID().toString();
            UserAccount passwordEncodedUserAccount = new UserAccount(
                userId, 
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
    public LoginResponse loginAccount(UserAccountLogin userAccountLogin) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userAccountLogin.username(),userAccountLogin.password());
            Authentication authentication = authenticationManager.authenticate(authToken);
            UserAccount account = userRepository.findByUsername(authentication.getName()).get();
            String token = tokenService.generateToken(account.id());
            return new LoginResponse(token, account.id(), account.username());
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }
}
