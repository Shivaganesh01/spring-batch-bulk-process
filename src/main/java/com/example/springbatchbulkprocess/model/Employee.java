package com.example.springbatchbulkprocess.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    private Integer empId;
    private String name;
    private String designation;
}
