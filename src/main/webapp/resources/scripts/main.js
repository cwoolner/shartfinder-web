var flexpokerModule = angular.module('flexpoker', ['AngularStomp']);

flexpokerModule.directive('chat', function() {
    return {
        templateUrl: rootUrl + 'resources/templates/chatWindow.html',
        restrict: 'A',
        compile: function compile(tElement, tAttrs) {
            return function postLink(scope, iElement, iAttrs) {
                iElement.find('button, input[type=submit]').button();
            }
        }
    }
});
