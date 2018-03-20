"use strict";

(function() {

    var UploadController =  function($log,$http) {
    	$log.log("Upload Controller Created");
    	var vm = this;

		vm.handleFiles = function(e){
			var ctx = document.getElementById('canvas').getContext('2d');
			var img = new Image;
			img.src = URL.createObjectURL(e.target.files[0]);
			img.onload = function() {
				ctx.drawImage(img, 20,20);
				vm.make_base2(img);
			}
		}

    	vm.make_base2 =function(img1){
    		console.log("make_base2 Created");
			var str;
			var img = new Image();
			img = img1;

				var canvas = document.createElement('canvas'), context = canvas.getContext('2d');
				canvas.width = img.width;
				canvas.height = img.height;
				context.drawImage(img, 0, 0, img.width, img.height);
				str = canvas.toDataURL('image/png');
				console.log("HERE");

				$http({
        			method: "post",
        			url: "http://localhost:5000/POSTIMAGE",
        			headers: {
        				"Accept": "application/json, text/plain, ",
        				"Content-Type": "text/plain"
        			},
        			data:str
        		}).then(function (result){
					$log.log(result);
					$log.log(result.data);
					if(result.data=="successful upload"){	
						document.getElementById('resulting').innerHTML = "Fantastic";
					}
				});
		}
		
		vm.getImage = function(){
			$http({
        			method: "get",
        			url: "http://localhost:5000/GETIMAGE",
        			headers: {
        				"Accept": "application/json, text/plain, ",
        				"Content-Type": "text/plain"
        			},
        			data:""
        		}).then(function (result){
					$log.log("successful");
					$log.log(result.data);
					var output = "<img src='data:image/png;base64,"+result.data+"' >";
					document.getElementById('imageresult').innerHTML = output;
				});
		}

		var input = document.getElementById('input');
		input.addEventListener('change', vm.handleFiles);

    }
    angular.module('accountApp').controller('uploadController',['$log','$http',UploadController]);
}());