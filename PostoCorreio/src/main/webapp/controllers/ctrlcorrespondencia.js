'use strict';

function CorrespondenciaController($scope, Correspondencia) {
    $scope.limpar = function () {
        $scope.correspondencia = {};
    };

    $scope.listar = function () {
        Correspondencia.query().then(function (data) {
            $scope.lista = data;
        }, function (error) {
            console.log('error', error);
            alert(error.data);
        });
    };
    
    $scope.gravar = function () {
      
        if ($scope.correspondencia.id) {
            $scope.correspondencia.update().then(function () {
                $scope.limpar();
                $scope.listar();
            }, function (error) {
                console.log('error', error);
                alert(error.data);
            });
        } else {
            new Correspondencia($scope.correspondencia).create()
                    .then(function () {
                        $scope.limpar();
                        $scope.listar();
                    }, function (error) {
                        console.log('error', error);
                        alert(error.data);
                    });
        }
    };

    $scope.editar = function (correspondencia) {
        $scope.correspondencia = angular.copy(correspondencia);
    };

    $scope.deletar = function (correspondencia) {
        correspondencia.remove().then(function () {
            $scope.listar();
        }, function (error) {
            console.log('error', error);
            alert(error.data);
        });
    };

    $scope.limpar();
    $scope.listar();
}
function CorrespondenciaRoute($stateProvider) {
    $stateProvider.state('correspondencia', {
        url: '/correspondencia',
        templateUrl: 'views/correspondencias.html',
        controller: 'CorrespondenciaController'
    });
}

angular.module('PostoCorreio')
        .config(CorrespondenciaRoute)
        .controller('CorrespondenciaController', CorrespondenciaController);



