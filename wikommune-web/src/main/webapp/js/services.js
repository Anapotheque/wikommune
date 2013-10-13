var services = angular.module('fr.egloo.wikommune.web.service', ['ngResource']);

services.factory('UserFactory', function ($resource) {
    return $resource('/fr/egloo/wikommune/web/communes', {}, {
        query: {
            method: 'GET',
            params: {},
            isArray: false
        }
    })
});
