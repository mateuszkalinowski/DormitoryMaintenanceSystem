package pl.dormitorymaintenancesystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.dormitorymaintenancesystem.enums.UserStatusEnum;
import pl.dormitorymaintenancesystem.model.UserRole;
import pl.dormitorymaintenancesystem.model.users.User;
import pl.dormitorymaintenancesystem.repositories.UserRepository;


import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            User user = userRepository.findByEmail(authentication.getPrincipal().toString());
            if (user == null) {
                throw new IllegalArgumentException("Niepoprawny email lub hasło");
            } else {
                String password = authentication.getCredentials().toString();
                if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {

                    if (!user.getUserStatus().equals(UserStatusEnum.ACCEPTED)) {
                        if (user.getUserStatus().equals(UserStatusEnum.NEW)) {
                            throw new IllegalArgumentException("Twoje konto nie zostało jeszcze aktywowane.");
                        } else if (user.getUserStatus().equals(UserStatusEnum.SUSPENDED)) {
                            throw new IllegalArgumentException("Twoje konto zostało zawieszone.");
                        }
                    }

                    List<GrantedAuthority> userRoleEnums = new ArrayList<>();
                    for (UserRole userRoles : user.getUserRoles())
                        userRoleEnums.add(new SimpleGrantedAuthority("ROLE_" + userRoles.getUserRole().toString()));

                    return new UsernamePasswordAuthenticationToken(
                            user.getEmail(), password, userRoleEnums);
                } else {
                    throw new IllegalArgumentException("Niepoprawny email lub hasło");
                }
            }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
