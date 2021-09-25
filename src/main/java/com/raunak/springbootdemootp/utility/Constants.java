package com.raunak.springbootdemootp.utility;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Constants {
    public enum Status {
        // ORDER MUST NOT BE CHANGED ELSE IT WILL BREAK THE DB. THE ENUM IS DEFINED AS ORDINALS.
        ACTIVE, // 0
        INACTIVE,   // 1
        INITIALIZING,
        DELETED,    //  used only for understanding purposes. IS_DELETED Attribute should be used to persist deletion.
        FAILED
    }

    public enum ExceptionTypes {
        ConstraintViolationException,
        ChildNotDeleted,
        TransientPropertyValueException,
    }

}
