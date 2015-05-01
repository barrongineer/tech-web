/**
 * Created by shaunn on 3/16/2015.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('CreateController', CreateController);

    CreateController.$inject = ['$http'];

    function CreateController($http) {
        var vm = this;
        vm.submit = submit;
        vm.technology = {};

        function submit() {
            $http.post('/technology', vm.technology)
                .success(function (data) {
                    console.log(data);
                })
        }


    }
})();