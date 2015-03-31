/**
 * Created by shaunn on 3/16/2015.
 */
(function () {
    'use strict';

    angular.module('app').controller('SearchController', SearchController);

    SearchController.$inject = ['$scope', '$http', '$mdToast'];

    function SearchController($scope, $http, $mdToast) {
        var self = this;

        $http.get("/technology")
        .success(function(data) {
            self.technologies = data;
        }).error(function(data, status) {
            $mdToast.showSimple(status + ' Error.');
        });

            self.selectedItem  = null;
            self.searchText    = null;
            self.querySearch   = querySearch;
            // ******************************
            // Internal methods
            // ******************************
            /**
             * Search for states... use $timeout to simulate
             * remote dataservice call.
             */
            function querySearch (query) {
              var results = query ? self.technologies.filter( createFilterFor(query) ) : [];
              return results;
            }

            /**
             * Create filter function for a query string
             */
            function createFilterFor(query) {
              return function filterFn(technology) {
                return (technology.displayName.toLowerCase().indexOf(query.toLowerCase()) === 0);
              };
            }

            self.search = function() {
                if (self.searchText !== null && self.searchText !== '') {

                    $mdToast.showSimple('Loading...');

                    $http.get("/technology/search/" + self.searchText)
                    .success(function(data) {
                        self.filteredTechnologies = data;
                    }).error(function(data, status) {
                        $mdToast.showSimple(status + ' Error.');
                    });
                } else {
                    self.filteredTechnologies = self.technologies;
                }
            };
    }

})();