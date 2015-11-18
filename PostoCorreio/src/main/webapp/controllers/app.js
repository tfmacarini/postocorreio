'use strict';

angular.module('PostoCorreio', [
    'ui.router',
    'ui.bootstrap',
    'rails'
]).config(function ($urlRouterProvider, RailsResourceProvider, railsSerializerProvider) {
    $urlRouterProvider.when('', '/');
   
    RailsResourceProvider.rootWrapping(false);
    railsSerializerProvider.underscore(angular.identity).camelize(angular.identity);
}).constant('API_URL', 'api/');