package com.example;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.Getter;

@Data
public class Tracking {

    @CsvBindByName(column = "x")
    private String horizontal;

    @CsvBindByName(column = "y")
    private String vertical;
}
