package com.todo.controller;

import com.todo.dao.RecordDao;
import com.todo.entity.Record;
import com.todo.entity.RecordStatus;
import com.todo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Stream;

@Controller
public class CommonController {

    private final RecordService recordService;
    private final RecordDao recordDao;

    @Autowired
    public CommonController(RecordService recordService, RecordDao recordDao) {
        this.recordService = recordService;
        this.recordDao = recordDao;
    }

    @RequestMapping("/home")
    public String getMainPage(Model model){
        List<Record> records = recordService.findAllRecords();
        int numberOfDoneRecords = (int)records.stream().filter(record -> record.getStatus() == RecordStatus.DONE).count();
        int numberOfActiveRecords = (int)records.stream().filter(record -> record.getStatus() == RecordStatus.ACTIVE).count();
        model.addAttribute("numberOfDoneRecords", numberOfDoneRecords);
        model.addAttribute("numberOfActiveRecords", numberOfActiveRecords);
        return "main-page";
    }
}
