package com.example.springbatchbulkprocess.service;

import com.example.springbatchbulkprocess.model.Employee;
import com.example.springbatchbulkprocess.model.EmployeeDetail;

import java.util.List;

public interface EmployeeService {
    EmployeeDetail getEmployeeDetail(Employee employee);

    List<EmployeeDetail> getEmployeesDetail(List<Employee> employees);
}
