package com.norbert.botscrew.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String departmentName) {
        super("Department '" + departmentName + "' not found.");
    }
}