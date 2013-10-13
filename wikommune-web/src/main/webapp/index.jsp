<!doctype html>
<html ng-app>
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
	            	ASIDE
	            </aside>
	            <section class="ui-corner-all">
	                
				    <p>
						<label>Tu cherches une commune ?</label>
						<div class="input-append">
					    	<input type="text" class="span2 input-xlarge" id="appendedInputButton" ng-model="yourName" placeholder="Remplis ta commune ici...">
						    <button class="btn" type="button">Go!</button>
					    </div>
				    </p>
				   	
					<div class="progress progress-striped active">
				    	<div class="bar" style="width: 40%;"></div>
				    </div>
				    
				    <h1>{{yourName}}</h1>
					<hr/>
	
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent vehicula ut magna fermentum fermentum. Sed pharetra elit quis nisi lacinia facilisis. Praesent non elit a arcu lobortis scelerisque. Suspendisse in egestas tellus, sed pretium enim. Quisque dapibus pellentesque libero ac dictum. Nulla tristique gravida rutrum. Proin venenatis tellus ut varius vulputate. Nullam ut rhoncus tellus. Cras at enim urna. Pellentesque dignissim ultrices eros, at vehicula erat placerat ut.
					<br/><br/>
					Nunc imperdiet ipsum at erat sodales, sollicitudin dignissim libero cursus. Nullam quis luctus lacus, eu sagittis lorem. Integer eu volutpat tortor. Quisque accumsan odio id turpis semper, facilisis ornare odio mollis. Integer justo augue, cursus id arcu in, pretium venenatis nisl. Proin ac dolor a lorem aliquet pellentesque sit amet id magna. Aenean posuere sapien in mi sollicitudin, et dapibus metus porttitor. Proin pharetra, est nec feugiat ultricies, nisi augue ultricies lectus, vitae commodo erat urna sit amet sapien. Sed id tellus congue, fringilla tortor vitae, tristique est.
					<br/><br/>
					Integer sodales interdum gravida. Curabitur porttitor congue lorem in volutpat. Proin vel augue ut diam sodales tempor. Vivamus accumsan libero quis metus vulputate vestibulum tempor non eros. Phasellus nulla lacus, ullamcorper et urna et, convallis mattis purus. Maecenas ac enim lectus. Phasellus sollicitudin suscipit quam quis ultricies. Vivamus quis scelerisque tortor.
					<br/><br/>
					Donec a metus purus. Pellentesque sit amet lorem luctus, convallis nibh sed, convallis sem. Sed lobortis risus eget quam adipiscing, sit amet scelerisque leo malesuada. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec fringilla sapien at nisl auctor blandit. Vivamus tellus nisi, blandit et quam a, dictum consequat risus. Aliquam at tincidunt libero. Quisque in tristique lorem. Aenean gravida arcu non commodo aliquet. Vestibulum molestie lorem sit amet urna porta ullamcorper ut a nisi. Fusce fringilla elit sit amet molestie auctor. Donec congue scelerisque tellus, id accumsan elit euismod vel. Aliquam faucibus quam massa, eget pretium magna ultrices non.
					<br/><br/>
					Etiam at dui consectetur, tincidunt massa ac, faucibus velit. Suspendisse suscipit turpis id euismod euismod. Donec at tincidunt arcu. Proin lacinia justo sit amet augue egestas euismod. Curabitur tincidunt arcu nec eros facilisis faucibus. Nunc et nisi urna. Phasellus eget tellus gravida, ultrices nisl sed, viverra urna. Proin commodo laoreet dolor rhoncus elementum. Curabitur at porttitor sem.
				    
	            </section>
	        </div>
	        <div class="clear"></div>
	        <footer class="ui-corner-all">
	            <i>Powered by the over-clocking Drixxor</i>
	        </footer>
        </div>
	   	
	   	<script src="/wikommune-web/angularJS/angular.min.js"></script>
		
	</body>
</html>