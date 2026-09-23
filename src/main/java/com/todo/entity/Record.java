package com.todo.entity;

public class Record {
    private static int counterSequence;

    private final int id;
    private final String title;
    private RecordStatus status;

    public Record(String title, RecordStatus status) {
        this.id = counterSequence++;
        this.title = title;
        this.status = status;
    }

    public Record(String title) {
        this.id = counterSequence++;
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
