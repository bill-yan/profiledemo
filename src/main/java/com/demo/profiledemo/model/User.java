package com.demo.profiledemo.model;

import java.time.Instant;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


@Data
@ToString(of = {"firstName", "lastName", "username", "createdDate", "lastModifiedDate"})
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("PMD.TooManyFields")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Email> emails;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Phone> phones;

    @javax.validation.constraints.Email
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String username;

    private String firstName;

    private String lastName;

    private String fullName;

    private String imageUrl;

    private String jobTitle;


    @ManyToOne
    private Locale defaultLocale;

    @ManyToOne
    private Timezone timezone;

    @LastModifiedDate
    private Instant lastModifiedDate;

    @CreatedDate
    private Instant createdDate;
}
