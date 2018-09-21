package ltu.Tests;

import static ltu.CalendarFactory.getCalendar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ltu.ICalendar;
import ltu.PaymentImpl;
import org.junit.*;

import java.io.IOException;

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
        ICalendar calendar = getCalendar();
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

}
