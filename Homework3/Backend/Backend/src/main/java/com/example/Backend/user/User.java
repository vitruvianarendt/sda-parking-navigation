package com.example.Backend.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class User implements UserDetails
{
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String username;
    private String email;
    private String password;
    private String[] savedParkings;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    public User(String username, String email, String password, UserRole userRole, String[] savedParkings)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.savedParkings = savedParkings;
        this.userRole = userRole;
    }

//    public User(UUID id, String username, String email, String password, UserRole userRole, String[] savedParkings)
//    {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.savedParkings = savedParkings;
//        this.userRole = userRole;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    public void setSavedParkings(String[] savedParkings)
    {
        this.savedParkings = savedParkings;
    }

    public String parkingsToString()
    {
        String S = "";
        for(int i = 0; i < savedParkings.length-1; i++)
        {
            S += savedParkings[i] + ", ";
        }
        S += savedParkings[savedParkings.length-1];
        return S;
    }

    @Override
    public String toString()
    {
        return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%b\",\"%b\",\"%s\"",
                id, username, email, password, userRole, locked, enabled, parkingsToString());
    }
}
