package com.algorizin.mentorship.authorization.permission.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name="Permission")
public class PermissionEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "oid", updatable = false, nullable = false)
    private String oid;

    @Column(name="permissionoid")
    private String permissionOid;

    @Column(name="permissionname")
    private String permissionName;

    @Column(name="url")
    private String url;

    @Column(name="method")
    private String method;

    @Column(name="createdon")
    private Timestamp createdOn;

}
