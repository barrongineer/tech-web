/**
 * Created by shaunn on 3/16/2015.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('ActuatorController', ActuatorController);

    ActuatorController.$inject = ['$http'];

    function ActuatorController($http) {
        var vm = this;
        vm.metrics = {};
        vm.health = {};
        vm.beans = {};

        init();

        function init() {
            $http.get('/admin/metrics')
                .success(function (data) {
                    vm.metrics = data;
                });

            $http.get('/admin/health')
                .success(function (data) {
                    vm.health = data;
                });

            $http.get('/admin/beans')
                .success(function (data) {
                    vm.beans = data[0].beans;
                });
        }
    }
})();