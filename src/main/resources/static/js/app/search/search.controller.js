/**
 * Created by shaunn on 3/16/2015.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('TechnologyController', TechnologyController);

    TechnologyController.$inject = ['$mdToast', 'technologyService'];

    function TechnologyController($mdToast, technologyService) {
        var vm = this;
        vm.technologies = [];
        vm.selectedItem = null;
        vm.searchText = null;
        vm.querySearch = querySearch;
        vm.search = search;

        var _loadingToast = $mdToast.simple().content('Loading...').position('top right').hideDelay(0);

        init();

        function init() {
            $mdToast.show(_loadingToast);
            technologyService.findAllTechnologies().then(function (data) {
                vm.technologies = data;
                $mdToast.hide(_loadingToast);
            });
        }

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
                $mdToast.show(_loadingToast);

                technologyService.findAllTechnologiesByDisplayName(vm.searchText).then(function (data) {
                    vm.filteredTechnologies = data;
                    $mdToast.hide(_loadingToast);
                });
            } else {
                vm.filteredTechnologies = vm.technologies;
            }
        };
    }
})();