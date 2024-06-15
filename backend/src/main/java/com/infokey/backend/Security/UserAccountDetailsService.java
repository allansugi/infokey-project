package com.infokey.backend.Security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infokey.backend.User.UserAccount;
import com.infokey.backend.User.UserRepository;

@Service
public class UserAccountDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserAccountDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    /**
     * uses email instead of username since email is unique
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> optionalUserAccount = userRepository.findByEmail(username);
        System.out.println(optionalUserAccount.isEmpty());
        
        if (optionalUserAccount.isEmpty()) {
            throw new UsernameNotFoundException("username does not exist");
        }
        UserAccount userAccount = optionalUserAccount.get();
        User.UserBuilder userBuilder = User.builder();
        userBuilder.username(userAccount.username());
        userBuilder.password(userAccount.password());
        return userBuilder.build();
    }
}
