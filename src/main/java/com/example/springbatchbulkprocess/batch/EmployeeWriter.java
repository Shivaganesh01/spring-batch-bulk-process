package com.example.springbatchbulkprocess.batch;

import com.example.springbatchbulkprocess.model.EmployeeDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public class EmployeeWriter implements ItemWriter<EmployeeDetail> {

    @Override
    public void write(Chunk<? extends EmployeeDetail> chunk) throws Exception {
        log.info("Employees Detail: {}", chunk.getItems());
    }
}
