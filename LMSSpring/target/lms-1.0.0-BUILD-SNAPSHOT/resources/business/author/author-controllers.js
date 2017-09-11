lmsApp.controller("addAuthorController", function($scope, $window, $http, authorService){
	
	authorService.initAuthorService().then(function(data){
		$scope.author = data;
	})
	
	
	$scope.saveAuthor = function(){
		console.log($scope.author);
		authorService.saveAuthorService($scope.author).then(function(data){
			$window.location.href = "#/listauthors";
		})
	}
	
});



lmsApp.controller("listAuthorsController", function($scope, $window, $http, authorService, Pagination, $filter){
    
	authorService.getAllAuthorsService().then(function(data){
		$scope.authors = data;
		$scope.pagination = Pagination.getNew(10);
		$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
	})
	
	$scope.editAuthorModal = function(authorId){
    	authorService.getAuthorByPk(authorId).then(function(data){
    		$scope.author = data;
    		$scope.showEditAuthorModal = true;
    	})
    	
    }
	
	$scope.closeModal = function(){
		$scope.showEditAuthorModal = false;
		$window.location.href = "#/viewauthors";
	}
	
	$scope.editAuthor = function(){
		console.log("here");
		authorService.updateAuthorService($scope.author).then(function(data){
			$window.location.href = "#/viewauthors";
		})
	}
	
    $scope.searchAuthors = function(){
    	authorService.getAuthorsByName($scope.searchString).then(function(data){
    		if($scope.searchString == ""){
    			$window.location.href = "#/viewauthors";
    		}
    		$scope.authors = data;
    		$scope.pagination = Pagination.getNew(10);
    		$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
    	})
    }
    
    $scope.sortAuthors = function(){
    	$scope.authors = $filter("orderBy")($scope.authors, "authorName");
    }
    
    
});
