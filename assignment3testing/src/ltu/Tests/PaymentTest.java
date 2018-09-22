package ltu.Tests;

import static ltu.CalendarFactory.getCalendar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ltu.ICalendar;
import ltu.PaymentImpl;
import org.junit.*;

import java.io.IOException;
import java.util.Calendar;

public class PaymentTest
{
    private final int FULL_LOAN = 7088;
    private final int HALF_LOAN = 3564;
    private final int ZERO_LOAN = 0;
    private final int FULL_SUBSIDY = 2816;
    private final int HALF_SUBSIDY = 1396;
    private final int ZERO_SUBSIDY = 0;
    private final int FULLTIME_INCOME = 85813;
    private final int PARTTIME_INCOME = 128722;

    private PaymentImpl payment;

    @Before
    public void init() throws IOException {
        ICalendar calendar = () -> {
            Calendar cal = Calendar.getInstance();
            cal.set(2016, Calendar.JANUARY, 10);
            return cal.getTime();
        };
        payment = new PaymentImpl(calendar);
    }

    @Test(expected = NumberFormatException.class)
    public void constructorNumberFormatExceptionTest() throws NumberFormatException, IOException {
        ICalendar calendar = getCalendar();
        String rules = "";
        PaymentImpl payment = new PaymentImpl(calendar, rules);
    }

    @Test
    public void constructorTest() throws IOException {
        ICalendar calendar = getCalendar();
        PaymentImpl payment = new PaymentImpl(calendar);
        assertNotNull(payment);
    }

    @Test
    public void ageBetween20And47LowIncomeStudyRate100CompletionHighTest() {
        int monthlyAmount = payment.getMonthlyAmount("19861125-1234", 85813, 100, 50);
        assertEquals(FULL_LOAN+FULL_SUBSIDY , monthlyAmount);
    }

    @Test
    public void ageBetween20And47HighIncomeStudyRate100CompletionHighTest() {
        int monthlyAmount = payment.getMonthlyAmount("19861125-1234", 85814, 100, 50);
        assertEquals(ZERO_SUBSIDY+ZERO_LOAN , monthlyAmount);
    }

    @Test
    public void ageBetween20And47HighIncomeStudyRate50CompletionHighTest() {
        int monthlyAmount = payment.getMonthlyAmount("19861125-1234", 128723, 50, 50);
        assertEquals(ZERO_SUBSIDY+ZERO_LOAN , monthlyAmount);
    }

    @Test
    public void ageBetween20And47LowIncomeStudyRate50CompletionHighTest() {
        int monthlyAmount = payment.getMonthlyAmount("19861125-1234", 128722, 50, 50);
        assertEquals(HALF_LOAN+HALF_SUBSIDY , monthlyAmount);
    }

    @Test
    public void ageBetween20And47LowIncomeStudyRate0CompletionHighTest() {
        int monthlyAmount = payment.getMonthlyAmount("19861125-1234", 50000, 0, 50);
        assertEquals(ZERO_LOAN+ZERO_SUBSIDY , monthlyAmount);
    }

    @Test
    public void ageBetween20And47LowIncomeStudyRate100CompletionLowTest() {
        int monthlyAmount = payment.getMonthlyAmount("19861125-1234", 85813, 100, 0);
        assertEquals(ZERO_SUBSIDY+ZERO_LOAN , monthlyAmount);
    }

    @Test
    public void ageBetween20And47HighIncomeStudyRate100CompletionLowTest() {
        int monthlyAmount = payment.getMonthlyAmount("19861125-1234", 85814, 100, 0);
        assertEquals(ZERO_SUBSIDY+ZERO_LOAN , monthlyAmount);
    }

    @Test
    public void ageBetween20And47HighIncomeStudyRate50CompletionLowTest() {
        int monthlyAmount = payment.getMonthlyAmount("19861125-1234", 128723, 50, 0);
        assertEquals(ZERO_SUBSIDY+ZERO_LOAN , monthlyAmount);
    }

    @Test
    public void ageBetween20And47LowIncomeStudyRate50CompletionLowTest() {
        int monthlyAmount = payment.getMonthlyAmount("19861125-1234", 128722, 50, 0);
        assertEquals(ZERO_SUBSIDY+ZERO_LOAN , monthlyAmount);
    }

    @Test
    public void ageBetween20And47LowIncomeStudyRate0CompletionLowTest() {
        int monthlyAmount = payment.getMonthlyAmount("19861125-1234", 50000, 0, 0);
        assertEquals(ZERO_LOAN+ZERO_SUBSIDY , monthlyAmount);
    }

    @Test
    public void ageBetween47And56LowIncomeStudyRate100CompletionHighTest() {
        int monthlyAmount = payment.getMonthlyAmount("19691125-1234", 85813, 100, 50);
        assertEquals(ZERO_LOAN+FULL_SUBSIDY , monthlyAmount);
    }

    @Test
    public void ageBetween47And56HighIncomeStudyRate100CompletionHighTest() {
        int monthlyAmount = payment.getMonthlyAmount("19691125-1234", 85814, 100, 50);
        assertEquals(ZERO_SUBSIDY+ZERO_LOAN , monthlyAmount);
    }

    @Test
    public void ageBetween47And56HighIncomeStudyRate50CompletionHighTest() {
        int monthlyAmount = payment.getMonthlyAmount("19691125-1234", 128723, 50, 50);
        assertEquals(ZERO_SUBSIDY+ZERO_LOAN , monthlyAmount);
    }

    @Test
    public void ageBetween47And56LowIncomeStudyRate50CompletionHighTest() {
        int monthlyAmount = payment.getMonthlyAmount("19691125-1234", 128722, 50, 50);
        assertEquals(ZERO_LOAN+HALF_SUBSIDY , monthlyAmount);
    }

