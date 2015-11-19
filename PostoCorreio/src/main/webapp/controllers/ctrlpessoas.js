'use strict';

function PessoaController($scope, Pessoa) {        
    $scope.limpar = function () {
        $scope.pessoa = {};
    };

    $scope.listar = function () {
        Pessoa.query().then(function (data) {
            $scope.lista = data;
        }, function (error) {
            console.log('error', error);
            alert(error.data);
        });
    };

    $scope.gravar = function () {
      
        if ($scope.pessoa.id) {
            $scope.pessoa.update().then(function () {
                $scope.limpar();
                $scope.listar();
            }, function (error) {
                console.log('error', error);
                alert(error.data);
            });
        } else {
            new Pessoa($scope.pessoa).create()
                    .then(function () {
                        $scope.limpar();
                        $scope.listar();
                    }, function (error) {
                        console.log('error', error);
                        alert(error.data);
                    });
        }
    };

    $scope.editar = function (pessoa) {
        $scope.pessoa = angular.copy(pessoa);
    };

    $scope.deletar = function (pessoa) {
        pessoa.remove().then(function () {
            $scope.listar();
        }, function (error) {
            console.log('error', error);
            alert(error.data);
        });
    };

    $scope.limpar();
    $scope.listar();
}
function PessoaRoute($stateProvider) {
    $stateProvider.state('pessoa', {
        url: '/pessoa',
        templateUrl: 'views/pessoas.html',
        controller: 'PessoaController'
    });
}
angular.module('PostoCorreio')
        .config(PessoaRoute)
        .controller('PessoaController', PessoaController);