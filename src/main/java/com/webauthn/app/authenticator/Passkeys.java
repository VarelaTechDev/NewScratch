package com.webauthn.app.authenticator;

import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import com.webauthn.app.user.User;
import com.webauthn.app.utility.ByteArrayAttributeConverter;
import com.yubico.webauthn.RegistrationResult;
import com.yubico.webauthn.data.AttestedCredentialData;
import com.yubico.webauthn.data.AuthenticatorAttestationResponse;
import com.yubico.webauthn.data.ByteArray;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Passkeys {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Lob
    @Convert(converter = ByteArrayAttributeConverter.class)
    @Column(nullable = false)
    private ByteArray credentialId;

    @Lob
    @Convert(converter = ByteArrayAttributeConverter.class)
    @Column(nullable = false)
    private ByteArray publicKey;

    @Column(nullable = false)
    private Long count;

    @Lob
    @Column(nullable = true)
    @Convert(converter = ByteArrayAttributeConverter.class)
    private ByteArray aaguid;

    @ManyToOne
    private User user;

    public Passkeys(RegistrationResult result, AuthenticatorAttestationResponse response, User user, String name) {
        Optional<AttestedCredentialData> attestationData = response.getAttestation().getAuthenticatorData().getAttestedCredentialData();
        this.credentialId = result.getKeyId().getId();
        this.publicKey = result.getPublicKeyCose();
        this.aaguid = attestationData.get().getAaguid();
        this.count = result.getSignatureCount();
        this.name = name;
        this.user = user;
    }
}
