package com.todo.entity;

public class Record {
    public Record(String title, RecordStatus status) {
        this.title = title;
        this.status = status;
    }

    private final String title;
    private RecordStatus status;

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
