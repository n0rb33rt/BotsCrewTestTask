package com.norbert.botscrew.service;

import com.norbert.botscrew.entity.Degree;
import com.norbert.botscrew.entity.Department;
import com.norbert.botscrew.entity.Lector;
import com.norbert.botscrew.exception.DepartmentNotFoundException;
import com.norbert.botscrew.exception.HeadOfDepartmentNotFoundException;
import com.norbert.botscrew.repository.DepartmentRepository;
import com.norbert.botscrew.repository.LectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityService {
    private final LectorRepository lectorRepository;

    private final DepartmentRepository departmentRepository;

    public String getHeadOfDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        if (department == null) {
            throw new DepartmentNotFoundException(departmentName);
        }
        if (department.getHeadOfDepartment() == null) {
            throw new HeadOfDepartmentNotFoundException(departmentName);
        }
        return department.getHeadOfDepartment().getName();
    }

    public Map<Degree, Long> getDepartmentStatistics(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        if (department == null) {
            throw new DepartmentNotFoundException(departmentName);
        }
        return department.getLectors().stream()
                .collect(Collectors.groupingBy(Lector::getDegree, Collectors.counting()));
    }

    public double getAverageSalary(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        if (department == null) {
            throw new DepartmentNotFoundException(departmentName);
        }
        if (department.getLectors().isEmpty()) {
            return 0;
        }
        return department.getLectors().stream()
                .mapToDouble(Lector::getSalary)
                .average().orElse(0);
    }

    public long getEmployeeCount(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        if (department == null) {
            throw new DepartmentNotFoundException(departmentName);
        }
        return department.getLectors().size();
    }

    public List<String> globalSearch(String template) {
        return lectorRepository.findByNameContainingIgnoreCase(template)
                .stream().map(Lector::getName)
                .collect(Collectors.toList());
    }
}
