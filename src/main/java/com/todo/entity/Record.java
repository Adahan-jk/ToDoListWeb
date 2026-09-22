package com.todo.entity;

public class Record {
    public Record(String title, RecordEnum status) {
        this.title = title;
        this.status = status;
    }

    private final String title;
    private RecordEnum status;

    public RecordEnum getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public void setStatus(RecordEnum status) {
        this.status = status;
    }
}
