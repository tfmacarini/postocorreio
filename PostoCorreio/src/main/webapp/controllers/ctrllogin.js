'use strict';

function LoginController($scope) {
    
}

function LoginRoute($stateProvider) {
    $stateProvider.state('login', {
        url: '/login',
        templateUrl: 'views/login.html',
        controller: 'LoginController'
    });
}
angular.module('PostoCorreio')
        .config(LoginRoute)
        .controller('LoginController', LoginController);