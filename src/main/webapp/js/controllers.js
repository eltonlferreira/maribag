/**
 * Controller API
 */
angular.module('mainApp.controllers', []).controller('ProdutoListController',
		function($scope, $state, popupService, $window, Produto, Grupo) {

			$scope.produtos = Produto.query();
			$scope.grupos = Grupo.query();

			$scope.getTotal = function(idgrupo) {
				total = 0;
				for (var i = 0; i < $scope.produtos.length; i++) {
					produto = $scope.produtos[i];
					if (produto.grupo.id == idgrupo) {
						total += produto.valor > 0 ? produto.valor : 0;
					}
				}
				return total;
			}

			$scope.deleteProduto = function(produto) {
				if (popupService.showPopup('Tem certeza disso?')) {
					produto.$delete(function() {
						$window.location.href = ''; // redirect to home
					});
				}
			}

			$scope.markProduto = function(produto) {
				produto.$update(function() {
					$state.go('produtos');
				});
			}

		}).controller('ProdutoViewController',
		function($scope, $stateParams, Produto) {

			$scope.produto = Produto.get({
				id : $stateParams.id
			});

		}).controller('ProdutoCreateController',
		function($scope, $state, $stateParams, Produto, Grupo) {

			$scope.grupos = Grupo.query();
			$scope.produtos = Produto.query();

			$scope.produto = new Produto();

			$scope.addProduto = function() {
				$scope.produto.$save(function() {
					$state.go('produtos');
				});
			}

		}).controller('GrupoCreateController',
		function($scope, $state, $stateParams, Produto, Grupo) {

			$scope.grupo = new Grupo();

			$scope.addGrupo = function() {
				$scope.grupo.$save(function() {
					$state.go('produtos');
				});
			}

		}).controller('ProdutoEditController',
		function($scope, $state, $stateParams, Produto, Grupo) {

			$scope.produtos = Produto.query();
			$scope.grupos = Grupo.query();

			$scope.updateProduto = function() {
				console.log(JSON.stringify($scope.produto));
				$scope.produto.$update(function() {
					$state.go('produtos');
				});
			};

			$scope.loadProduto = function() {
				$scope.produto = Produto.get({
					id : $stateParams.id
				});
			};

			$scope.loadProduto();
		});