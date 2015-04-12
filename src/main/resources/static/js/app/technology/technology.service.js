/**
 * Created by shaunn on 4/12/2015.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .factory('technologyService', technologyService);

    technologyService.$inject = ['$http', '$mdToast'];

    function technologyService($http, $mdToast) {
        return {
            findAllTechnologies: findAllTechnologies,
            findAllTechnologiesByDisplayName: findAllTechnologiesByDisplayName
        };

        function findAllTechnologies() {
            return $http.get("/tech-service/technology")
                .then(handleResponse)
                .catch(showError);
        }

        function findAllTechnologiesByDisplayName(displayName) {
            return $http.get("/tech-service/technology/displayName/" + displayName)
                .then(handleResponse)
                .catch(showError);
        }

        function handleResponse(response) {
            return response.data;
        }

        function showError(response) {
            $mdToast.showSimple({content: response.status + ' Error.', position: 'top right'});
        }
    }
})();
