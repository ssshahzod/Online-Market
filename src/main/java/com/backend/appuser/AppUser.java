package com.backend.appuser;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@SecondaryTable(name = "buckets")
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
    private boolean archive;
    //@Transient TODO: move users password to the other table
    private String password;

    @Column(name = "app_user_role")
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;

    /*@OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "bucket_id")
    private Bucket bucket;*/

    public AppUser(String firstName,
                   String secondName,
                   String email,
                   String password,
                   AppUserRole appUserRole) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }


    public String getPassword() {
        return password;
    }

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
        return appUserRole.toString();
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
