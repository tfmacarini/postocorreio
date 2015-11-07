var app = angular.module('app',['ngRoute']);
 
app.config(function($routeProvider, $locationProvider)
{
   // remove o # da url
   $locationProvider.html5Mode(true);
 
   $routeProvider
 
    .when('/', {
      templateUrl : 'PostoCorreio/views/home.html',
      controller     : 'HomeCtrl',
   })
  
   .when('/corresp', {
      templateUrl : 'PostoCorreio/views/corresp.html',
      controller  : 'SobreCtrl',
   })
   
   .when('/pessoa', {
      templateUrl : 'PostoCorreio/views/pessoas.html',
      controller  : 'PessoaCtrl',
   })
   
   .when('/cidade', {
      templateUrl : 'PostoCorreio/views/cidades.html',
      controller  : 'CidadeCtrl',
   })
 
   .when('/familia', {
      templateUrl : 'PostoCorreio/views/familias.html',
      controller  : 'ContatoCtrl',
   })
   
   .when('/usuario', {
      templateUrl : 'PostoCorreio/views/usuarios.html',
      controller  : 'UsuarioCtrl',
   })
 
   .otherwise ({ redirectTo: '/' });
});