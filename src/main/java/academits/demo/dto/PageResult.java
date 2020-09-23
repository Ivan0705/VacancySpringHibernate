package academits.demo.dto;

import java.util.List;

public class PageResult<T> {
    private List<T> entries;
    private long total;
    private int pages;

    public PageResult(List<T> entries, long total, int pages) {
        this.entries = entries;
        this.total = total;
        this.pages = pages;
    }

    public List<T> getEntries() {
        return entries;
    }

    public void setEntries(List<T> entries) {
        this.entries = entries;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
