lmsApp.controller("addAuthorController", function($scope, $window, $http, Pagination, authorService){
	
	authorService.initAuthorService().then(function(data){
		$scope.author = data;
	})
	
	authorService.getBooksService().then(function(data){
		$scope.books = data;
		$scope.pagination = Pagination.getNew(10);
		$scope.pagination.numPages = Math.ceil($scope.books.length/$scope.pagination.perPage);
	})
	
	authorService.initBookService().then(function(data){
		$scope.book = data;
	})

	
	authorService.initBookListService().then(function(data){
		$scope.bookList = data;
	})
	
	$scope.addBook = function(bookId){
		document.getElementById("myButton1").value = "selected";
		authorService.initBookService().then(function(data){
			$scope.book = data;
		})
		$scope.book.bookId = bookId;
		console.log($scope.book);
		$scope.bookList.push($scope.book);
	}
	
	$scope.saveAuthor = function(){
		console.log($scope.author);
		$scope.author.books = $scope.bookList;
		authorService.saveAuthorService($scope.author).then(function(data){
			$window.location.href = "#/listauthors";
		})
	}
	
	$scope.searchBooks = function(){
		authorService.searchBooksService($scope.searchString).then(function(data){
			$scope.books = data;
			$scope.pagination = Pagination.getNew(10);
    		$scope.pagination.numPages = Math.ceil($scope.books.length/$scope.pagination.perPage);
		})
	}
	
});



lmsApp.controller("listAuthorsController", function($scope, $window, $http, authorService, Pagination, $filter){
    
	authorService.getAllAuthorsService().then(function(data){
		$scope.authors = data;
		$scope.pagination = Pagination.getNew(10);
		$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
		$scope.showAuthorsTable = true;
	})
	
	$scope.editAuthorModal = function(authorId){
    	authorService.getAuthorByPk(authorId).then(function(data){
    		$scope.author = data;
    		$scope.showEditAuthorModal = true;
    	})
    	
    }
	
	$scope.closeModal = function(){
		$scope.showEditAuthorModal = false;
		$window.location.reload();
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
    
    $scope.deleteAuthor = function(authorId){
    	authorService.deleteAuthorService(authorId).then(function(data){
    		$window.location.href = "#/author"
    	})
    }
    
});
