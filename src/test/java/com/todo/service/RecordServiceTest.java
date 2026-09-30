package com.todo.service;

import com.todo.dao.RecordDao;
import com.todo.entity.Record;
import com.todo.entity.RecordStatus;
import com.todo.entity.dto.RecordsContainerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecordServiceTest {
    @Mock
    private RecordDao recordDao;

    private RecordService recordService;

    private final Record active = new Record("active task", RecordStatus.ACTIVE);
    private final Record done = new Record("done task", RecordStatus.DONE);

    @BeforeEach
    void setUp() {
        recordService = new RecordService(recordDao);
    }

    private void givenTwoRecords() {
        when(recordDao.findAll()).thenReturn(Arrays.asList(active, done));
    }
    @Test
    void findAll_noFilter_returnsAllWithStats() {
        givenTwoRecords();
        RecordsContainerDto dto = recordService.findAllRecords(null);

        assertEquals(2, dto.getRecords().size());
        assertEquals(1, dto.getNumberOfDoneRecords());
        assertEquals(1, dto.getNumberOfActiveRecords());
    }

    @Test
    void findAll_blankFilter_returnsAll() {
        givenTwoRecords();
        RecordsContainerDto dto = recordService.findAllRecords("   ");

        assertEquals(2, dto.getRecords().size());
    }
    @Test
    void findAll_activeFilter_returnsOnlyActive() {
        givenTwoRecords();
        RecordsContainerDto dto = recordService.findAllRecords("active");

        assertEquals(1, dto.getRecords().size());
        assertEquals("active task", dto.getRecords().get(0).getTitle());
        assertEquals(1, dto.getNumberOfDoneRecords());
        assertEquals(1, dto.getNumberOfActiveRecords());
    }

    @Test
    void findAll_doneFilter_returnsOnlyDone() {
        givenTwoRecords();
        RecordsContainerDto dto = recordService.findAllRecords("DONE");

        assertEquals(1, dto.getRecords().size());
        assertEquals("done task", dto.getRecords().get(0).getTitle());
    }
    @Test
    void findAll_unknownFilter_returnsAll() {
        givenTwoRecords();
        RecordsContainerDto dto = recordService.findAllRecords("trash");

        assertEquals(2, dto.getRecords().size());
    }

    @Test
    void saveRecord_validTitle_delegatesToDao() {
        recordService.saveRecord("new task");

        ArgumentCaptor<Record> captor = ArgumentCaptor.forClass(Record.class);
        verify(recordDao).saveRecord(captor.capture());
        assertEquals("new task", captor.getValue().getTitle());
        assertEquals(RecordStatus.ACTIVE, captor.getValue().getStatus());
    }
    @Test
    void updateRecordStatus_delegatesToDao() {
        recordService.updateRecordStatus(5, RecordStatus.DONE);

        verify(recordDao).updateRecordStatus(5, RecordStatus.DONE);
    }

    @Test
    void deleteRecord_delegatesToDao() {
        recordService.deleteRecord(7);

        verify(recordDao).deleteRecord(7);
    }
}

