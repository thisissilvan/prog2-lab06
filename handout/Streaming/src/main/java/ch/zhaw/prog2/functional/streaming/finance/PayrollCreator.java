package ch.zhaw.prog2.functional.streaming.finance;

import ch.zhaw.prog2.functional.streaming.Company;
import ch.zhaw.prog2.functional.streaming.humanresource.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class PayrollCreator {
    private final Company company;

    public PayrollCreator(Company company) {
        this.company = company;
    }

    /*
     * Aufgabe d) - Test dazu exisitert in PayrollCreatorTest OK
     */
    public Payroll getPayrollForAll() {
        Payroll payroll = new Payroll();
        payroll.addPayments(company.getAllEmployees().stream()
        .filter(Employee::isWorkingForCompany)
        .map(employee -> {
            Payment payment = new Payment();
            payment.setBeneficiary(employee);
            payment.setTargetAccount(employee.getAccount());
            payment.setCurrencyAmount(employee.getYearlySalary());
            return payment;
        })
            .collect(Collectors.toList()));
        return payroll;
    }

    /*
     * Aufgabe e) - Test dazu existiert in PayrollCreatorTest
     */
    public static int payrollValueCHF(Payroll payroll) {
        return payroll.getPaymentList().stream()
            .mapToInt(payment -> payment.getCurrencyAmount().getAmount())
            .sum();
    }

    /*
     * Aufgabe f) - schreiben Sie einen eigenen Test in PayrollCreatorTestStudent OK
     */
    public static List<CurrencyAmount> payrollAmountByCurrency(Payroll payroll) {
        return payroll.getPaymentList().stream()
            .map(Payment::getCurrencyAmount)
            .collect(Collectors.groupingBy(CurrencyAmount::getCurrency, Collectors.summingInt(CurrencyAmount::getAmount)))
            .entrySet().stream()
            .map(entry -> new CurrencyAmount(entry.getValue(), entry.getKey()))
            .collect(Collectors.toList());
    }

}
