package com.example.springbatchbulkprocess.batch;

import com.example.springbatchbulkprocess.model.Employee;
import com.example.springbatchbulkprocess.model.EmployeeDetail;
import com.example.springbatchbulkprocess.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class EmployeeDelegateWriter implements ItemWriter<EmployeeDetail> {
    private final ItemWriter<EmployeeDetail> delegate;
    private final EmployeeService employeeService;

    @Override
    public void write(Chunk<? extends EmployeeDetail> chunk) throws Exception {
        List<Employee> employees = chunk.getItems().stream().map(EmployeeDetail::getEmployee).toList();
        var employeeDetails = employeeService.getEmployeesDetail(employees);
        chunk.forEach(item -> {
            var empDetail = employeeDetails.stream().filter(ed ->
                            Objects.equals(item.getEmployee().getEmpId(), ed.getEmployee().getEmpId()))
                    .findFirst();
            empDetail.ifPresent(employeeDetail -> item.setInsuranceData(employeeDetail.getInsuranceData()));
        });
        delegate.write(chunk);
    }
}
