package com.wanari.multidb.example.domain.view;

import javax.persistence.*;

@Entity
@Table(name = "sys_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column
    public String email;

}
