package com.demo.profiledemo.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
public class Phone {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Pattern(regexp = "^[0-9]+[0-9\\-]{3,}[0-9]+$", message = "{invalid.phone.number}")
    private String number;

    private boolean primaryPhone;
    private boolean validated;

    @ManyToOne
    private User user;

    @NotNull
    @ReadOnlyProperty
    @LastModifiedDate
    private Instant lastModifiedDate;

    @NotNull
    @ReadOnlyProperty
    @CreatedDate
    private Instant createdDate;
}
