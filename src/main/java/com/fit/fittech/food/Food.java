package com.fit.fittech.food;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
public class Food {

    @Id
    long id;
    byte waterIntake;
    short calories;
}
