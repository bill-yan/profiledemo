package com.demo.profiledemo;

import com.google.common.collect.Lists;
import java.time.Instant;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.profiledemo.model.Email;
import com.demo.profiledemo.model.Locale;
import com.demo.profiledemo.model.Phone;
import com.demo.profiledemo.model.Timezone;
import com.demo.profiledemo.model.User;
import com.demo.profiledemo.repository.EmailRepository;
import com.demo.profiledemo.repository.LocaleRepository;
import com.demo.profiledemo.repository.PhoneRepository;
import com.demo.profiledemo.repository.TimezoneRepository;
import com.demo.profiledemo.repository.UserRepository;

@Component
public class DataInitializer {

    @Autowired
    LocaleRepository localeRepository;

    @Autowired
    TimezoneRepository timezoneRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @PostConstruct
    public void init() {
        Locale en = Locale.builder()
            .name("en-us")
            .locale("en-us")
            .enabled(true)
            .createdDate(Instant.now())
            .lastModifiedDate(Instant.now())
            .build();

        en = localeRepository.save(en);

        Locale cn = Locale.builder()
            .name("zh-cn")
            .locale("zh-cn")
            .enabled(true)
            .createdDate(Instant.now())
            .lastModifiedDate(Instant.now())
            .build();
        localeRepository.save(cn);

        Timezone utc = Timezone.builder()
            .name("UTC")
            .utcOffset(0)
            .description("UTC")
            .build();
        utc = timezoneRepository.save(utc);
        Timezone east = Timezone.builder()
            .name("east")
            .description("east")
            .utcOffset(-4)
            .build();
        timezoneRepository.save(east);

        User testUser = User.builder()
            .id(1L)
            .defaultLocale(en)
            .timezone(utc)
            .fullName("test user")
            .username("test@test.com")
            .createdDate(Instant.now())
            .lastModifiedDate(Instant.now())
            .build();
        testUser = userRepository.save(testUser);
        Email testEmail = Email.builder()
            .email("test@test.com")
            .primaryEmail(true)
            .user(testUser)
            .createdDate(Instant.now())
            .lastModifiedDate(Instant.now())
            .build();
        testEmail = emailRepository.save(testEmail);

        Phone testPhone = Phone.builder()
            .user(testUser)
            .primaryPhone(true)
            .number("123456789")
            .createdDate(Instant.now())
            .lastModifiedDate(Instant.now())
            .build();
        testPhone = phoneRepository.save(testPhone);

        testUser.setEmails(Lists.newArrayList(testEmail));
        testUser.setPhones(Lists.newArrayList(testPhone));


    }
}
