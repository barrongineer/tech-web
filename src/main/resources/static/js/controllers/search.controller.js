/**
 * Created by shaunn on 3/16/2015.
 */
(function () {
    'use strict';

    angular.module('app').controller('SearchController', SearchController);

    SearchController.$inject = ['$http', '$mdToast'];

    function SearchController($http, $mdToast) {
        var vm = this;
        vm.selectedItem = null;
        vm.searchText = null;
        vm.querySearch = querySearch;
        vm.search = search;
        vm.loadingToast = $mdToast.simple().content('Loading...').position('top right').hideDelay(0);

        $http.get("/technology")
            .success(function (data) {
                vm.technologies = data;
            }).error(function (data, status) {
                $mdToast.showSimple(status + ' Error.');
            });

        function querySearch(query) {
            var results = query ? vm.technologies.filter(createFilterFor(query)) : [];
            return results;
        }

        function createFilterFor(query) {
            return function filterFn(technology) {
                return (technology.displayName.toLowerCase().indexOf(query.toLowerCase()) > -1);
            };
        }

        function search() {
            if (vm.searchText !== null && vm.searchText !== '') {

                $mdToast.show(vm.loadingToast);

                $http.get("/technology/search/" + vm.searchText)
                    .success(function (data) {
                        vm.filteredTechnologies = data;
                        $mdToast.hide(vm.loadingToast);
                    }).error(function (data, status) {
                        $mdToast.showSimple(status + ' Error.');
                    });
            } else {
                vm.filteredTechnologies = vm.technologies;
            }
        };
    }
})();