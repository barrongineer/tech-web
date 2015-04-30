/**
 * Created by shaunn on 4/28/2015.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('NavigationController', NavigationController);

    NavigationController.$inject = ['$http', '$mdSidenav', '$location'];

    function NavigationController($http, $mdSidenav, $location) {
        var vm = this;
        vm.logout = logout;
        vm.toggleNav = toggleNav;
        vm.goto = goto;
        vm.hasRole = hasRole;
        vm.user = {
            authorities: []
        };

        function hasRole(role) {
            var hasRole = false;

            angular.forEach(vm.user.authorities, function (value) {
                if (value.authority === role) {
                    hasRole = true;
                }
            });

            return hasRole;
        }

        function goto(url) {
            $location.url(url);
        }

        function toggleNav() {
            $mdSidenav('nav').toggle();
        }

        function logout() {
            document.getElementById("logoutForm").submit();
        }

        $http.get('/oauth-service/uaa/user')
            .success(function (data) {
                vm.user = data;
                console.log(data);
            });
    }
})();
