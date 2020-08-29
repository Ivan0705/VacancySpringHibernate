package academits.demo.dto.api;

import academits.demo.model.Vacancy;

import java.util.List;

public class ResultPage {
    private int page;
    private int pages;
    private List<Vacancy> items;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Vacancy> getItems() {
        return items;
    }

    public void setItems(List<Vacancy> items) {
        this.items = items;
    }
}
