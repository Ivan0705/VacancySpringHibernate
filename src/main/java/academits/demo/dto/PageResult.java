package academits.demo.dto;

import java.util.List;

public class PageResult<T> {
    private List<T> entries;//список записей
    private int total;//общее кол-во
    private int pages;//страницы

    public PageResult() {
    }

    public List<T> getEntries() {
        return entries;
    }

    public void setEntries(List<T> entries) {
        this.entries = entries;
    }

    public int getTotal() {
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
