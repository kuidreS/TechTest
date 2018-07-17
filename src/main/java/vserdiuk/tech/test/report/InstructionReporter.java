package vserdiuk.tech.test.report;

import vserdiuk.tech.test.model.BuySellFlag;
import vserdiuk.tech.test.model.Instruction;
import vserdiuk.tech.test.util.AppUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InstructionReporter implements Reporter {

    @Override
    public String report(List<Instruction> instructions) {
        StringBuilder stringBuilder = new StringBuilder();

        Map<LocalDate, BigDecimal> incomeAmount = AppUtil.countDailyTradingAmount(instructions, BuySellFlag.B);
        Map<LocalDate, BigDecimal> outgoingAmount = AppUtil.countDailyTradingAmount(instructions, BuySellFlag.S);
        List<Instruction> sortedInstructionsByRank = sortByRank(instructions);

        stringBuilder.append("1. Amount in USD settled incoming everyday\n\n");

        for (Map.Entry<LocalDate, BigDecimal> entry : incomeAmount.entrySet()) {
            stringBuilder.append("Date: ").append(entry.getKey()).append("; ").append("Income: ").append(entry.getValue()).append("\n");
        }

        stringBuilder.append("\n\n");
        stringBuilder.append("2. Amount in USD settled outgoing everyday\n\n");

        for (Map.Entry<LocalDate, BigDecimal> entry : incomeAmount.entrySet()) {
            stringBuilder.append("Date: ").append(entry.getKey()).append("; ").append("Outcome: ").append(entry.getValue()).append("\n");
        }

        stringBuilder.append("\n\n");
        stringBuilder.append("3. Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest\n" +
                "amount for a buy instruction, then foo is rank 1 for outgoing\n\n");


        for (int i=0; i<sortedInstructionsByRank.size(); i++) {
            stringBuilder.append("Rank: ").append(i+1).append("; ").append("Entity: ").append(sortedInstructionsByRank.get(i).getEntity()).append("\n");
        }

        return stringBuilder.toString();
    }

    private List<Instruction> sortByRank(List<Instruction> instructions) {
        Comparator<Instruction> instructionComparator = new Comparator<Instruction>() {
            @Override
            public int compare(Instruction instruction1, Instruction instruction2) {
                return AppUtil.countAmount(instruction2).compareTo(AppUtil.countAmount(instruction1));
            }
        };
        return instructions.stream().sorted(instructionComparator).distinct().collect(Collectors.toList());
    }
}
