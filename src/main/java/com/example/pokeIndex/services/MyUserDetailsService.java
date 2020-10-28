package com.example.pokeIndex.services;

import com.example.pokeIndex.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserService userService;
/*
    @PostConstruct
    private void createDefaultUsers(){
        System.out.println("dasdsad");
            List<String> roles = new ArrayList<>();
            roles.add("ADMIN");
            addUser("kalle", "skalle","password",roles);

    }
*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), getGrantedAuthorities(user));
    }
    private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
        //return user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // ROLE_ADMIN -> ADMIN
                .collect(Collectors.toList());
    }

    public Boolean checkUserRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().toUpperCase().equals("ROLE_"+role));
    }
    public String getCurrentUser() {
        // the login session is stored between page reloads,
        // and we can access the current authenticated User with this
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


/*
    public User addUser(String name, String username, String password, List<String> roles){
        User user = new User(name, username, encoder.encode(password),roles);
        try {
            return userRepo.save(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

 */
}
