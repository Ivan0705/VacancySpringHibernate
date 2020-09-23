function Vacancy(name, published_at, employer, area, salary) {
    this.name = name;
    this.published_at = published_at;
    this.area = area;
    this.salary = salary;
    this.employer = employer;
    this.shown = true;
}

new Vue({
    el: "#app",
    data: {
        name: "",
        published_at: "",
        employer: "",
        area: "",
        salary: "",
        rows: [],
        filterQuery: "",
        filterRegion: "",
        filterActive: false,
        pageActive: false,
        currentPage: 0,
        pages: 0,
        sizePage: 10
    },
    computed: {
        cityName: function () {
            const cities = {};
            this.rows.forEach(function (row) {
                if (row.area) {
                    cities[row.area.id] = row.area;
                }
            });
            return Object.values(cities);
        }
    },
    methods: {
        formatDate: function (date) {
            return moment(date).format('DD.MM.YYYY');
        },
        applyFilter: function () {
            this.filterActive = true;
            this.currentPage = 0;
            this.loadData();
        },
        resetFilter: function () {
            this.filterQuery = null;
            this.filterActive = false;
            this.loadData();
        },
        vacancyToString: function (vacancy) {
            var note = "(";
            note += +vacancy.name + ", ";
            note += vacancy.published_at;
            note += vacancy.employer + ", ";
            note += vacancy.area + ", ";
            note += vacancy.salary + ", ";
            note += ")";
            return note;
        },
        convertVacancyList: function (vacancyListFromServer) {
            return vacancyListFromServer.map(function (vacancy, i) {
                return {
                    id: vacancy.id,
                    name: vacancy.name,
                    published_at: vacancy.published_at,
                    employer: vacancy.employer,
                    area: vacancy.area,
                    salary: vacancy.salary,
                    shown: true,
                    number: i + 1

                };
            });
        },
        setPage: function (page) {
            this.currentPage = page;
            this.loadData();
        },
        getTopSalary: function () {
            const self = this;

            $.ajax({
                type: "GET",
                url: "/vacancyBook/rpc/api/v1/getTopSalary"
            }).done(function (vacancyListFormServer) {
                self.rows = self.convertVacancyList(vacancyListFormServer);
            });
        },
        loadData: function () {
            const self = this;
            let filterQuery = "";
            let filterRegion = "";

            if (this.filterActive) {
                filterQuery = this.filterQuery;
                filterRegion = this.filterRegion
            }

            $.get("/vacancyBook/rpc/api/v1/getAllVacancies?filter=" + filterQuery
                + "&filterRegion=" + filterRegion
                + "&page=" + this.currentPage
                + "&sizePage=" + this.sizePage
            ).done(function (vacancyListFormServer) {
                self.rows = self.convertVacancyList(vacancyListFormServer.entries);
                self.pages = vacancyListFormServer.pages;
            });
        },
        prevPage: function () {
            if (this.currentPage >= 1) {
                this.currentPage--;
                this.loadData();
            }
        },
        nextPage: function () {
            if (this.currentPage < this.pages - 1) {
                this.currentPage++;
                this.loadData();
            }
        }
    },
    created: function () {
        this.loadData();
    }
});

