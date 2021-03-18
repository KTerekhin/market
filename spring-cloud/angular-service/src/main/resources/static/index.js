(function () {
    'use strict';

    angular
        .module('app', [])
        .config(config)
        .run(run);

    function config($httpProvider) {
    }

    function run() {
    }
})();

angular.module('app').controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:5555/cloud';

    $scope.fillTable = function () {
        console.log("1");
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
        }).then(function (response) {
            $scope.ProductList = response.data;
        });
    };

    $scope.fillTable();
});