$(document).ready(function(){

	 var v = $('#leaveApproveflag').text() ;
	 
	   if(v == 1){
		   $("#approvedSuccessful").modal();
	   }else if(v ==2){
		   $("#approvedFail").modal();
	   }
	   
	   var v1 = $('#leaveRejectflag').text() ;
	  
	   if(v1 == 1){
		   $("#rejectSuccessful").modal();
	   }else if(v ==2){
		   $("#rejectFail").modal()
	   }
	
});

