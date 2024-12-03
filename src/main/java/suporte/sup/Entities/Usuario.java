package suporte.sup.Entities;

import lombok.Data;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }

    @Override
    public String getPassword(){
        return password;
    };

    public String getUsername(){
        return nome;
    };

    @JsonIgnore
    @OneToMany(mappedBy = "usuarioCriador", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
