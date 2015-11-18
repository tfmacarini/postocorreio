'use strict';

function UsuarioController($scope, Usuario) {
    $scope.limpar = function () {
        $scope.usuario = {};
    };

    $scope.listar = function () {
        Usuario.query().then(function (data) {
            $scope.lista = data;
        }, function (error) {
            console.log('error', error);
            alert(error.data);
        });
    };

    $scope.gravar = function () {
      
        if ($scope.usuario.id) {
            $scope.usuario.update().then(function () {
                $scope.limpar();
                $scope.listar();
            }, function (error) {
                console.log('error', error);
                alert(error.data);
            });
        } else {
            new Usuario($scope.usuario).create()
                    .then(function () {
                        $scope.limpar();
                        $scope.listar();
                    }, function (error) {
                        console.log('error', error);
                        alert(error.data);
                    });
        }
    };

    $scope.editar = function (usuario) {
        $scope.usuario = angular.copy(usuario);
    };

    $scope.deletar = function (usuario) {
        usuario.remove().then(function () {
            $scope.listar();
        }, function (error) {
            console.log('error', error);
            alert(error.data);
        });
    };

    $scope.limpar();
    $scope.listar();
}
function UsuarioRoute($stateProvider) {
    $stateProvider.state('usuario', {
        url: '/usuario',
        templateUrl: 'views/usuarios.html',
        controller: 'UsuarioController'
    });
}
angular.module('PostoCorreio')
        .config(UsuarioRoute)
        .controller('UsuarioController', UsuarioController);