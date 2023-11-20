package com.bkj.demo.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table()
public class Rules {

    @Id
    @GeneratedValue
    private Integer id;
    private String entity;
    private String org;
    private String userAction;
    private String ruleType;
    private String  ruleSet;
}
