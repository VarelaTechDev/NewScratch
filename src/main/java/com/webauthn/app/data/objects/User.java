package com.webauthn.app.data.objects;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.yubico.webauthn.data.ByteArray;

import lombok.Builder;
import lombok.Value;

@Entity
@Value
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false, length = 64)
    private ByteArray userHandle;

    @OneToMany
    private Set<Authenticator> authenticators;
}