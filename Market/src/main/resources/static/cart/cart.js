angular.module('app').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'POST',
            params: {
                uuid: $localStorage.marketCartUuid
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.createOrder = function () {
        $http.get(contextPath + '/api/v1/orders/create')
            .then(function (response) {
                $scope.showMyOrders();
                $scope.showCart();
            });
    }

    $scope.loadCart = function () {
        $http.get(contextPath + '/api/v1/cart/' + $localStorage.marketCartUuid)
            .then(function (response) {
                $scope.marketUserCart = response.data;
            });
    }

    $scope.goToOrderSubmit = function () {
        $location.path('/order_confirmation');
    }

    $scope.addProductToCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add',
            method: 'POST',
            params: {
                uuid: $localStorage.marketCartUuid,
                product_id: productId
            }
        }).then(function (response) {
            console.log("OK");
            $scope.loadCart();
        });
    }

    $scope.decrementProductFromCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/dec',
            method: 'GET',
            params: {
                uuid: $localStorage.marketCartUuid,
                product_id: productId
            }
        }).then(function (response) {
            console.log("OK");
            $scope.loadCart();
        });
    }

    $scope.loadCart();
});