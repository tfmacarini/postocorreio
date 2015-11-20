'use strict';

function HomeController($scope) {
    $scope.mensagem = 'Teste';
}
function HomeRoute($stateProvider) {
    $stateProvider.state('home', {
        url: '/home',
        templateUrl: 'views/home.html',
        controller: 'HomeController'
    });
}
angular.module('PostoCorreio')
        .config(HomeRoute)
        .controller('HomeController', HomeController);