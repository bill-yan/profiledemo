package com.demo.profiledemo.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @javax.validation.constraints.Email
    @NotEmpty
    @Column(unique = true)
    private String email;

    private Boolean primaryEmail;
    private Boolean notificationEnabled;
    private Boolean validated;

    @ManyToOne
    private User user;

    @LastModifiedDate
    private Instant lastModifiedDate;

    @CreatedDate
    private Instant createdDate;
}
