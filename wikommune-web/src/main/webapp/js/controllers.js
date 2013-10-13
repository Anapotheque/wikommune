'use strict';

/* Controllers */

var app = angular.module('wikommune-web.controllers', []);

//Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
app.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

app.controller('CommunesControlleur', ['$scope', 'CommuneFactory', function ($scope, CommuneFactory) {
	CommuneFactory.get({}, function (communeFactory) {
        $scope.libelle = communeFactory.libelle;
        $scope.codePostal = communeFactory.codePostal;
    })
}]);
