//package by.academy.springboot.model.entity;
//
//import by.academy.springboot.model.entity.enums.Role;
//import jakarta.persistence.*;
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Data
//@Entity
//@Table(name = "user")
//public class User implements UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//    @Column(name = "password")
//    private String password;
//    @Column(name = "username")
//    private String username;
//    @Column(name = "account_non_expired")
//    private boolean accountNonExpired;
//    @Column(name = "account_non_locked")
//    private boolean accountNonLocked;
//    @Column(name = "credentials_non_expired")
//    private boolean credentialsNonExpired;
//    @Column(name = "enabled")
//    private boolean enabled;
//    @Enumerated
//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<Role> roles;
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
