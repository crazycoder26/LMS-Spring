lmsApp.controller("validateBorrowerController", function($scope, $window, $http, borrowerService){
	
	borrowerService.initBorrowerService().then(function(data){
		$scope.borrower = data;
	})
	
	$scope.validateCard = function(cardNo){
		borrowerService.validateCardService(cardNo).then(function(data){
			console.log(data);
			var val = data;
			if(val == true){
				$window.location.href = "#/viewbranches"
			}else{
				$window.location.href = "#/MainBorrower"
			}
		})
	}
	
});

lmsApp.controller("ListAllBorrowerBranches", function($scope, $window, $http, borrowerService, Pagination){
	
	borrowerService.initBorrowerService().then(function(data){
		$scope.borrower = data;
	})
	
	$scope.validateCard = function(cardNo){
		borrowerService.validateCardService(cardNo).then(function(data){
			console.log(data);
			var val = data;
			if(val == true){
				$scope.showBranchTable = true;
			}else{
				$window.location.href = "#/MainBorrower"
			}
		})
	}
	
	borrowerService.initBorrowerService().then(function(data){
		$scope.borrower = data;
	})
	
	borrowerService.initBookService().then(function(data){
		$scope.book = data;
	})
	
	borrowerService.initBranchService().then(function(data){
		$scope.branch = data;
	})
	
	
	
	$scope.getAllbranchBooks = function(branchId){
		$scope.branch.branchId = branchId;
		console.log($scope.branch);
		borrowerService.getAllbranchBooksService(branchId).then(function(data){
			$scope.books = data;
			
			$scope.showViewBookModal = true;
		})
	}
	
	
	$scope.checkOutBook = function(bookId){
		$scope.book.bookId = bookId;
		console.log($scope.book);
		borrowerService.checkOutBookService($scope.borrower, $scope.book, $scope.branch).then(function(data){
			$window.location.href = "#/admin"
		})
	}
	
});

lmsApp.controller("checkIn", function($scope, $window, $http, borrowerService, Pagination){
	
	borrowerService.initBorrowerService().then(function(data){
		$scope.borrower = data;
	})
	
	var cardno = 0; 
	$scope.validateCard = function(cardNo){
		cardno = cardNo;
		borrowerService.validateCardService(cardNo).then(function(data){
			console.log(data);
			var val = data;
			if(val == true){
				borrowerService.ListAllLoans(cardno).then(function(data){
					console.log(cardno);
					$scope.loans = data;
					console.log(data);
				})
				$scope.showBranchTable = true;
			}else{
				$window.location.href = "#/MainBorrower"
			}
		})
	}
	
	
	
	
	borrowerService.initBookService().then(function(data){
		$scope.book = data;
	})
	
	borrowerService.initBranchService().then(function(data){
		$scope.branch = data;
	})
	
	
	
	
	
});



//lmsApp.controller("listAuthorsController", function($scope, $window, $http, authorService, Pagination, $filter){
//    
//	authorService.getAllAuthorsService().then(function(data){
//		$scope.authors = data;
//		$scope.pagination = Pagination.getNew(10);
//		$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
//	})
//	
//	$scope.editAuthorModal = function(authorId){
//    	authorService.getAuthorByPk(authorId).then(function(data){
//    		$scope.author = data;
//    		$scope.showEditAuthorModal = true;
//    	})
//    	
//    }
//	
//	$scope.closeModal = function(){
//		$scope.showEditAuthorModal = false;
//		$window.location.default();
//	}
//	
//	
//    $scope.searchAuthors = function(){
//    	authorService.getAuthorsByName($scope.searchString).then(function(data){
//    		if($scope.searchString == ""){
//    			$window.location.href = "#/viewauthors";
//    		}
//    		$scope.authors = data;
//    		$scope.pagination = Pagination.getNew(10);
//    		$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
//    	})
//    }
//    
//    $scope.sortAuthors = function(){
//    	$scope.authors = $filter("orderBy")($scope.authors, "authorName");
//    }
//      
//});
