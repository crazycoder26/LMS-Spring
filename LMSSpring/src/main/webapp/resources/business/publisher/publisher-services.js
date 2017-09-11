lmsApp.factory("publisherService", function($http, publisherConstants){
	return{
		initPublisherService: function(){
			var getPublisherData = {};
			return $http({
				url: publisherConstants.INIT_PUBLISHER_URL
			}).success(function(data){
				getPublisherData = data;
			}).then(function(){
				return getPublisherData;
			})
		},
		initBookService: function(){
			var getBookData = {};
			return $http({
				url: publisherConstants.INIT_BOOK_URL
			}).success(function(data){
				getBookData = data;
			}).then(function(){
				return getBookData;
			})
		},
		initBookListService: function(){
			var listdata = {};
			return $http({
				url: publisherConstants.GET_BOOKLIST_URL
			}).success(function(data){
				listdata = data;
			}).then(function(){
				return listdata;
			})
		},
		addPublisherService: function(list, name, address, phone){
			var publisherData = {};
			return $http({
				url: publisherConstants.ADD_PUBLISHER_URL + "/" + list + "/" + name + "/" + address + "/" + phone
			}).success(function(data){
				publisherData = data;
			}).then(function(){
				return publisherData;
			})
		},
		getAllBooksService: function(){
			var booksData = {};
			return $http({
				url: publisherConstants.GET_ALLBOOKS_URL
			}).success(function(data){
				bookData = data;
			}).then(function(){
				return bookData;
			})
		},
        getAllPublishersService: function(){
			var getPublishersData = {};
			return $http({
				url: publisherConstants.GET_ALL_PUBLISHERS_URL
			}).success(function(data){
				getPublishersData = data;
			}).then(function(){
				return getPublishersData;
			})
		},
		getPublishersByName: function(publisherName){
			var getPublishersData = {};
			return $http({
				url: publisherConstants.SEARCH_PUBLISHER_BY_NAME +"/"+ publisherName
			}).success(function(data){
				getPublishersData = data;
			}).then(function(){
				return getPublishersData;
			})
		},
		getPublisherByPk: function(publisherId){
			var getPublisherByPkData = {};
			return $http({
				url: publisherConstants.GET_PUBLISHER_BY_PK_URL+"/"+ publisherId
			}).success(function(data){
				getPublisherByPkData = data;
			}).then(function(){
				return getPublisherByPkData;
			})
		},
		deletePublisherService: function(publisherId){
			var deleteData = {};
			return $http({
				url: publisherConstants.DELETE_PUBLISHER_URL + "/" + publisherId
			}).success(function(data){
				deleteData = data;
			}).then(function(){
				return deleteData;
			})
		},
		updatePublisherService: function(id, name, add, phone){
			var values = id + "," + name + "," + add + "," + phone
			var result = {};
			return $http({
				url: publisherConstants.UPDATE_PUBLISHER_URL + "/" + values
			}).success(function(data){
				result = data;
			}).then(function(){
				return result;
			})
		}
	}
})