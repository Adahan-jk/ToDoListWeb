package com.todo.controller;

import com.todo.dao.RecordDao;
import com.todo.entity.Record;
import com.todo.entity.RecordStatus;
import com.todo.entity.dto.RecordsContainerDto;
import com.todo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.todo.entity.dto.RecordsContainerDto;
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
    public String getMainPage(Model model,@RequestParam(name = "filter", required = false) String filterMode){
        RecordsContainerDto container = recordService.findAllRecords(filterMode);
        model.addAttribute("numberOfDoneRecords", container.getNumberOfDoneRecords());
        model.addAttribute("numberOfActiveRecords", container.getNumberOfActiveRecords());
        model.addAttribute("records", container.getRecords());
        return "main-page";
    }

    @RequestMapping(value = "/add-record", method = RequestMethod.POST)
    public String addRecord(@RequestParam String title){
        recordService.saveRecord(title);
        return "redirect:/home";
    }

    @RequestMapping(value = "/make-record-done", method = RequestMethod.POST)
    public String makeRecordDone(@RequestParam int id,
                                 @RequestParam(name = "filter", required = false) String filterMode){
        recordService.updateRecordStatus(id, RecordStatus.DONE);
        return "redirect:/home" + (filterMode != null && !filterMode.isBlank() ? "?filter=" + filterMode: "");
    }

    @RequestMapping(value = "/delete-record", method = RequestMethod.POST)
    public String deleteRecord(@RequestParam int id,
                               @RequestParam(name = "filter", required = false) String filterMode){
        recordService.deleteRecord(id);
        return "redirect:/home" + (filterMode != null && !filterMode.isBlank() ? "?filter=" + filterMode: "");
    }
}
