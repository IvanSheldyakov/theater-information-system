package com.db.theaterinformationsystem.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Period;

@Converter(autoApply = true)
public class PeriodToStringConverter implements AttributeConverter<Period, String> {

    @Override
    public String convertToDatabaseColumn(Period period) {
        return period == null ? null : period.toString();
    }

    @Override
    public Period convertToEntityAttribute(String periodStr) {
        return periodStr == null || periodStr.isEmpty() ? null : Period.parse(periodStr);
    }
}