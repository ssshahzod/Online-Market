package com.backend.appuser;

import com.backend.bucket.Bucket;
import com.backend.dto.AppUserDTO.AppUserDTO;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

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
    @Column(name = "user_id")
    private Long id;
    private String firstName;
    private String secondName;
    private String email;

    @Column(name = "app_user_role")
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "bucket_id", referencedColumnName = "bucket_id")
    private Bucket bucket;

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUsername(){
        return email;
    }

    public String getRole(){
        return appUserRole.name();
    }

    public AppUser(AppUserDTO appUserDTO){
        this.firstName = appUserDTO.getFirstName();
        this.secondName = appUserDTO.getSecondName();
        this.email = appUserDTO.getEmail();
        this.appUserRole = appUserDTO.getAppUserRole();
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
