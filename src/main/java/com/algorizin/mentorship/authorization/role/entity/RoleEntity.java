package com.algorizin.mentorship.authorization.role.entity;

import com.algorizin.mentorship.authorization.permission.entity.PermissionEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "roleoid", updatable = false, nullable = false)
    private String roleOid;

    @Column(name = "rolename")
    private String roleName;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name = "createdon")
    private Timestamp createdOn;

    @Column(name = "updatedby")
    private String updatedBy;

    @Column(name = "updatedon")
    private Timestamp updatedOn;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "rolepermission",
            joinColumns = @JoinColumn(name = "roleoid", referencedColumnName = "roleoid"),
            inverseJoinColumns = @JoinColumn(name = "permissionoid", referencedColumnName = "permissionoid")
    )
    private Collection<PermissionEntity> permissions;
}