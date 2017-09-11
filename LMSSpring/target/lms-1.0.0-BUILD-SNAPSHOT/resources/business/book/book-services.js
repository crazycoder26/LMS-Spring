lmsApp.factory("bookService", function($http, bookConstants){
	return{
		initBookService: function(){
			var getBookData = {};
			return $http({
				url: bookConstants.INIT_BOOK_URL
			}).success(function(data){
				getBookData = data;
			}).then(function(){
				return getBookData;
			})
		},
        saveBookService: function(book){
			var saveBookData = {};
			return $http({
				url: bookConstants.ADD_BOOK_URL,
                method: "POST",
                data: {
                    book: book
                }
			}).success(function(data){
				saveBookData = data;
			}).then(function(){
				return saveBookData;
			})
		},
        getAllBooksService: function(){
			var getBooksData = {};
			return $http({
				url: bookConstants.GET_ALL_BOOKS_URL
			}).success(function(data){
				getBooksData = data;
			}).then(function(){
				return getBooksData;
			})
		},
		getBooksByName: function(bookName){
			var getBooksData = {};
			return $http({
				url: bookConstants.SEARCH_BOOK_BY_NAME +"/"+ " " +bookName
			}).success(function(data){
				getBooksData = data;
			}).then(function(){
				return getBooksData;
			})
		},
		getBookByPk: function(bookId){
			var getBookByPkData = {};
			return $http({
				url: bookConstants.GET_BOOK_BY_PK_URL+"/"+bookId
			}).success(function(data){
				getBookByPkData = data;
			}).then(function(){
				return getBookByPkData;
			})
		}
	}
})