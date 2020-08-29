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
        currentPage: 0

    }, prop: {
        listData: {
            type: Array,
            required: true
        },
        size: {
            type: Number,
            required: false,
            default: 10
        }
    },
    computed: {
        cityName: function () {
            var cities = {};
            this.rows.forEach(function (row) {
                if (row.area) {
                    cities[row.area.id] = row.area;
                }
            });
            return Object.values(cities);
        },
        getDate: function () {
            var year;
            var month;
            var day;
            var date;
            /*  var self = this;

            /* if (self.rows.published_at) {
                   year = self.rows.published_at.slice(0, 4);
                   month = self.rows.published_at.slice(5, 7);
                   day = self.rows.published_at.slice(8, 10);
                   date = day + "." + month + "." + year;
               }*/
            this.rows.forEach(function (row) {
                if (row.published_at) {
                    year = row.published_at.slice(0, 4);
                    month = row.published_at.slice(5, 7);
                    day = row.published_at.slice(8, 10);
                    date = day + "." + month + "." + year;
                }
            });
            return date;
        }


    },

    methods: {
        applyFilter: function () {
            this.filterActive = true;

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
        getTopSalary: function () {
            var self = this;

            $.ajax({
                type: "GET",
                url: "/vacancyBook/rpc/api/v1/getTopSalary"
            }).done(function (vacancyListFormServer) {
                self.rows = self.convertVacancyList(vacancyListFormServer);
            });
        },
        loadData: function () {
            var self = this;

            var filterQuery = "";
            var filterRegion = "";
            if (this.filterActive) {
                filterQuery = this.filterQuery;
                filterRegion = this.filterRegion
            }

            $.get("/vacancyBook/rpc/api/v1/getAllVacancies?filter=" + filterQuery + "&filterRegion=" + filterRegion)
                .done(function (vacancyListFormServer) {
                    self.rows = self.convertVacancyList(vacancyListFormServer);
                });
        },
        prevPage: function () {
            this.currentPage--;
            g
        },
        nextPage: function () {
            this.currentPage++;
        }
    },
    created: function () {
        this.loadData();
    }
});

