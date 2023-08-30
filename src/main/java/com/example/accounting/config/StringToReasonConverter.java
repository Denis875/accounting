package com.example.accounting.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.example.accounting.entity.Reason;
import com.example.accounting.service.ReasonService;

@Component
public class StringToReasonConverter implements Converter<String, Reason> {

    private final ReasonService reasonService;

    public StringToReasonConverter(ReasonService reasonService) {
        this.reasonService = reasonService;
    }

    @Override
    public Reason convert(String source) {
        Long id = Long.valueOf(source);
        return reasonService.getReasonById(id);
    }
}
