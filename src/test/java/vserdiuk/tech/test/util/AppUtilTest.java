package vserdiuk.tech.test.util;

import org.junit.Test;
import vserdiuk.tech.test.model.BuySellFlag;
import vserdiuk.tech.test.model.Instruction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.Month;
import java.util.Currency;

import static org.junit.Assert.*;

public class AppUtilTest {

    @Test
    public void isSundayStart() {
        assertEquals(true, AppUtil.isSundayStart(Currency.getInstance("AED")));
        assertEquals(true, AppUtil.isSundayStart(Currency.getInstance("SAR")));
        assertEquals(false, AppUtil.isSundayStart(Currency.getInstance("UAH")));
        assertEquals(false, AppUtil.isSundayStart(Currency.getInstance("USD")));
    }

    @Test
    public void getLocalDate() {
        LocalDate localDate1 = LocalDate.of(2018, Month.JULY, 4);
        boolean isSundayStart1 = true;
        assertEquals(LocalDate.of(2018, Month.JULY, 4), AppUtil.getLocalDate(isSundayStart1, localDate1));

        LocalDate localDate2 = LocalDate.of(2018, Month.JULY, 13);
        boolean isSundayStart2 = true;
        assertEquals(LocalDate.of(2018, Month.JULY, 15), AppUtil.getLocalDate(isSundayStart2, localDate2));

        LocalDate localDate3 = LocalDate.of(2018, Month.JULY, 14);
        boolean isSundayStart3 = true;
        assertEquals(LocalDate.of(2018, Month.JULY, 15), AppUtil.getLocalDate(isSundayStart3, localDate3));

        LocalDate localDate4 = LocalDate.of(2018, Month.JULY, 4);
        boolean isSundayStart4 = false;
        assertEquals(LocalDate.of(2018, Month.JULY, 4), AppUtil.getLocalDate(isSundayStart4, localDate4));

        LocalDate localDate5 = LocalDate.of(2018, Month.JULY, 14);
        boolean isSundayStart5 = false;
        assertEquals(LocalDate.of(2018, Month.JULY, 16), AppUtil.getLocalDate(isSundayStart5, localDate5));

        LocalDate localDate6 = LocalDate.of(2018, Month.JULY, 15);
        boolean isSundayStart6 = false;
        assertEquals(LocalDate.of(2018, Month.JULY, 16), AppUtil.getLocalDate(isSundayStart6, localDate6));

    }
}