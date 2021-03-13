angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189';

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
        }).then(function (response) {
            $scope.ProductsList = response.data;
        });
    };
});