    @Test
    public void ageBetween47And56LowIncomeStudyRate0CompletionHighTest() {
        int monthlyAmount = payment.getMonthlyAmount("19691125-1234", 50000, 0, 50);
        assertEquals(ZERO_LOAN+ZERO_SUBSIDY , monthlyAmount);
    }

    @Test
    public void ageBetween47And56LowIncomeStudyRate100CompletionLowTest() {
        int monthlyAmount = payment.getMonthlyAmount("19691125-1234", 85813, 100, 0);
        assertEquals(ZERO_LOAN+ZERO_SUBSIDY , monthlyAmount);
    }

    @Test
    public void ageBetween47And56HighIncomeStudyRate100CompletionLowTest() {
        int monthlyAmount = payment.getMonthlyAmount("19691125-1234", 85814, 100, 0);
        assertEquals(ZERO_SUBSIDY+ZERO_LOAN , monthlyAmount);
    }

    @Test
    public void ageBetween47And56HighIncomeStudyRate50CompletionLowTest() {
        int monthlyAmount = payment.getMonthlyAmount("19691125-1234", 128723, 50, 0);
        assertEquals(ZERO_SUBSIDY + ZERO_LOAN, monthlyAmount);
    }

    @Test
    public void nextPaymentDaySundayLastDayTest() throws IOException {
        ICalendar calendar = () -> {
            Calendar cal = Calendar.getInstance();
            cal.set(2016, Calendar.JANUARY, 10);
            return cal.getTime();
        };
        PaymentImpl payment = new PaymentImpl(calendar);
        String result = "20160129";
        String day = payment.getNextPaymentDay();
        assertEquals(result, day);
    }

    @Test
    public void nextPaymentDaySaturdayLastDayTest() throws IOException {
        ICalendar calendar = () -> {
            Calendar cal = Calendar.getInstance();
            cal.set(2016, Calendar.APRIL, 10);
            return cal.getTime();
        };
        PaymentImpl payment = new PaymentImpl(calendar);
        String result = "20160429";
        String day = payment.getNextPaymentDay();
        assertEquals(result, day);
    }

    @Test
    public void nextPaymentDayWeekdayLastDayTest() throws IOException {
        ICalendar calendar = () -> {
            Calendar cal = Calendar.getInstance();
            cal.set(2016, Calendar.FEBRUARY, 10);
            return cal.getTime();
        };
        PaymentImpl payment = new PaymentImpl(calendar);
        String result = "20160229";
        String day = payment.getNextPaymentDay();
        assertEquals(result, day);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidInputIdTest() throws IllegalArgumentException {
        payment.getMonthlyAmount(null, 0, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidInputAmountTest() throws IllegalArgumentException {
        payment.getMonthlyAmount("19950315-1234", -1, 0, 0);
    }

    @Test
    public void ageBetween47And56LowIncomeStudyRate50CompletionLowTest() {
        int monthlyAmount = payment.getMonthlyAmount("19691125-1234", 128722, 50, 0);
        assertEquals(ZERO_LOAN+ZERO_SUBSIDY , monthlyAmount);
    }

    @Test
    public void ageBetween47And56LowIncomeStudyRate0CompletionLowTest() {
        int monthlyAmount = payment.getMonthlyAmount("19691125-1234", 50000, 0, 0);
        assertEquals(ZERO_LOAN+ZERO_SUBSIDY , monthlyAmount);
    }


    @Test(expected = IllegalArgumentException.class)
    public void invalidInputStudyRateTest() throws IllegalArgumentException {
        payment.getMonthlyAmount("19950315-1234", 0, -1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidInputCompletionTest() throws IllegalArgumentException {
        payment.getMonthlyAmount("19950315-1234", 0, 0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidPersonTest() throws IllegalArgumentException {
        payment.getMonthlyAmount("", 0, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidPersonTooLongIdTest() throws IllegalArgumentException {
        payment.getMonthlyAmount("azertyuioazertyuioar", 0, 0, 0);
    }

    @Test
    public void ageBelow20Test() {
        int amount = 0;
        String personId = "19970425-1234";
        assertEquals(amount, payment.getMonthlyAmount(personId, 0, 0, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, FULLTIME_INCOME, 0, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, PARTTIME_INCOME, 0, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, 0, 0, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, FULLTIME_INCOME, 0, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, PARTTIME_INCOME, 0, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, 0, 50, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, FULLTIME_INCOME, 50, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, PARTTIME_INCOME, 50, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, 0, 50, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, FULLTIME_INCOME, 50, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, PARTTIME_INCOME, 50, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, 0, 100, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, FULLTIME_INCOME, 100, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, PARTTIME_INCOME, 100, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, 0, 100, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, FULLTIME_INCOME, 100, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, PARTTIME_INCOME, 100, 50));
    }

    @Test
    public void ageAbove56Test() {
        int amount = 0;
        String personId = "19590101-1234";
        assertEquals(amount, payment.getMonthlyAmount(personId, 0, 0, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, FULLTIME_INCOME, 0, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, PARTTIME_INCOME, 0, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, 0, 0, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, FULLTIME_INCOME, 0, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, PARTTIME_INCOME, 0, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, 0, 50, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, FULLTIME_INCOME, 50, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, PARTTIME_INCOME, 50, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, 0, 50, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, FULLTIME_INCOME, 50, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, PARTTIME_INCOME, 50, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, 0, 100, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, FULLTIME_INCOME, 100, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, PARTTIME_INCOME, 100, 0));
        assertEquals(amount, payment.getMonthlyAmount(personId, 0, 100, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, FULLTIME_INCOME, 100, 50));
        assertEquals(amount, payment.getMonthlyAmount(personId, PARTTIME_INCOME, 100, 50));
    }
}
