/**
 * APP
 */
angular.module('mainApp', [ 'ui.router', 'ngResource', 'mainApp.controllers',
		'mainApp.services' ]);

angular.module('mainApp').config(function($stateProvider) {
	$stateProvider.state('produtos', {
		url : '',
		templateUrl : 'modules/produtos.html',
		controller : 'ProdutoListController'

	}).state('viewProduto', {
		url : '/produtos/:id/view',
		templateUrl : 'modules/produto-view.html',
		controller : 'ProdutoViewController'

	}).state('newProduto', {
		url : '/produtos/new',
		templateUrl : 'modules/produto-add.html',
		controller : 'ProdutoCreateController'

	}).state('editProduto', {
		url : '/produtos/:id/edit',
		templateUrl : 'modules/produto-edit.html',
		controller : 'ProdutoEditController'

	}).state('newGrupo', {
		url : '/grupo/new',
		templateUrl : 'modules/grupo-add.html',
		controller : 'GrupoCreateController'
	});
}).run(function($state) {
	$state.go('produtos');
});