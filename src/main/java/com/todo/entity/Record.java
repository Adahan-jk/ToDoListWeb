package com.todo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Enumerated(EnumType.STRING)
    private RecordStatus status;

    protected Record() {
    }

    public Record(String title, RecordStatus status) {
        this.title = title;
        this.status = status;
    }

    public Record(String title) {
        this.title = title;
        this.status = RecordStatus.ACTIVE;
    }

    public int getId() {
        return id;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }
}
