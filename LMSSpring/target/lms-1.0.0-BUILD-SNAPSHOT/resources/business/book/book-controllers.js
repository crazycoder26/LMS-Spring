/**
 * 
 */
lmsApp.controller("addBookController", function($scope, $window, $http, authorService){
	
	bookService.getAllBooksService().then(function(data){
		$scope.book = data;
	})
	
	
	$scope.saveBook = function(){
		bookService.saveBookService($scope.book).then(function(data){
			$window.location.href = "#/viewbooks";
		})
	}
	
});

lmsApp.controller("listBooksController", function($scope, $window, $http, bookService, Pagination, $filter){
    
	bookService.getAllBooksService().then(function(data){
		$scope.books = data;
		$scope.pagination = Pagination.getNew(10);
		$scope.pagination.numPages = Math.ceil($scope.books.length/$scope.pagination.perPage);
	})
	
	$scope.editBookModal = function(bookId){
    	bookService.getBookByPk(bookId).then(function(data){
    		$scope.book = data;
    		$scope.showEditBookModal = true;
    	})
    	
    }
    $scope.searchBooks = function(){
    	if($scope.searchString === " "){
    		$window.location.href = "#/viewbooks";
    	}
    	bookService.getBooksByName($scope.searchString).then(function(data){
    		$scope.books = data;
    		$scope.pagination = Pagination.getNew(10);
    		$scope.pagination.numPages = Math.ceil($scope.books.length/$scope.pagination.perPage);
    	})
    }
    
    $scope.sortBooks = function(){
    	$scope.books = $filter("orderBy")($scope.books, "title");
    }
    
});
