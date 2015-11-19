'use strict';

angular.module('PostoCorreio')
    .factory('Pessoa', 
        function(railsResourceFactory, API_URL) {
        var Pessoa = railsResourceFactory({
            url: API_URL + 'pessoas'
        });

        return Pessoa;
    });