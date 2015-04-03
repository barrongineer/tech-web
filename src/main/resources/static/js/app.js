/**
 * Created by shaunn on 3/16/2015.
 */
(function () {
    'use strict';

    angular.module('app', ['ngMaterial'])
        .config(function($mdThemingProvider) {
            $mdThemingProvider.theme('default')
                .primaryPalette('cyan')
                .accentPalette('deep-orange');
        });
})();