package trainMe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.UserDao;
import trainMe.model.User;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by romab on 11/26/16.
 */
@Service
public class CustomUserService implements UserDetailsService{

    @Autowired
    private UserDao userDao;


    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.read(login);

        if (user != null) {
            List<GrantedAuthority> authorities =
                    new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new org.springframework.security.core.userdetails.User(
                    user.getLogin(),
                    user.getPassword(),
                    authorities);
        }

        throw new UsernameNotFoundException(
                "User '" + login + "' not found.");
    }

}
