package com.demo.profiledemo.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Locale {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotEmpty
    private String locale;

    @Column(unique = true)
    private String name;
    private String nativeName;
    private String region;
    private String nativeRegion;
    private boolean enabled;
    private boolean localized;

    @ReadOnlyProperty
    @LastModifiedDate
    private Instant lastModifiedDate;

    @ReadOnlyProperty
    @CreatedDate
    private Instant createdDate;
}
