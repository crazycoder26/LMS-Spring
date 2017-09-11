lmsApp.factory("borrowerService", function($http, borrowerConstants){
	return{
		initBorrowerService: function(){
			var getBorrowerData = {};
			return $http({
				url: borrowerConstants.INIT_BORROWER_URL
			}).success(function(data){
				getBorrowerData = data;
			}).then(function(){
				return getBorrowerData;
			})
		},
		initBookService: function(){
			var getBookData = {};
			return $http({
				url: borrowerConstants.INIT_BOOK_URL
			}).success(function(data){
				getBookData = data;
			}).then(function(){
				return getBookData;
			})
		},
		initBranchService: function(){
			var getBranchData = {};
			return $http({
				url: borrowerConstants.INIT_BRANCH_URL
			}).success(function(data){
				getBranchData = data;
			}).then(function(){
				return getBranchData;
			})
		},
		validateCardService: function(cardNo){
			var cardData = {};
			return $http({
				url: borrowerConstants.VALIDATE_CARD_URL + "/" + cardNo
			}).success(function(data){
				cardData = data;
			}).then(function(){
				return cardData;
			})
		},
		ListAllBranchService: function(){
			var branchData = {};
			return $http({
				url: borrowerConstants.READ_BRANCHES_URL
			}).success(function(data){
				branchData = data;
			}).then(function(){
				return branchData;
			})
		},
		getAllbranchBooksService: function(branchId){
			var booksData = {};
			return $http({
				url: borrowerConstants.READ_BRANCHBOOKS_URL + "/" + branchId
			}).success(function(data){
				booksData = data;
			}).then(function(data){
				return booksData;
			})
		},
		ListAllLoans: function(cardNo){
			var loansData = {};
			return $http({
				url: borrowerConstants.READ_ALLLOANS_URL + "/" + cardNo
			}).success(function(data){
				loansData = data;
			}).then(function(data){
				return loansData;
			})
		},
		checkOutBookService: function(borrower, book, branch){
			var checkOutData = {};
			return $http({
				url: borrowerConstants.CHECK_OUT_BOOK_URL,
                method: "POST",
                data: {
                    borrower: borrower,
                    book: book,
                    branch: branch  
                }
			}).success(function(data){
				checOutData = data;
			}).then(function(data){
				return checkOutdata;
			})
		}
		
	}
	  
	
	
//        getAllAuthorsService: function(){
//			var getAuthorsData = {};
//			return $http({
//				url: authorConstants.GET_ALL_AUTHORS_URL
//			}).success(function(data){
//				getAuthorsData = data;
//			}).then(function(){
//				return getAuthorsData;
//			})
//		},
//		getAuthorsByName: function(authorName){
//			var getAuthorsData = {};
//			return $http({
//				url: authorConstants.SEARCH_AUTHOR_BY_NAME +"/"+authorName
//			}).success(function(data){
//				getAuthorsData = data;
//			}).then(function(){
//				return getAuthorsData;
//			})
//		},
//		getAuthorByPk: function(authorId){
//			var getAuthorByPkData = {};
//			return $http({
//				url: authorConstants.GET_AUTHOR_BY_PK_URL+"/"+authorId
//			}).success(function(data){
//				getAuthorByPkData = data;
//			}).then(function(){
//				return getAuthorByPkData;
//			})
//		}
//	}
})