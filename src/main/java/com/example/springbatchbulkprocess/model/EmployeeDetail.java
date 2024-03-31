package com.example.springbatchbulkprocess.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDetail {
    private Employee employee;
    private InsuranceData insuranceData;

    @Data
    @Builder
    public static class InsuranceData {
        private Integer insuranceId;
        private String plan;
        private Integer premiumAmount;
    }
}
