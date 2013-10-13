<!doctype html>
<html ng-app="wikommune-web">
	<head>
		<link rel="stylesheet" type="text/css" href="/wikommune-web/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="/wikommune-web/skin/css/structure.css">
		<link rel="stylesheet" type="text/css" href="/wikommune-web/skin/css/details.css">
		<link rel="stylesheet" type="text/css" href="/wikommune-web/skin/css/overrideBootstrap.css">
	</head>
	<body>
	
		<div class="wrapper ui-corner-all">
    	    <header class="ui-corner-all">
	            <h1>WIKOMMUNE</h1>
	            Un site qu'il est pratique pour trouver une commune...
	        </header>
	        <nav class="ui-corner-all">
	            NAVIGATION
	        </nav>
	        <nav class="ui-corner-all">
	            BREADCRUMBS
	        </nav>
	        <div class="body">
	            <aside class="ui-corner-all">
	            	<ul class="menu">
					    <li><a href="/wikommune-web/#/Toulon">Toulon</a></li>
					    <li><a href="/wikommune-web/#/Paris">Paris</a></li>
					    <li><a href="/wikommune-web/#/Nanterre">Nanterre</a></li>
					</ul>
	            </aside>
	            <section class="ui-corner-all">
	                
				    <p>
						<label>Tu cherches une commune ?</label>
						<div class="input-append">
					    	<input type="text" class="span2" id="appendedInputButton" ng-model="yourName" placeholder="Votre commune">
						    <button class="btn" type="button">Go!</button>
					    </div>
				    </p>
				   	
					<div class="progress progress-striped active">
				    	<div class="bar" style="width: 40%;"></div>
				    </div>
				    
				    <h1>{{yourName}}</h1>
					<hr/>
					
					<div ng-view></div>
					
	            </section>
	        </div>
	        <div class="clear"></div>
	        <footer class="ui-corner-all">
	            <i>Powered by the over-clocking Drixxor</i>
	        </footer>
        </div>

	   	<script src="/wikommune-web/angularJS/angular.js"></script>
		<script src="/wikommune-web/angularJS/angular-resource.js"></script>
		
		<script src="/wikommune-web/js/app.js"></script>
	   	<script src="/wikommune-web/js/services.js"></script>
	   	<script src="/wikommune-web/js/controllers.js"></script>
	   	<script src="/wikommune-web/js/filters.js"></script>
	   	<script src="/wikommune-web/js/directives.js"></script>
	   			
	</body>
</html>