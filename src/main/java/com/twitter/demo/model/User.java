package com.twitter.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String location;
    private String website;
    private String birthDate;
    private String email;
    private String password;
    private String mobile;
    private String backgroundImage;
    private String bio;
    private boolean req_user;
    private  boolean loginWithGoogle;
    @JsonIgnore
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL)
    private List<Twit> twit = new ArrayList<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();
    @Embedded

    private Varification verification;
    @ManyToMany
    @JsonIgnore
    private List<User> followers = new ArrayList<>();
    @JsonIgnore
    @ManyToMany
    private List<User> followings = new ArrayList<>();



}
