package com.backend.appuser;

import com.backend.bucket.Bucket;
import com.backend.dto.AppUserDTO.AppUserDTO;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class AppUser{
    private static final String SEQ_NAME = "user_seq";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    @Transient
    private String password;

    @JoinColumn(name = "app_user_role")
    @ManyToOne
    private Role role;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Bucket bucket;

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }




    public String getPassword() {
        return password;
    }

    public String getUsername(){
        return email;
    }


    public boolean isAccountNonExpired() {
        return false;
    }


    public boolean isAccountNonLocked() {
        return false;
    }


    public boolean isCredentialsNonExpired() {
        return false;
    }


    public boolean isEnabled() {
        return true;
    }

    public Role getRole(){
        return role;
    }

    public AppUser(AppUserDTO appUserDTO){
        this.firstName = appUserDTO.getFirstName();
        this.secondName = appUserDTO.getSecondName();
        this.email = appUserDTO.getEmail();
        this.role = appUserDTO.getRole();
        this.password = appUserDTO.getPassword();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AppUser appUser = (AppUser) o;
        return id != null && Objects.equals(id, appUser.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
