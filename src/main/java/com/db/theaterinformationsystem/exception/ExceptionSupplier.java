package com.db.theaterinformationsystem.exception;

import java.util.function.Supplier;

public final class ExceptionSupplier {

    public static final Supplier<DataNotFoundException> DATA_NOT_FOUND =
            () -> new DataNotFoundException("Данные не найдены");
}
