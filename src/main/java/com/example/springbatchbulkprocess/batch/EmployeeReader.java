package com.example.springbatchbulkprocess.batch;

import com.example.springbatchbulkprocess.model.Employee;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

public class EmployeeReader implements ItemReader<Employee> {

    private int count = 0;
    private final List<Employee> employees = List.of(
            Employee.builder().empId(1).name("Ram").designation("CEO").build(),
            Employee.builder().empId(2).name("Shyam").designation("CIO").build(),
            Employee.builder().empId(3).name("Hari").designation("COO").build(),
            Employee.builder().empId(4).name("Venu").designation("CFO").build()
            );

    @Override
    public Employee read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(count < employees.size()){
            return employees.get(count++);
        }else{
            count = 0;
        }
        return null;
    }
}
