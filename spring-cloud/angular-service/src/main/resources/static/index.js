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

    const headerDict = {
        'Content-Type': 'Access-Control-Allow-Origin',
        'Accept': 'application/json',
        'Access-Control-Allow-Headers': 'Content-Type',
    }
    const requestOptions = {
        headers: new Headers(headerDict),
    };

    $scope.fillTable = function () {
        console.log("1");
        $http({
            url: contextPath + '/api/v1/products',
            // url: 'http://localhost:51200/',
            method: 'GET',
        }).then(function (response) {
            $scope.ProductList = response.data;
        });
    };

    $scope.fillTable();
});