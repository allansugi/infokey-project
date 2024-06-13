package com.infokey.backend.Auth;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infokey.backend.Token.TokenService;
import com.infokey.backend.User.DuplicateUserException;
import com.infokey.backend.User.UserAccount;
import com.infokey.backend.User.UserAccountLogin;
import com.infokey.backend.User.UserRepository;

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
     * check password requirement and duplicate email, if pass then encrypt it and store
     * the user info into the database
     * @param userAccount user info for register
     */
    @Override
    public void registerAccount(UserAccount userAccount) {
        // TODO: make password requirement
        // currently only do length check but more requirement will be added
        if (userAccount.password().length() < 8) {
            throw new PasswordRequirementException();
        }

        try {
            UserAccount passwordEncodedUserAccount = userAccount.withEncodedPassword(passwordEncoder);
            userRepository.create(passwordEncodedUserAccount);
        } catch (DuplicateKeyException e) {
            throw new DuplicateUserException();
        }
    }

    @Override
    public String loginAccount(UserAccountLogin userAccountLogin) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userAccountLogin.username(),
                    userAccountLogin.password()));
            return tokenService.generateToken(authentication);
        } catch (AuthenticationException e) {
            System.out.println("Authentication error");
            throw new RuntimeException(e);
        }
    }
}
