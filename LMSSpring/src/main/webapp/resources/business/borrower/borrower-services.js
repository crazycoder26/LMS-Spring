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
		initLoansService: function(){
			var loansData = {};
			return $http({
				url: borrowerConstants.INIT_LOANS_URL
			}).success(function(data){
				loansData = data;
			}).then(function(){
				return loansData;
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
		checkOutBookService: function(card, bookId, branchId){
			var checkOutData = {};
			return $http({
				url: borrowerConstants.CHECK_OUT_BOOK_URL + "/" + card + "/" + bookId + "/" + branchId
			}).success(function(data){
				checOutData = data;
			}).then(function(data){
				return checkOutData;
			})
		},
		checkInBookService: function(bookId, branchId, cardNo){
			var checkInData = {};
			return $http({
				url: borrowerConstants.CHECK_IN_BOOK_URL + "/"+ bookId + "/" + branchId + "/" + cardNo
			}).success(function(data){
				checkInData = data;
			}).then(function(){
				return checkInData;
			})
		},
		getAllBorrowerService: function(){
			var borrowerData = {};
			return $http({
				url:borrowerConstants.GET_ALL_BORROWER_URL
			}).success(function(data){
				borrowerData = data;
			}).then(function(){
				return borrowerData;
			})
		},
		deleteCardService: function(cardNo){
			var deleteData = {};
			return $http({
				url: borrowerConstants.DELETE_BORROWER_URL + "/" + cardNo
			}).success(function(data){
				deleteData = data;
			}).then(function(){
				return deleteData;
			})
		},
		addBorrowerService: function(name, address, phone){
			var data = {};
			return $http({
				url: borrowerConstants.ADD_BORROWER_URL + "/" + name + "/" + address + "/" + phone
			}).success(function(data){
				data = data;
			}).then(function(){
				return data;
			})
		},
		searchBorrowerService: function(name){
			var bdata = {};
			return $http({
				url: borrowerConstants.SEARCH_BORROWER_NAME_URL + "/" + name
			}).success(function(data){
				bdata = data;
			}).then(function(){
				return bdata;
			})
		},
		getAllLoansService: function(){
			var loansData = {};
			return $http({
				url: borrowerConstants.GET_LOANS_PENDING_URL
			}).success(function(data){
				loansData = data;
			}).then(function(){
				return loansData;
			})
		},
		overrideDateService: function(bookId, branchId, cardNo, days){
			var data = {};
			return $http({
				url: borrowerConstants.OVERRIDE_LOANS_URL + "/" + bookId + "/" + branchId + "/" + cardNo + "/" + days
			}).success(function(data){
				data = data;
			}).then(function(){
				return data;
			})
		},
		editBorrowerService: function(cardNo, name, address, phone){
			var values = cardNo + "," + name + "," + address + "," + phone;
			var editData = {};
			return $http({
				url: borrowerConstants.UPDATE_BORROWER_URL + "/" + values
			}).success(function(data){
				editData = data;
			}).then(function(){
				return editData;
			})
		}
	}
})