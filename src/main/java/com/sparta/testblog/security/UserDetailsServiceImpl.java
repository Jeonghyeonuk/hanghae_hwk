package com.sparta.testblog.security;

import com.sparta.testblog.model.Users;
import com.sparta.testblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository usersRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Users users = usersRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + userId));
        return new UserDetailsImpl(users);
    }
}
