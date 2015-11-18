'use strict';

angular.module('PostoCorreio')
    .factory('Familia', 
        function(railsResourceFactory, API_URL) {
        var Familia = railsResourceFactory({
            url: API_URL + 'familias'
        });

        return Familia;
    });