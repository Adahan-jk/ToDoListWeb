package com.todo.dao;

import com.todo.entity.Record;
import com.todo.entity.RecordStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RecordDao {
    @PersistenceContext
    private EntityManager em;

    public List<Record> findAll() {
        return em.createQuery("SELECT r FROM Record r ORDER BY r.id", Record.
                        class)
                .getResultList();
    }

    public void saveRecord(Record record) {
        em.persist(record);
    }

    public void updateRecordStatus(int id, RecordStatus newStatus) {
        Record item = em.find(Record.class, id);
        if (item != null) {
            item.setStatus(newStatus);
        }
    }

    public void deleteRecord(int id) {
        Record item = em.find(Record.class, id);
        if (item != null) {
            em.remove(item);
        }
    }
}
