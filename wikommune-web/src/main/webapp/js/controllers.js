var app = angular.module('wikommune.controllers', []);

app.controller('MyCtrl1', ['$scope', 'CommuneFactory', function ($scope, CommuneFactory) {
	CommuneFactory.get({}, function (communeFactory) {
        $scope.libelle = communeFactory.libelle;
    })
}]);
