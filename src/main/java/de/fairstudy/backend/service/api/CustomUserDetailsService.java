package de.fairstudy.backend.service.api;

import de.fairstudy.backend.dto.AllUserDto;
import de.fairstudy.backend.model.UserRole;
import de.fairstudy.backend.service.AllUserService;
import de.fairstudy.backend.service.UserRoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AllUserService allUserService;
    private final UserRoleService userRoleService;

    public CustomUserDetailsService(AllUserService allUserService, UserRoleService userRoleService) {
        this.allUserService = allUserService;
        this.userRoleService = userRoleService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AllUserDto> oUser = allUserService.getUsersByEmail(email);

        if (oUser.isPresent()) {
            AllUserDto user = oUser.get();
            return new org.springframework.security.core.userdetails.User(user.getEmailAddress(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getUserRoleIds()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Long> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Long role : roles) {
            Optional<UserRole> userRoleDao = userRoleService.findRoleById(role);
            if (userRoleDao.isEmpty()) {
                throw new UsernameNotFoundException("Role not found");
            }

            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRoleDao.get().getRole().name());
            list.add(simpleGrantedAuthority);
        }
        return list;
    }
}

