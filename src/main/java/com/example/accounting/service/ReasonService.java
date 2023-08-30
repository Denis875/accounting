package com.example.accounting.service;

import com.example.accounting.entity.Reason;
import com.example.accounting.repository.ReasonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReasonService {

    private final ReasonRepository reasonRepository;

    public ReasonService(ReasonRepository reasonRepository) {
        this.reasonRepository = reasonRepository;
    }

    public List<Reason> getAllReasons() {
        return reasonRepository.getAllReasons();
    }

    public Reason getReasonById(Long id) {
        return reasonRepository.findById(id);
    }
}
