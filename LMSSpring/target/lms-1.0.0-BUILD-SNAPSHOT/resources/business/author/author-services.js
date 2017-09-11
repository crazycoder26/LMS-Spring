lmsApp.factory("authorService", function($http, authorConstants){
	return{
		initAuthorService: function(){
			var getAuthorData = {};
			return $http({
				url: authorConstants.INIT_AUTHOR_URL
			}).success(function(data){
				getAuthorData = data;
			}).then(function(){
				return getAuthorData;
			})
		},
        saveAuthorService: function(author){
        	console.log(author);
			var saveAuthorData = {};
			return $http({
				url: authorConstants.ADD_AUTHOR_URL,
                method: "POST",
                data: {
                    author: author
                }
			}).success(function(data){
				saveAuthorData = data;
			}).then(function(){
				return saveAuthorData;
			})
		},
        getAllAuthorsService: function(){
			var getAuthorsData = {};
			return $http({
				url: authorConstants.GET_ALL_AUTHORS_URL
			}).success(function(data){
				getAuthorsData = data;
			}).then(function(){
				return getAuthorsData;
			})
		},
		getAuthorsByName: function(authorName){
			var getAuthorsData = {};
			return $http({
				url: authorConstants.SEARCH_AUTHOR_BY_NAME +"/"+authorName
			}).success(function(data){
				getAuthorsData = data;
			}).then(function(){
				return getAuthorsData;
			})
		},
		getAuthorByPk: function(authorId){
			var getAuthorByPkData = {};
			return $http({
				url: authorConstants.GET_AUTHOR_BY_PK_URL+"/"+authorId
			}).success(function(data){
				getAuthorByPkData = data;
			}).then(function(){
				return getAuthorByPkData;
			})
		}
	}
})