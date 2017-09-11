/**
 * 
 */
lmsApp.controller("librarianController", function($scope){
	
	librarianService.listAllBranches().then(function(data){
		$scope.branches = data
	})
	
})