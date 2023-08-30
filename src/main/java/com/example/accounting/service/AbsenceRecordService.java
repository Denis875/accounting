package com.example.accounting.service;

import com.example.accounting.entity.AbsenceRecord;
import com.example.accounting.repository.AbsenceRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AbsenceRecordService {
    private final AbsenceRecordRepository absenceRecordRepository;

    public AbsenceRecordService(AbsenceRecordRepository absenceRecordRepository) {
        this.absenceRecordRepository = absenceRecordRepository;
    }

    public AbsenceRecord getAbsenceRecordById(Long id) {
        return absenceRecordRepository.findById(id);
    }

    public List<AbsenceRecord> getAllAbsenceRecords() {
        return absenceRecordRepository.getAllAbsenceRecords();
    }

    public void addAbsenceRecord(AbsenceRecord record) {
        absenceRecordRepository.createAbsenceRecord(record);
    }

    public void updateAbsenceRecord(Long id, AbsenceRecord record) {
        absenceRecordRepository.updateAbsenceRecord(record, id);
    }

    public void deleteAbsenceRecord(Long id) {
        absenceRecordRepository.deleteAbsenceRecord(id);
    }
}
