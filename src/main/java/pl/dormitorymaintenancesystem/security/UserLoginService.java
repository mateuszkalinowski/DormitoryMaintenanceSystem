package pl.dormitorymaintenancesystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.dormitorymaintenancesystem.model.UserRole;
import pl.dormitorymaintenancesystem.model.users.User;
import pl.dormitorymaintenancesystem.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null)
            throw new UsernameNotFoundException("User not found");
        Set<GrantedAuthority> authorities = convertAuthorities(user.getUserRoles());
        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        authorities);
        return userDetails;
    }

    private Set<GrantedAuthority> convertAuthorities(List<UserRole> userRoles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(UserRole ur: userRoles) {
            authorities.add(new SimpleGrantedAuthority(ur.getUserRole().toString()));
        }
        return authorities;
    }

}
