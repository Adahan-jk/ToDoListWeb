package com.todo.controller;

import com.todo.dao.RecordDao;
import com.todo.entity.Record;
import com.todo.entity.RecordStatus;
import com.todo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommonController {

    private final RecordService recordService;
    private final RecordDao recordDao;

    @Autowired
    public CommonController(RecordService recordService, RecordDao recordDao) {
        this.recordService = recordService;
        this.recordDao = recordDao;
    }

    @RequestMapping("/")
    public String getMainPage(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String getMainPage(Model model){
        List<Record> records = recordService.findAllRecords();
        int numberOfDoneRecords = (int)records.stream().filter(record -> record.getStatus() == RecordStatus.DONE).count();
        int numberOfActiveRecords = (int)records.stream().filter(record -> record.getStatus() == RecordStatus.ACTIVE).count();
        model.addAttribute("numberOfDoneRecords", numberOfDoneRecords);
        model.addAttribute("numberOfActiveRecords", numberOfActiveRecords);
        model.addAttribute("records", records);
        return "main-page";
    }

    @RequestMapping(value = "/add-record", method = RequestMethod.POST)
    public String addRecord(@RequestParam String title){
        recordService.saveRecord(title);
        return "redirect:/home";
    }

    @RequestMapping(value = "/make-record-done", method = RequestMethod.POST)
    public String makeRecordDone(@RequestParam int id){
        recordService.updateRecordStatus(id, RecordStatus.DONE);
        return "redirect:/home";
    }

    @RequestMapping(value = "/delete-record", method = RequestMethod.POST)
    public String deleteRecord(@RequestParam int id){
        recordService.deleteRecord(id);
        return "redirect:/home";
    }
}
