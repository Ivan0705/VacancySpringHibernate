Vue.use(GoTop);

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
            sizePage: 35,
            areas: [],
            countTopVacancy: 50,
            currency: "",
            currentName: "",
            loadDataActive: true
        },
        computed: {
            sortedCitiesName: function () {
                function compare(a, b) {
                    if (a.name < b.name) {
                        return -1;
                    }
                    if (a.name > b.name) {
                        return 1;
                    }
                    return 0;
                }
                return this.areas.sort(compare);
            }
        },
        methods: {
            translateCurrency: function (currency) {
                if (currency === "RUR") {
                    return "руб.";

                }

                if (currency === "KZT") {
                    return "тенге";
                }

                if (currency === "BYR") {
                    return "бел.руб.";
                }

                if (currency === "EUR") {
                    return "евро";
                }

                if (currency === "USD") {
                    return "долл.";
                }
                return "у.е.";
            },
            formatDate: function (date) {
                return moment(date).format('DD.MM.YYYY');
            },
            applyFilter: function () {
                this.filterActive = true;
                this.currentPage = 0;
                this.loadDataActive = true;
                this.loadData();
            },
            resetFilter: function () {
                this.filterQuery = null;
                this.filterActive = false;
                this.loadData();
            },
            getTopSalary: function () {
                this.filterActive = true;
                this.currentPage = 0;
                this.loadDataActive = false;
                this.getSalary();
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
                if (this.loadDataActive) {
                    this.currentPage = page;
                    this.loadData();
                }
                if (!this.loadDataActive) {
                    this.currentPage = page;
                    this.getSalary();
                }
            },
            getSalary: function () {
                const self = this;

                $.ajax({
                    type: "GET",
                    url: "/vacancyBook/rpc/api/v1/getTopSalary?page=" + this.currentPage + "&sizePage=" + this.sizePage + "&countTopVacancy=" + this.countTopVacancy
                }).done(function (vacancyListFormServer) {
                    self.rows = self.convertVacancyList(vacancyListFormServer.entries);
                    self.pages = vacancyListFormServer.pages;
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

                $.get("/vacancyBook/rpc/api/v1/getAllVacancies",
                    {
                        filter: filterQuery,
                        filterRegion: filterRegion,
                        page: this.currentPage,
                        sizePage: this.sizePage,
                    }
                ).done(function (vacancyListFormServer) {
                    self.rows = self.convertVacancyList(vacancyListFormServer.entries);
                    self.pages = vacancyListFormServer.pages;
                });
            },
            prevPage: function () {
                if (this.currentPage >= 1) {
                    this.currentPage--;
                }
                if (this.loadDataActive) {
                    this.loadData();
                } else {
                    this.getSalary();
                }
            },
            nextPage: function () {
                if (this.currentPage < this.pages - 1) {
                    this.currentPage++;
                }
                if (this.loadDataActive) {
                    this.loadData();
                } else {
                    this.getSalary();
                }
            }
            ,
            loadRegions: function () {
                const self = this;

                $.get("/vacancyBook/rpc/api/v1/getRegions"
                ).done(function (response) {
                    self.areas = response;
                });
            }
        },
        created: function () {
            this.loadRegions();
            this.loadData();
        }
    }
);

