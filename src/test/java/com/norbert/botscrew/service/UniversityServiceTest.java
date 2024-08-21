package com.norbert.botscrew.service;

import com.norbert.botscrew.entity.Degree;
import com.norbert.botscrew.entity.Department;
import com.norbert.botscrew.entity.Lector;
import com.norbert.botscrew.exception.DepartmentNotFoundException;
import com.norbert.botscrew.exception.HeadOfDepartmentNotFoundException;
import com.norbert.botscrew.repository.DepartmentRepository;
import com.norbert.botscrew.repository.LectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UniversityServiceTest {
    @Mock
    private LectorRepository lectorRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private UniversityService universityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getHeadOfDepartment_ShouldReturnHeadOfDepartmentName() {
        Department department = new Department();
        Lector head = new Lector();
        head.setName("John Doe");
        department.setHeadOfDepartment(head);

        when(departmentRepository.findByName("Engineering")).thenReturn(department);

        String result = universityService.getHeadOfDepartment("Engineering");

        assertEquals("John Doe", result);
        verify(departmentRepository, times(1)).findByName("Engineering");
    }

    @Test
    void getHeadOfDepartment_ShouldThrowDepartmentNotFoundException() {
        when(departmentRepository.findByName("Unknown")).thenReturn(null);

        assertThrows(DepartmentNotFoundException.class, () -> universityService.getHeadOfDepartment("Unknown"));

        verify(departmentRepository, times(1)).findByName("Unknown");
    }

    @Test
    void getHeadOfDepartment_ShouldThrowHeadOfDepartmentNotFoundException() {
        Department department = new Department();

        when(departmentRepository.findByName("Engineering")).thenReturn(department);

        assertThrows(HeadOfDepartmentNotFoundException.class, () -> universityService.getHeadOfDepartment("Engineering"));

        verify(departmentRepository, times(1)).findByName("Engineering");
    }

    @Test
    void getDepartmentStatistics_ShouldReturnStatistics() {
        Department department = new Department();

        Lector assistant = new Lector();
        assistant.setDegree(Degree.ASSISTANT);

        Lector professor = new Lector();
        professor.setDegree(Degree.PROFESSOR);

        department.setLectors(List.of(assistant, professor, professor));

        when(departmentRepository.findByName("Engineering")).thenReturn(department);

        Map<Degree, Long> stats = universityService.getDepartmentStatistics("Engineering");

        assertEquals(1, stats.get(Degree.ASSISTANT));
        assertEquals(2, stats.get(Degree.PROFESSOR));
        verify(departmentRepository, times(1)).findByName("Engineering");
    }

    @Test
    void getDepartmentStatistics_ShouldThrowDepartmentNotFoundException() {
        when(departmentRepository.findByName("Unknown")).thenReturn(null);

        assertThrows(DepartmentNotFoundException.class, () -> universityService.getDepartmentStatistics("Unknown"));

        verify(departmentRepository, times(1)).findByName("Unknown");
    }

    @Test
    void getAverageSalary_ShouldReturnAverageSalary() {
        Department department = new Department();

        Lector lecturer1 = new Lector();
        lecturer1.setSalary(1000);

        Lector lecturer2 = new Lector();
        lecturer2.setSalary(2000);

        department.setLectors(List.of(lecturer1, lecturer2));

        when(departmentRepository.findByName("Engineering")).thenReturn(department);

        double averageSalary = universityService.getAverageSalary("Engineering");

        assertEquals(1500, averageSalary);
        verify(departmentRepository, times(1)).findByName("Engineering");
    }

    @Test
    void getAverageSalary_ShouldReturnZeroForNoEmployees() {
        Department department = new Department();
        department.setLectors(Collections.emptyList());

        when(departmentRepository.findByName("Engineering")).thenReturn(department);

        double averageSalary = universityService.getAverageSalary("Engineering");

        assertEquals(0, averageSalary);
        verify(departmentRepository, times(1)).findByName("Engineering");
    }

    @Test
    void getAverageSalary_ShouldThrowDepartmentNotFoundException() {
        when(departmentRepository.findByName("Unknown")).thenReturn(null);

        assertThrows(DepartmentNotFoundException.class, () -> universityService.getAverageSalary("Unknown"));

        verify(departmentRepository, times(1)).findByName("Unknown");
    }

    @Test
    void getEmployeeCount_ShouldReturnEmployeeCount() {
        Department department = new Department();
        department.setLectors(List.of(new Lector(), new Lector()));

        when(departmentRepository.findByName("Engineering")).thenReturn(department);

        long count = universityService.getEmployeeCount("Engineering");

        assertEquals(2, count);
        verify(departmentRepository, times(1)).findByName("Engineering");
    }

    @Test
    void getEmployeeCount_ShouldThrowDepartmentNotFoundException() {
        when(departmentRepository.findByName("Unknown")).thenReturn(null);

        assertThrows(DepartmentNotFoundException.class, () -> universityService.getEmployeeCount("Unknown"));

        verify(departmentRepository, times(1)).findByName("Unknown");
    }

    @Test
    void globalSearch_ShouldReturnMatchingLectors() {
        Lector lector1 = new Lector();
        lector1.setName("John Smith");

        Lector lector2 = new Lector();
        lector2.setName("Johnny Depp");

        when(lectorRepository.findByNameContainingIgnoreCase("John")).thenReturn(List.of(lector1, lector2));

        List<String> results = universityService.globalSearch("John");

        assertEquals(2, results.size());
        assertTrue(results.contains("John Smith"));
        assertTrue(results.contains("Johnny Depp"));
        verify(lectorRepository, times(1)).findByNameContainingIgnoreCase("John");
    }

    @Test
    void globalSearch_ShouldReturnEmptyListWhenNoMatch() {
        when(lectorRepository.findByNameContainingIgnoreCase("Unknown")).thenReturn(Collections.emptyList());

        List<String> results = universityService.globalSearch("Unknown");

        assertTrue(results.isEmpty());
        verify(lectorRepository, times(1)).findByNameContainingIgnoreCase("Unknown");
    }
}
