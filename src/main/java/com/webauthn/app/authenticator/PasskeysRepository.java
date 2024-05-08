package com.webauthn.app.authenticator;

import java.util.List;
import java.util.Optional;

import com.webauthn.app.user.User;
import com.yubico.webauthn.data.ByteArray;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasskeysRepository extends CrudRepository<Passkeys, Long> {
    Optional<Passkeys> findByCredentialId(ByteArray credentialId);
    List<Passkeys> findAllByUser (User user);
    List<Passkeys> findAllByCredentialId(ByteArray credentialId);
}
