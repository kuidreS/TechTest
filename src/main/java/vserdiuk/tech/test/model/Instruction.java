package vserdiuk.tech.test.model;

import vserdiuk.tech.test.util.AppUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Objects;

public class Instruction {

    private String entity;
    private BuySellFlag buySellFlag;
    private BigDecimal agreedFx;
    private Currency currency;
    private LocalDate instructionDate;
    private LocalDate settlementDate;
    private long units;
    private BigDecimal pricePerUnit;

    public Instruction(InstuctionBuilder builder) {
        this.entity = builder.entity;
        this.buySellFlag = builder.buySellFlag;
        this.agreedFx = builder.agreedFx;
        this.currency = builder.currency;
        this.instructionDate = builder.instructionDate;
        this.settlementDate = builder.settlementDate;
        this.units = builder.units;
        this.pricePerUnit = builder.pricePerUnit;
    }

    public String getEntity() {
        return entity;
    }

    public BuySellFlag getBuySellFlag() {
        return buySellFlag;
    }

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public Long getUnits() {
        return units;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return Objects.equals(entity, that.entity) &&
                buySellFlag == that.buySellFlag &&
                Objects.equals(agreedFx, that.agreedFx) &&
                currency == that.currency &&
                Objects.equals(instructionDate, that.instructionDate) &&
                Objects.equals(settlementDate, that.settlementDate) &&
                Objects.equals(units, that.units) &&
                Objects.equals(pricePerUnit, that.pricePerUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entity, buySellFlag, agreedFx, currency, instructionDate, settlementDate, units, pricePerUnit);
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "entity='" + entity + '\'' +
                ", buySellFlag=" + buySellFlag +
                ", agreedFx=" + agreedFx +
                ", currency=" + currency +
                ", instructionDate=" + instructionDate +
                ", settlementDate=" + settlementDate +
                ", units=" + units +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }

    public static class InstuctionBuilder {
        private String entity;
        private BuySellFlag buySellFlag;
        private BigDecimal agreedFx;
        private Currency currency;
        private LocalDate instructionDate;
        private LocalDate settlementDate;
        private long units;
        private BigDecimal pricePerUnit;

        public InstuctionBuilder setEntity(String entity) {
            this.entity = entity;
            return this;
        }

        public InstuctionBuilder setBuySellFlag(BuySellFlag buySellFlag) {
            this.buySellFlag = buySellFlag;
            return this;
        }

        public InstuctionBuilder setAgreedFx(BigDecimal agreedFx) {
            this.agreedFx = agreedFx;
            return this;
        }

        public InstuctionBuilder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public InstuctionBuilder setInstructionDate(LocalDate instructionDate) {
            this.instructionDate = instructionDate;
            return this;
        }

        public InstuctionBuilder setSettlementDate(LocalDate settlementDate) {
            boolean isSundayStart = AppUtil.isSundayStart(this.currency);
            this.settlementDate = AppUtil.getLocalDate(isSundayStart, settlementDate);
            return this;
        }

        public InstuctionBuilder setUnits(long units) {
            this.units = units;
            return this;
        }

        public InstuctionBuilder setPricePerUnit(BigDecimal pricePerUnit) {
            this.pricePerUnit = pricePerUnit;
            return this;
        }

        public Instruction build() {
            return new Instruction(this);
        }
    }

}
