/**
 * Created by shaunn on 4/12/2015.
 */
(function () {
    'use strict';

    angular.module('app')
        .config(function ($mdThemingProvider) {
            $mdThemingProvider.theme('default')
                .primaryPalette('cyan')
                .accentPalette('deep-orange');
        });
})();