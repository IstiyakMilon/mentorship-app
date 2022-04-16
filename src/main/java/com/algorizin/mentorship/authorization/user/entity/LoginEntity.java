package com.algorizin.mentorship.authorization.user.entity;

import com.algorizin.mentorship.authorization.role.entity.RoleEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name="login")
public class LoginEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "oid", updatable = false, nullable = false)
    private String oid;

    @Column(name = "userid")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private String status;

    @Column(name = "createdon")
    private Timestamp createdOn;

    @Column(name = "updatedon")
    private Timestamp updatedOn;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "userrole",
            joinColumns = @JoinColumn(name = "loginoid", referencedColumnName = "oid"),
            inverseJoinColumns = @JoinColumn(name = "roleoid", referencedColumnName = "roleoid")
    )
    private Collection<RoleEntity> roles;
}
