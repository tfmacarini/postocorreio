'use strict';

angular.module('PostoCorreio')
    .factory('Correspondencia', 
        function(railsResourceFactory, API_URL) {
        var Correspondecia = railsResourceFactory({
            url: API_URL + 'correspondencias'
        });

        return Correspondecia;
    });