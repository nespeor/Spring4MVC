'use strict';

var App = angular.module('myApp',['ngRoute']);

//configure our routes
App.config(function($routeProvider) {
    $routeProvider

        //route for the users page
        .when('/users', {
            templateUrl : 'static/pages/users.html',
            controller  : 'UserController'
        })

        // route for the about page
        .when('/about', {
            templateUrl : 'static/pages/about.html',
            controller  : 'aboutController'
        })
});
