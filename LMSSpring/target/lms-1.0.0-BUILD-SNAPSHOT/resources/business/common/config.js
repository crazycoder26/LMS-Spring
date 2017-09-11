
lmsApp.config(["$routeProvider", function($routeProvider){
	return $routeProvider.when("/", {
		redirectTo: "/home"
	}).when("/home", {
		templateUrl: "welcome.html"
	}).when("/admin", {
		templateUrl: "admin.html"
	}).when("/librarian", {
		templateUrl: "librarian.html"
	}).when("/borrower", {
		templateUrl: "borrower.html"
	}).when("/viewauthors", {
		templateUrl: "viewauthors.html"
	}).when("/author", {
		templateUrl: "author.html"
	}).when("/addauthor", {
		templateUrl: "addauthor.html"
	}).when("/book", {                   
		templateUrl: "book.html"
	}).when("/addbook", {
		templateUrl: "addbook.html"
	}).when("/viewbooks", {
		templateUrl: "viewbooks.html"
	}).when("/publisher", {
		templateUrl: "publisher.html"
	}).when("/addpublisher", {
		templateUrl: "addPublisher.html"
	}).when("/viewpublisher", {
		templateUrl: "viewPublisher.html"
	}).when("/MainBorrower", {
		templateUrl: "MainBorrower.html"
	}).when("/checkin", {
		templateUrl: "checkin.html"
	}).when("/checkout", {
		templateUrl: "viewborrowerbranches.html"
	}).when("/librarian",{
		templateUrl: "librarian.html"
	})
}])