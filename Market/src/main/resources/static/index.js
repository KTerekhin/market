angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';
    $scope.authorized = false;

    $scope.showProductsPage = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                p: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;

            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.ProductsPage.totalPages) {
                maxPageIndex = $scope.ProductsPage.totalPages;
            }

            $scope.PaginationArray = $scope.generatePagesIndexes(1, $scope.ProductsPage.totalPages);
        });
    };

    $scope.showCart = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    };

    $scope.showMyOrders = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.MyOrders = response.data;
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    // $scope.submitCreateNewProduct = function () {
    //     $http.post(contextPath + '/api/v1/products', $scope.newProduct)
    //         .then(function (response) {
    //             $scope.newProduct = null;
    //             $scope.fillTable();
    //         });
    // };

    $scope.deleteProductById = function (id) {
        $http.delete(contextPath + '/api/v1/products/' + id)
            .then(function (response) {
                $scope.showProductsPage();
            });
    }

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

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $scope.clientname =  $scope.user.username;
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.authorized = true;
                    $scope.showProductsPage();
                    $scope.showMyOrders();
                    $scope.showCart();
                }
            }, function errorCallback(response) {
                window.alert("Error");
            });
    }

    $scope.createOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders/create',
            method: 'POST',
            params: {address: $scope.order.address}
        }).then(function (response) {
            $scope.showMyOrders();
            $scope.showCart();
        })
    }

    // $scope.fillTable();
    // $scope.showCart();
});