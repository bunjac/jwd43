var wafepaApp = angular.module('wafepaApp');

wafepaApp.controller("DrzaveCtrl", function($scope, $http){
    
    var url = "https://restcountries.eu/rest/v2/all?fields=name;capital;flag";

    $scope.countries = [];

    var getCountries = function(){
        $http.get(url).then(
            function uspeh(result){
                $scope.countries = result.data;
            },
            function neuspeh(result){
                alert("neuspesno dobavljanje drzava")
            }
        )
    }
    
    getCountries();

});