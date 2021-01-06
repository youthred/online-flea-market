package net.add1s.ofm.pojo.entity.sys;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Accessors(chain = true)
public class MyUserDetails implements UserDetails {

    private Long tbId;
    private String nickname;

    private String username;
    private String password;
    /**
     * 账户是否未过期
     */
    private boolean accountNonExpired;
    /**
     * 账户是否未锁定
     */
    private boolean accountNonLocked;
    /**
     * 凭证是否未过期
     */
    private boolean credentialsNonExpired;
    /**
     * 账户是否可用
     */
    private boolean enabled;
    /**
     * 用户权限集合
     */
    Collection<? extends GrantedAuthority> authorities;

    private static final long serialVersionUID = -7279968192485267014L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
