package com.todo.dao;

import com.todo.entity.RecordStatus;
import org.springframework.stereotype.Repository;
import com.todo.entity.Record;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RecordDao {
    private final List<Record> records = new ArrayList<>(
            Arrays.asList(
                    new Record("изучить спринг", RecordStatus.ACTIVE),
                    new Record("изучить docker", RecordStatus.DONE),
                    new Record("изучить kafka", RecordStatus.ACTIVE)
            )
    );

    public List<Record> findAll(){return new ArrayList<>(records);}

    public void saveRecord(Record record){
        records.add(record);
    }

    public void updateRecordStatus(int id, RecordStatus newStatus){
        for(Record item: records){
            if (item.getId()==id){
                item.setStatus(newStatus);
                break;
            }
        }
    }

    public void deleteRecord(int id){
        records.removeIf(item -> item.getId() == id);
    }
}
