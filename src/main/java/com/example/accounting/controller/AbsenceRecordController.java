package com.example.accounting.controller;

import com.example.accounting.entity.AbsenceRecord;
import com.example.accounting.entity.Reason;
import com.example.accounting.service.AbsenceRecordService;
import com.example.accounting.service.ReasonService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AbsenceRecordController {

    private final AbsenceRecordService absenceRecordService;
    private final ReasonService reasonService;

    public AbsenceRecordController(AbsenceRecordService absenceRecordService, ReasonService reasonService) {
        this.absenceRecordService = absenceRecordService;
        this.reasonService = reasonService;
    }

    @GetMapping("/")
    public String showPage() {
        return "index";
    }



    @GetMapping("/get-absence-records")
    public String getAllAbsenceRecords(Model model) {
        List<AbsenceRecord> records = absenceRecordService.getAllAbsenceRecords();
        List<Reason> reasons = reasonService.getAllReasons(); // Получаем все значения из таблицы Reason

        model.addAttribute("records", records);
        model.addAttribute("reasons", reasons); // Передаем список Reason в модель

        return "records-list"; // Возвращает имя шаблона records-list.html
    }


    @GetMapping("/get-absence-form")
    public String getAbsenceForm(@RequestParam(required = false) Long id, Model model) {
        List<Reason> reasons = reasonService.getAllReasons();
        model.addAttribute("reasons", reasons);

        if (id != null) {
            AbsenceRecord record = absenceRecordService.getAbsenceRecordById(id);
            model.addAttribute("record", record);
            return "edit-absence-form";
        } else {
            AbsenceRecord newRecord = new AbsenceRecord();
            model.addAttribute("record", newRecord);
            return "absence-form";
        }
    }



    @PostMapping("/save-absence-record")
    public String saveAbsenceRecord(@ModelAttribute("record") AbsenceRecord record) {
        absenceRecordService.addAbsenceRecord(record);
        return "redirect:/get-absence-records";
    }

    @PostMapping("/update-absence-record/{id}")
    public String updateAbsenceRecord(@PathVariable Long id, @ModelAttribute @DateTimeFormat(pattern = "yyyy-MM-dd") AbsenceRecord record) {
        absenceRecordService.updateAbsenceRecord(id, record);
        return "redirect:/get-absence-records";
    }



    @PostMapping("/delete-absence-record")
    @ResponseBody
    public void deleteAbsenceRecord(@RequestParam Long id) {
        absenceRecordService.deleteAbsenceRecord(id);
    }

}
