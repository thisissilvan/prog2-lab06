package ch.zhaw.prog2.functional.streaming;

import ch.zhaw.prog2.functional.streaming.humanresource.Employee;
import ch.zhaw.prog2.functional.streaming.humanresource.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static ch.zhaw.prog2.functional.streaming.CompanyTest.EMPLOYEE_COUNT;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class is for all test methods written by students for easier review by lecturers.
 * In a real application these test would be in the class CompanyTest.
 */
public class CompanyTestStudent {
    private Company testCompany;

    @BeforeEach
    void setUp() {
        Random random = new Random(CompanyTest.RANDOM_SEED);
        CompanySupplier companySupplier = new CompanySupplier(random, EMPLOYEE_COUNT);
        testCompany = companySupplier.get();
    }

    /*
     * Aufgabe c)
     */
    @Test
    void getEmployeesByPredicate() {
        List<Employee> isFemale = testCompany.getEmployeesByPredicate(Person::isFemale);
        List<Employee> allEmployee = testCompany.getAllEmployees();
        int notFemale = allEmployee.size() - isFemale.size();
        assertNotNull(isFemale, "Method has to be implemented.");
        assertTrue(allEmployee.size() >= 400, "default company has at least 400 working employees");
        assertEquals(EMPLOYEE_COUNT, notFemale + isFemale.size());
    }


}
