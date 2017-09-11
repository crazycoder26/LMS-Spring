lmsApp.factory("publisherService", function($http, publisherConstants){
	return{
		initPublisherService: function(){
			var getPublisherData = {};
			return $http({
				url: publisherConstants.INIT_PUBLISHER_URL,
				method: "GET"
			}).success(function(data){
				getPublisherData = data;
			}).then(function(){
				return getPublisherData;
			})
		},
        savePublisherService: function(publisher){
			var savePublisherData = {};
			return $http({
				url: publisherConstants.ADD_PUBLISHER_URL,
                method: "POST",
                data: {
                    publisher: publisher
                }
			}).success(function(data){
				savePublisherData = data;
			}).then(function(){
				return savePublisherData;
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
		}
	}
})