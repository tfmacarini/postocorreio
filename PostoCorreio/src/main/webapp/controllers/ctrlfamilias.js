'use strict';

function FamiliaController($scope, Familia) {
    $scope.limpar = function () {
        $scope.familia = {};
    };

    $scope.listar = function () {
        Familia.query().then(function (data) {
            $scope.lista = data;
        }, function (error) {
            console.log('error', error);
            alert(error.data);
        });
    };

    $scope.gravar = function () {
      
        if ($scope.familia.id) {
            $scope.familia.update().then(function () {
                $scope.limpar();
                $scope.listar();
            }, function (error) {
                console.log('error', error);
                alert(error.data);
            });
        } else {
            new Familia($scope.familia).create()
                    .then(function () {
                        $scope.limpar();
                        $scope.listar();
                    }, function (error) {
                        console.log('error', error);
                        alert(error.data);
                    });
        }
    };

    $scope.editar = function (familia) {
        $scope.familia = angular.copy(familia);
    };

    $scope.deletar = function (familia) {
        familia.remove().then(function () {
            $scope.listar();
        }, function (error) {
            console.log('error', error);
            alert(error.data);
        });
    };

    $scope.limpar();
    $scope.listar();
}
function FamiliaRoute($stateProvider) {
    $stateProvider.state('familia', {
        url: '/familia',
        templateUrl: 'views/familias.html',
        controller: 'FamiliaController'
    });
}
angular.module('PostoCorreio')
        .config(FamiliaRoute)
        .controller('FamiliaController', FamiliaController);