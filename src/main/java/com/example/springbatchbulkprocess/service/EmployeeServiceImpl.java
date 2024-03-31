package com.example.springbatchbulkprocess.service;

import com.example.springbatchbulkprocess.model.Employee;
import com.example.springbatchbulkprocess.model.EmployeeDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public EmployeeDetail getEmployeeDetail(Employee employee){
        return getMockEmployeeDetail(employee);
    }

    @Override
    public List<EmployeeDetail> getEmployeesDetail(List<Employee> employees){
        return employees.stream().map(this::getMockEmployeeDetail).toList();
    }

    private EmployeeDetail getMockEmployeeDetail(Employee employee){
        List<String> insurancePlans = List.of("Silver", "Gold", "Platinum", "Titanium");
        Integer[] insurancePremiumAmount = new Integer[]{10000, 15000, 25000, 40000};
        int index = employee.getEmpId() % insurancePlans.size();
        String plan = insurancePlans.get(index);
        EmployeeDetail.InsuranceData insuranceData = EmployeeDetail.InsuranceData.builder()
                .insuranceId(plan.length())
                .plan(plan)
                .premiumAmount(insurancePremiumAmount[index]).build();
        return EmployeeDetail.builder().employee(employee).insuranceData(insuranceData).build();
    }
}
