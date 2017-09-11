lmsApp.controller("addPublisherController", function($scope, $window, $http, publisherService){
	
	publisherService.initPublisherService().then(function(data){
		$scope.publisher = data;
	})
	console.log($scope.publisher);
	
	
	$scope.savePublisher = function(){
		publisherService.savePublisherService($scope.publisher).then(function(data){
			$window.location.href = "#/viewpublishers";
		})
	}
	
});



lmsApp.controller("listPublishersController", function($scope, $window, $http, publisherService, Pagination, $filter){
    
	publisherService.getAllPublishersService().then(function(data){
		$scope.publishers = data;
		$scope.pagination = Pagination.getNew(10);
		$scope.pagination.numPages = Math.ceil($scope.publishers.length/$scope.pagination.perPage);
	})
	
	
	
    $scope.searchPublishers = function(){
		publisherService.getPublishersByName($scope.searchString).then(function(data){
			$scope.publishers = data;
			$scope.pagination = Pagination.getNew(10);
    		$scope.pagination.numPages = Math.ceil($scope.publishers.length/$scope.pagination.perPage);
		})
		
    }
    
    $scope.sortPublishers = function(){
    	$scope.publishers = $filter("orderBy")($scope.publishers, "publisherName");
    }
    
    $scope.sortPublishersA = function(){
    	$scope.publishers = $filter("orderBy")($scope.publishers, "publisherAddress");
    }
    
});
