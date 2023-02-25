package com.uni.bakalauras.model;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "uzsakymas")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Uzsakymo_NR_", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name="DARBUOTOJO_NR", nullable=false)
    private Employees employee;

    @ManyToOne
    @JoinColumn(name="KLIENTO_ID", nullable=false)
    private Clients client;

    @Column(name = "BUSENA")
    private String status;

    @Column(name = "SUKURTAS")
    private LocalDateTime created;

    @Column(name = "ATNAUJINTAS")
    private LocalDateTime updated;

    @Column(name = "UZRASAI")
    private String note;

    @Column(name = "PRISTATYMO_ADRESAS")
    private String orderAddress;

    @Column(name = "APMOKETAS")
    private Boolean payedFor;

    @Column(name = "PRISTATYMO_BUDAS")
    private String deliveryType;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "yra",
            joinColumns = { @JoinColumn(name = "UZSAKYMO_NR_") },
            inverseJoinColumns = { @JoinColumn(name = "PREKES_ID") }
    )
    Set<Products> product = new HashSet<>();


}
