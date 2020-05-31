package ch.zhaw.prog2.functional.streaming.finance;

import ch.zhaw.prog2.functional.streaming.Company;
import ch.zhaw.prog2.functional.streaming.CompanySupplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static ch.zhaw.prog2.functional.streaming.finance.PayrollCreatorTest.EMPLOYEE_COUNT;
import static ch.zhaw.prog2.functional.streaming.finance.PayrollCreatorTest.RANDOM_SEED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayrollCreatorTestStudent {

    @Test
    void payrollAmountByCurrency() {
        Company company = new CompanySupplier(new Random(RANDOM_SEED), EMPLOYEE_COUNT).get();
        PayrollCreator payrollCreator = new PayrollCreator(company);

        int actualSum = PayrollCreator.payrollAmountByCurrency(payrollCreator.getPayrollForAll()).stream()
            .mapToInt(CurrencyAmount::getAmount)
            .sum();

        int expectedSum = payrollCreator.getPayrollForAll().getPaymentList().stream()
            .mapToInt(Payment -> Payment.getCurrencyAmount().getAmount())
            .sum();

        assertEquals(expectedSum, actualSum);

    }

}
