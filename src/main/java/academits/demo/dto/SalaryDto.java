package academits.demo.dto;

public class SalaryDto {
    private int to;
    private int from;
    private String currency;
    private boolean gross;

    public SalaryDto() {
    }

    public SalaryDto(Integer to, Integer from, String currency, boolean gross) {
        this.to = to;
        this.from = from;
        this.currency = currency;
        this.gross = gross;
    }

    public SalaryDto(Integer to, int from, String currency, boolean gross) {
        this.currency = currency;
        this.gross = gross;
    }

    public SalaryDto(int to, int from, String currency, boolean gross) {
        this.to = to;
        this.from = from;
        this.currency = currency;
        this.gross = gross;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isGross() {
        return gross;
    }

    public void setGross(boolean gross) {
        this.gross = gross;
    }

    @Override
    public String toString() {
        return "\"from\":\"" + this.from + "\", \"to\":\"" + this.to + "\", \"currency\":\"" + this.currency + "\", \"gross\":\"" + this.gross + "\"";
    }
}
