package com.wanari.multidb.example.domain.prod;


import javax.persistence.*;

@Entity
@Table(name = "detonation_code")
public class DetonationCode {

    @Id
    public Long id;

    @Column
    public String code;

    @Column
    public String usage;
}
