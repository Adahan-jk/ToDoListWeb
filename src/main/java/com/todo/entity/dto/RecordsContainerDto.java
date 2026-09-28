package com.todo.entity.dto;

import java.util.List;
import com.todo.entity.Record;


public class RecordsContainerDto {
    private final List<Record> records;
    private final int numberOfDoneRecords;
    private final int numberOfActiveRecords;

    public RecordsContainerDto(List<Record> records, int numberOfDoneRecords, int numberOfActiveRecords){
        this.records = records;
        this.numberOfDoneRecords = numberOfDoneRecords;
        this.numberOfActiveRecords = numberOfActiveRecords;
    }


    public List<Record> getRecords() {
        return records;
    }

    public int getNumberOfDoneRecords() {
        return numberOfDoneRecords;
    }

    public int getNumberOfActiveRecords() {
        return numberOfActiveRecords;
    }
}
