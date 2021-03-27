angular.module('app').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.addProductToCart = function (productId) {
        $http.get(contextPath + '/api/v1/cart/{uuid}/add/' + productId)
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.decrementProductFromCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/{uuid}/dec/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.showCart();
        });
    };

    $scope.clearCart = function () {
        $localStorage.marketCart.clear();
    };

    $scope.goToOrderSubmit = function () {
        $location.path('/order_confirmation');
    }

    $scope.cartView = $localStorage.happyCart;
});