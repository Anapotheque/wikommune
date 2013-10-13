'use strict';

/* Services */

var services = angular.module('wikommune-web.services', ['ngResource']);

services.factory('CommuneFactory', ['$resource',
   function($resource){
	   return $resource('/wikommune-web/rest/communes/:commune', {}, {
		   query: {method:'GET', params:{commune:'commune'}, isArray:false}
   });
}]);