flexpokerModule.controller('MainController', ['$rootScope', '$scope', 'ngstomp', '$location', '$templateCache',
    function($rootScope, $scope, ngstomp, $location, $templateCache) {
    
    $rootScope.username = $('.username').prop('innerText');
    
    if ($scope.client === undefined) {
        $scope.client = ngstomp(new SockJS(rootUrl + 'application'));
    }

    $scope.client.connect("", "", function(frame) {

    }, function() {}, '/');

    if ($rootScope.stompClients === undefined) {
        $rootScope.stompClients = [];
    }
    $rootScope.stompClients.push($scope.client);
}]);
