package com.norbert.botscrew;

import com.norbert.botscrew.entity.Degree;
import com.norbert.botscrew.exception.DepartmentNotFoundException;
import com.norbert.botscrew.exception.HeadOfDepartmentNotFoundException;
import com.norbert.botscrew.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class BotsCrewTestTaskApplication implements CommandLineRunner {
    private final UniversityService universityService;

    public static void main(String[] args) {
        SpringApplication.run(BotsCrewTestTaskApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter command:");
                String command = scanner.nextLine();
                processCommand(command);
            } catch (DepartmentNotFoundException | HeadOfDepartmentNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void processCommand(String command) {
        if (command.startsWith("Who is head of department")) {
            handleHeadOfDepartmentCommand(command);
        } else if (command.startsWith("Show") && command.contains("statistics")) {
            handleDepartmentStatisticsCommand(command);
        } else if (command.startsWith("Show the average salary for the department")) {
            handleAverageSalaryCommand(command);
        } else if (command.startsWith("Show count of employee for")) {
            handleEmployeeCountCommand(command);
        } else if (command.startsWith("Global search by")) {
            handleGlobalSearchCommand(command);
        } else {
            System.out.println("Unknown command");
        }
    }

    private void handleHeadOfDepartmentCommand(String command) {
        String departmentName = command.substring("Who is head of department".length()).trim();
        String headOfDepartment = universityService.getHeadOfDepartment(departmentName);
        System.out.println("Head of " + departmentName + " department is " + headOfDepartment);
    }

    private void handleDepartmentStatisticsCommand(String command) {
        String departmentName = command.substring("Show".length(), command.indexOf("statistics")).trim();
        Map<Degree, Long> stats = universityService.getDepartmentStatistics(departmentName);
        System.out.println("assistants - " + stats.getOrDefault(Degree.ASSISTANT, 0L));
        System.out.println("associate professors - " + stats.getOrDefault(Degree.ASSOCIATE_PROFESSOR, 0L));
        System.out.println("professors - " + stats.getOrDefault(Degree.PROFESSOR, 0L));
    }

    private void handleAverageSalaryCommand(String command) {
        String departmentName = command.substring("Show the average salary for the department".length()).trim();
        double averageSalary = universityService.getAverageSalary(departmentName);
        if (averageSalary > 0) {
            System.out.println("The average salary of " + departmentName + " is " + averageSalary);
        } else {
            System.out.println("No employees or department found");
        }
    }

    private void handleEmployeeCountCommand(String command) {
        String departmentName = command.substring("Show count of employee for".length()).trim();
        long employeeCount = universityService.getEmployeeCount(departmentName);
        System.out.println("Employee count: " + employeeCount);
    }

    private void handleGlobalSearchCommand(String command) {
        String template = command.substring("Global search by".length()).trim();
        List<String> results = universityService.globalSearch(template);
        if (!results.isEmpty()) {
            System.out.println(String.join(", ", results));
        } else {
            System.out.println("No matches found");
        }
    }
}
