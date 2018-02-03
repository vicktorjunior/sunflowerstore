package br.com.sunflowerstore.config.auth;

import br.com.sunflowerstore.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by rodrigo on 3/19/17.
 */
public class UserImpl extends org.springframework.security.core.userdetails.User{

    private User user;

    public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities, User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserImpl(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public User getUser() {
        return user;
    }
}
