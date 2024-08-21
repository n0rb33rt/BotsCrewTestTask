package com.norbert.botscrew.exception;

public class HeadOfDepartmentNotFoundException extends RuntimeException {
    public HeadOfDepartmentNotFoundException(String departmentName) {
        super("No head of department found for department '" + departmentName + "'.");
    }
}