var wafepaApp = angular.module('wafepaApp', []);

wafepaApp.controller("MyController", function ($scope) {
    $scope.polje1 = "ovde text";
    $scope.disableDugme = ($scope.polje1 === "");

    $scope.prazniPolje = function () {
        $scope.polje1 = "";
        $scope.disableDugme = true;
    };

    $scope.kontrolisiDugme = function () {
        proveriText($scope);
    };
});

wafepaApp.controller("MyControllerDrugi", function ($scope) {
    $scope.polje1;
});

function proveriText($scope) {
    if ($scope.polje1 === "") {
        $scope.disableDugme = true;
    }
    else {
        $scope.disableDugme = false;
    }
};

