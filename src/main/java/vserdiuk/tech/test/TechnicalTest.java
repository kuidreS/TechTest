package vserdiuk.tech.test;

import vserdiuk.tech.test.model.BuySellFlag;
import vserdiuk.tech.test.model.Instruction;
import vserdiuk.tech.test.report.InstructionReporter;
import vserdiuk.tech.test.report.Reporter;
import vserdiuk.tech.test.util.AppUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Description
 * Create a report that shows:
 *  - Amount in USD settled incoming everyday
 *  - Amount in USD settled outgoing everyday
 *  - Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest amount for a buy
 *  instruction, then foo is rank 1 for outgoing
 * */

public class TechnicalTest {
    public static void main(String[] args) {
        List<Instruction> instructions = new ArrayList<>();

        Instruction i1 = new Instruction.InstuctionBuilder()
                .setEntity("A")
                .setBuySellFlag(BuySellFlag.B)
                .setAgreedFx(new BigDecimal(0.2))
                .setCurrency(Currency.getInstance("UAH"))
                .setInstructionDate(LocalDate.of(2018, Month.JULY, 4))
                .setSettlementDate(LocalDate.of(2018, Month.JULY, 5))
                .setUnits(100)
                .setPricePerUnit(new BigDecimal(10.5))
                .build();

        Instruction i2 = new Instruction.InstuctionBuilder()
                .setEntity("A")
                .setBuySellFlag(BuySellFlag.B)
                .setAgreedFx(new BigDecimal(0.2))
                .setCurrency(Currency.getInstance("UAH"))
                .setInstructionDate(LocalDate.of(2018, Month.JULY, 4))
                .setSettlementDate(LocalDate.of(2018, Month.JULY, 29))
                .setUnits(100)
                .setPricePerUnit(new BigDecimal(10.5))
                .build();

        Instruction i3 = new Instruction.InstuctionBuilder()
                .setEntity("A")
                .setBuySellFlag(BuySellFlag.S)
                .setAgreedFx(new BigDecimal(0.2))
                .setCurrency(Currency.getInstance("AED"))
                .setInstructionDate(LocalDate.of(2018, Month.JULY, 4))
                .setSettlementDate(LocalDate.of(2018, Month.JULY, 5))
                .setUnits(100)
                .setPricePerUnit(new BigDecimal(10.5))
                .build();

        Instruction i4 = new Instruction.InstuctionBuilder()
                .setEntity("A")
                .setBuySellFlag(BuySellFlag.S)
                .setAgreedFx(new BigDecimal(0.2))
                .setCurrency(Currency.getInstance("AED"))
                .setInstructionDate(LocalDate.of(2018, Month.JULY, 4))
                .setSettlementDate(LocalDate.of(2018, Month.JULY, 14))
                .setUnits(100)
                .setPricePerUnit(new BigDecimal(10.5))
                .build();

        instructions.add(i1);
        instructions.add(i2);
        instructions.add(i3);
        instructions.add(i4);

        Reporter reporter = new InstructionReporter();
        String report = reporter.report(instructions);
        System.out.println(report);
    }
}
