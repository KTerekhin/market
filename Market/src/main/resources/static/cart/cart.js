angular.module('app').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market';

    $scope.showCart = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    };

    $scope.addProductToCart = function (id) {
        $http.get(contextPath + '/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.decrementProductFromCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/dec/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.showCart();
        });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + '/api/v1/cart/clear')
            .then(function (response) {
                $scope.showCart();
            });
    };

    // $scope.createOrder = function () {
    //     $http.get(contextPath + '/api/v1/orders/create')
    //         .then(function (response) {
    //             $scope.showMyOrders();
    //             $scope.showCart();
    //         });
    // }

    // $scope.createOrder = function () {
    //     $http({
    //         url: contextPath + '/api/v1/orders/create',
    //         method: 'POST',
    //         params: {address: $scope.order.address}
    //     }).then(function (response) {
    //         $scope.showMyOrders();
    //         $scope.showCart();
    //     })
    // }

    $scope.goToOrderSubmit = function () {
        $location.path('/order_confirmation');
    }

    $scope.showCart();
});