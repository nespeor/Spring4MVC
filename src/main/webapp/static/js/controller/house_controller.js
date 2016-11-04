'use strict';

angular.module('myApp').controller('HouseController', ['$scope', 'HouseService', function($scope, HouseService) {
    var self = this;
    self.house={id:null,calle:'', num:'', codpostal:'', poblacion:'', tipo:'', numhab:'', descripcion:''};
    self.houses=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllHouses();

    function fetchAllHouses(){
        HouseService.fetchAllHouses()
            .then(
            function(d) {
                self.houses = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

    function createUser(house){
        UserService.createUser(house)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }

    function updateUser(house, id){
        UserService.updateUser(house, id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating User');
            }
        );
    }

    function deleteUser(id){
        UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }

    function submit() {
        if(self.house.id===null){
            console.log('Saving New User', self.house);
            createUser(self.house);
        }else{
            updateUser(self.house, self.house.id);
            console.log('User updated with id ', self.house.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.houses.length; i++){
            if(self.houses[i].id === id) {
                self.house = angular.copy(self.houses[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.house.id === id) {//clean form if the house to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }


    function reset(){
        self.house={id:null,calle:'', num:'', codpostal:'', poblacion:'', tipo:'', numhab:'', descripcion:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
