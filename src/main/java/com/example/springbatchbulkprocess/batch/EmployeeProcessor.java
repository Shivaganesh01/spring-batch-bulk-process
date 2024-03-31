package com.example.springbatchbulkprocess.batch;

import com.example.springbatchbulkprocess.model.Employee;
import com.example.springbatchbulkprocess.model.EmployeeDetail;
import com.example.springbatchbulkprocess.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class EmployeeProcessor implements ItemProcessor<Employee, EmployeeDetail> {
    private final EmployeeService employeeService;

    @Override
    public EmployeeDetail process(Employee item) throws Exception {
        return EmployeeDetail.builder().employee(item).build();
    }

//    @Override
//    public EmployeeDetail process(Employee item) throws Exception {
//        return employeeService.getEmployeeDetail(item);
//    }
}
