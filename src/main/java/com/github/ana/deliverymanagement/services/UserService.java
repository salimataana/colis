package com.github.ana.deliverymanagement.services;


import com.github.ana.deliverymanagement.models.Users;
import com.github.ana.deliverymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

      @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          Users users = userRepository.findByEmail(email);

          if (users != null){
              var springUser = User.withUsername(users.getEmail())
                      .password(users.getPassword())
                      .roles(users.getRole())
                      .build();

              return springUser;
          }
          return null;
      }
}
