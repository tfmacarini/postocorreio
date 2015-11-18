'use strict';

angular.module('PostoCorreio')
    .factory('Usuario', 
        function(railsResourceFactory, API_URL) {
        var Usuario = railsResourceFactory({
            url: API_URL + 'usuarios'
        });

        return Usuario;
    });