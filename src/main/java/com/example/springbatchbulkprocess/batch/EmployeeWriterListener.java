package com.example.springbatchbulkprocess.batch;

import com.example.springbatchbulkprocess.model.Employee;
import com.example.springbatchbulkprocess.model.EmployeeDetail;
import com.example.springbatchbulkprocess.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
public class EmployeeWriterListener implements ItemWriteListener<EmployeeDetail> {

    private final EmployeeService employeeService;

    @Override
    public void beforeWrite(Chunk<? extends EmployeeDetail> items) {
        List<Employee> employees = items.getItems().stream().map(EmployeeDetail::getEmployee).toList();
        var employeeDetails = employeeService.getEmployeesDetail(employees);
        items.forEach(item -> {
            var empDetail = employeeDetails.stream().filter(ed ->
                            Objects.equals(item.getEmployee().getEmpId(), ed.getEmployee().getEmpId()))
                    .findFirst();
            empDetail.ifPresent(employeeDetail -> item.setInsuranceData(employeeDetail.getInsuranceData()));
        });
    }

    @Override
    public void afterWrite(Chunk<? extends EmployeeDetail> items) {
        ItemWriteListener.super.afterWrite(items);
    }

    @Override
    public void onWriteError(Exception exception, Chunk<? extends EmployeeDetail> items) {
        ItemWriteListener.super.onWriteError(exception, items);
    }
}
