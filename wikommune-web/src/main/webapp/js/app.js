'use strict';

// Declare app level module which depends on filters, and services
angular.module('wikommune-web', ['wikommune-web.filters', 'wikommune-web.services', 'wikommune-web.directives', 'wikommune-web.controllers']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/', {templateUrl: '/wikommune-web/view/communes.jsp', controller: 'CommunesControlleur'});
        $routeProvider.when('/:commune', {templateUrl: '/wikommune-web/view/communes.jsp', controller: 'CommunesControlleur'});
        $routeProvider.otherwise({redirectTo: '/:commune'});
    }]);
