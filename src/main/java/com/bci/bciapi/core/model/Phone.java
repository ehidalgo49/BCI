package com.bci.bciapi.core.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String cityCode;
    private String countryCode;
}
