package vserdiuk.tech.test.util;

import vserdiuk.tech.test.model.BuySellFlag;
import vserdiuk.tech.test.model.Instruction;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AppUtil {

    public static boolean isSundayStart(Currency currency) {
        if (currency.equals(Currency.getInstance("AED")) || currency.equals(Currency.getInstance("SAR"))) {
            return true;
        } else {
            return false;
        }
    }

    public static LocalDate getLocalDate(boolean isSundayStart, LocalDate localDate) {
        LocalDate updatedLocalDate = null;
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        if (isSundayStart) {
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                updatedLocalDate = localDate.plusDays(2l);
            }
            if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                updatedLocalDate = localDate.plusDays(1l);
            }
            if (!dayOfWeek.equals(DayOfWeek.FRIDAY) && !dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                updatedLocalDate = localDate;
            }
        } else {
            if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                updatedLocalDate = localDate.plusDays(2l);
            }
            if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
                updatedLocalDate = localDate.plusDays(1l);
            }
            if (!dayOfWeek.equals(DayOfWeek.SATURDAY) && !dayOfWeek.equals(DayOfWeek.SUNDAY)) {
                updatedLocalDate = localDate;
            }
        }
        return updatedLocalDate;
    }

    public static Map<LocalDate, BigDecimal> countDailyTradingAmount(List<Instruction> instructions, BuySellFlag buySellFlag) {

        List<Instruction> instructionsByFlagList = instructions.stream().filter(instruction ->
                instruction.getBuySellFlag().equals(buySellFlag)).collect(Collectors.toList());

        Map<LocalDate, BigDecimal> dailyTradingAmount = new HashMap<>();
        for (Instruction instructionsByFlag : instructionsByFlagList) {
            LocalDate date = instructionsByFlag.getSettlementDate();
            BigDecimal sum = BigDecimal.ZERO;
            for (Instruction instruction : instructionsByFlagList) {
                if (instruction.getSettlementDate().equals(date)) {
                    sum = sum.add(countAmount(instruction));
                }
            }
            dailyTradingAmount.put(date, sum);
        }
        return dailyTradingAmount;
    }

    public static BigDecimal countAmount(Instruction instruction) {
        BigDecimal pricePerUnit = instruction.getPricePerUnit();
        BigDecimal agreedFx = instruction.getAgreedFx();
        long units = instruction.getUnits();
        return pricePerUnit.multiply(agreedFx).multiply(new BigDecimal(units));
    }

}
