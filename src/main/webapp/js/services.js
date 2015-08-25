/**
 * Service APP
 */
app = angular.module('mainApp.services',[]);

app.factory('Produto',function($resource){
	return $resource('rest/produtos/:id',{id:'@id'},{
        'update': {method:'PUT'},
	    'save'  : {method:'POST'},
        'delete': {method:'DELETE'},
        'query' : {method:'GET', isArray:true},
        'get' : {method:'GET'}
    });

}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});

app.factory('Grupo',function($resource){
	return $resource('rest/grupos/:id',{id:'@id'},{

	})
});
