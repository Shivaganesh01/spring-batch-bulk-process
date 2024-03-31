package com.example.springbatchbulkprocess.batch;

import com.example.springbatchbulkprocess.model.Employee;
import com.example.springbatchbulkprocess.model.EmployeeDetail;
import com.example.springbatchbulkprocess.service.EmployeeService;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class EmployeeBatchConfig {

    @Bean
    public Job employeeJob(final JobRepository jobRepository,
                           final PlatformTransactionManager transactionManager,
                           final EmployeeService employeeService) {
        return new JobBuilder("employeeJob", jobRepository)
                .start(employeeStep(jobRepository, transactionManager, employeeService))
                .build();
    }

    @Bean
    public Step employeeStep(final JobRepository jobRepository,
                             final PlatformTransactionManager transactionManager,
                             final EmployeeService employeeService) {
        return new StepBuilder("employeeStep", jobRepository)
                .<Employee, EmployeeDetail>chunk(2, transactionManager)
                .reader(employeeItemReader())
                .processor(employeeDetailItemProcessor(employeeService))
//                .writer(employeeDetailItemWriter())
//                .listener(itemWriteListener(employeeService))
                .writer(employeeDetailDelegateItemWriter(employeeService, employeeDetailItemWriter()))
                .build();
    }

    @Bean
    public ItemReader<Employee> employeeItemReader() {
        return new EmployeeReader();
    }

    @Bean
    public ItemProcessor<Employee, EmployeeDetail> employeeDetailItemProcessor(EmployeeService employeeService) {
        return new EmployeeProcessor(employeeService);
    }

    @Bean
    public ItemWriter<EmployeeDetail> employeeDetailItemWriter() {
        return new EmployeeWriter();
    }

    @Bean
    public ItemWriteListener<EmployeeDetail> itemWriteListener(EmployeeService employeeService){
        return new EmployeeWriterListener(employeeService);
    }

    @Bean
    public ItemWriter<EmployeeDetail> employeeDetailDelegateItemWriter(EmployeeService employeeService,
                                                                       ItemWriter<EmployeeDetail> employeeDetailItemWriter){
        return new EmployeeDelegateWriter(employeeDetailItemWriter, employeeService);
    }
}
