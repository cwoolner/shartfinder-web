flexpokerModule.controller('Index', ['$rootScope', '$scope', 'ngstomp', function($rootScope, $scope, ngstomp) {

    $('body').find('button, input[type=submit]').button();
    $scope.chatDisplay = '';

    if ($scope.client === undefined) {
        $scope.client = ngstomp(new SockJS(rootUrl + 'application'));
    }

    $scope.client.connect("", "", function() {
        $scope.client.subscribe('/topic/chat/global/user', function(message) {
            var scrollHeight = $('.chat-display').prop('scrollHeight');
            $('.chat-display').prop('scrollTop', scrollHeight);
            $scope.chatDisplay += $.parseJSON(message.body) + '\n';
        });
        $scope.client.subscribe('/topic/chat/global/system', function(message) {
            var scrollHeight = $('.chat-display').prop('scrollHeight');
            $('.chat-display').prop('scrollTop', scrollHeight);
            $scope.chatDisplay += $.parseJSON(message.body) + '\n';
        });
    }, function() {}, '/');

    if ($rootScope.stompClients === undefined) {
        $rootScope.stompClients = [];
    }
    $rootScope.stompClients.push($scope.client);

    $scope.sendChat = function() {
        if ($scope.chatMessage == '') {
            return;
        }
        $scope.client.send('/app/sendchatmessage', {}, JSON.stringify($scope.chatMessage));
        $scope.chatMessage = '';
    };

    $scope.sendDiceRoll = function() {
    	if ($scope.diceRoll == '') {
    		return;
    	}

    	var diceRollMessage = {
    			encounterId: '1a2ebaa8-a609-11e4-89d3-123b93f75cba',
    			diceRoll: $scope.diceRoll
    	};

    	$scope.client.send('/app/rolldice', {}, JSON.stringify(diceRollMessage));
    	$scope.diceRoll = '';
    };


  if ($scope.encounter === undefined) {
    $scope.encounter = {};
  };

  $scope.encounter.getCombatants = function() {
    return [{combatantName: "JSG", currentHP: 22, maxHP: 30, status: null, isActive: false},
            {combatantName: "Tomp", currentHP: 2, maxHP: 30, status: "drummed up", isActive: false},
            {combatantName: "Boolner", currentHP: 2, maxHP: 49, status: null, isActive: false},
            {combatantName: "Ogre", currentHP: 28, maxHP: 84, status: null, isActive: false},
            {combatantName: "Bike", currentHP: 20, maxHP: 35, status: null, isActive: false},
            {combatantName: "Bean", currentHP: 2, maxHP: 32, status: null, isActive: true},
            {combatantName: "Shook", currentHP: 0, maxHP: 22, status: "Norwegian", isActive: false}
           ];
  };

  $scope.encounter.getEncounterName = function() {
    return "Goblin Ball Battle";
  }

}]);
