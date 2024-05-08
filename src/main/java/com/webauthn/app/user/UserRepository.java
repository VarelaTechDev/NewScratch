package com.webauthn.app.user;

import com.yubico.webauthn.data.ByteArray;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);
    User findByHandle(ByteArray handle);
}
