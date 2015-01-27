flexpokerModule.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'Index',
            templateUrl: rootUrl + 'resources/templates/main.html'
        });
});
