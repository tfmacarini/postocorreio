'use strict';

function CidadeController($scope, Cidade) {
    $scope.limpar = function () {
        $scope.cidade = {};
    };

    $scope.listar = function () {
        Cidade.query().then(function (data) {
            $scope.lista = data;
        }, function (error) {
            console.log('error', error);
            alert(error.data);
        });
    };

    $scope.gravar = function () {
      
        if ($scope.cidade.id) {
            $scope.cidade.update().then(function () {
                $scope.limpar();
                $scope.listar();
            }, function (error) {
                console.log('error', error);
                alert(error.data);
            });
        } else {
            new Cidade($scope.cidade).create()
                    .then(function () {
                        $scope.limpar();
                        $scope.listar();
                    }, function (error) {
                        console.log('error', error);
                        alert(error.data);
                    });
        }
    };

    $scope.editar = function (cidade) {
        $scope.cidade = angular.copy(cidade);
    };

    $scope.deletar = function (cidade) {
        cidade.remove().then(function () {
            $scope.listar();
        }, function (error) {
            console.log('error', error);
            alert(error.data);
        });
    };

    $scope.limpar();
    $scope.listar();
}
function CidadeRoute($stateProvider) {
    $stateProvider.state('cidade', {
        url: '/cidade',
        templateUrl: 'views/cidades.html',
        controller: 'CidadeController'
    });
}
angular.module('PostoCorreio')
        .config(CidadeRoute)
        .controller('CidadeController', CidadeController);