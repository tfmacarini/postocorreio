'use strict';

angular.module('PostoCorreio')
    .factory('Cidade', 
        function(railsResourceFactory, API_URL) {
        var Cidade = railsResourceFactory({
            url: API_URL + 'cidades'
        });

        return Cidade;
    });