package com.lrcv.helpdesk.config.security.services;

import com.lrcv.helpdesk.config.security.domain.models.UserSS;
import com.lrcv.helpdesk.modules.person.domain.models.Person;
import com.lrcv.helpdesk.modules.person.domain.repositories.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person user = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

        return new UserSS(user.getId(), user.getEmail(), user.getPassword(),
                user.getProfiles());
    }
}
