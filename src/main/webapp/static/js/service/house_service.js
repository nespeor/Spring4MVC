'use strict';

angular.module('myApp').factory('HouseService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/Spring4MVC/house/';

    var factory = {
        fetchAllHouses: fetchAllHouses,
        createHouse: createHouse,
        updateHouse: updateHouse,
        deleteHouse: deleteHouse
    };

    return factory;

    function fetchAllHouses() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Houses');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createHouse(house) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, house)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating House');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateHouse(house, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, house)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating House');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteHouse(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting House');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
