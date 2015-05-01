/**
 * Created by shaunn on 4/12/2015.
 */
(function () {
    'use strict';

    angular.module('app')
        .config(function ($mdThemingProvider, $routeProvider) {
            $mdThemingProvider.theme('default')
                .primaryPalette('blue')
                .accentPalette('red');

            $routeProvider.when('/', {
                templateUrl: '/views/search.html'
            }).when('/create', {
                templateUrl: '/views/create.html'
            }).when('/admin/actuator', {
                templateUrl: '/views/actuator.html'
            }).otherwise('/');
        });
})();